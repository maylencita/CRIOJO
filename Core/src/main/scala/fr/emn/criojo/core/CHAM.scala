package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:15:43 AM
 * To change this template use File | Settings | File Templates.
 */

import Criojo._
import fr.emn.criojo.util.Logger._

abstract class CHAM /*extends AbstractMachine*/ extends RuleFactory{
  protected var index = 0

  val T = new Top(){ def apply(vlst:Variable*):Atom = new Top(vlst.toList) } //Atom("true") //Rel("true")
  val F = Atom("false") //Rel("false")

//  val solution = Solution() //new Solution
  var rules:List[Rule] = List()
  var relations:List[Relation] = List()

  val solution = Solution(this)

  def Guard(sttr:Atom, ruleDefs:(RuleFactory => Rule)*):Guard = new ChamGuard(this, sttr, ruleDefs.toList)
  def Abs(atom:Atom):Guard = new ChamGuard(this, new Top(atom.vars), List((new Top(atom.vars) &: atom) ==> F))

  def addRelation(relation:Relation) { relations :+= relation }

  def addRule(rule:Rule) { rules :+= rule }

  def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = solution.findMatches(conj, subs)

  def relation(relName:String):Option[Relation] = relations.find(_.name == relName)


  def execute(){
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def newLocalRelation(name:String,public:Boolean):LocalRelation = {
    new LocalRelation(name, public)
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation = {
    throw new IllegalAccessException("Unimplemented method newRemoteRelation.")
  }

  def introduceAtom(atom:Atom){
    solution.addAtom(atom)
//    findRelation(atom.relName) match{
//      case r:Relation =>
//        atom.relation = r
//        r.notifyObservers(atom)
//      case _ => //skip
//    }
  }

  def receiveUpdate(atom:Atom){
    findRelation(atom.relName) match{
      case r:Relation =>
        atom.relation = r
        r.notifyObservers(atom)
      case _ => //skip
    }
  }

  def findRelation(relName:String):Relation = relation(relName) match{
      case Some(r) => r
      case _ =>
        if(relName startsWith ("$"))
          new LocalRelation(relName)
        else{
          log(WARNING, this.getClass, "findRelation","Undefined relation " + relName)
          new LocalRelation("Undefined")
        }
  }

  def querySansEffect(conj:List[Atom]):List[Atom] = {
    val lstresult= query(conj, conj.flatMap(a=>a.vars.map(v=>(v,v))))
    solution.revert
    lstresult
  }

  implicit def atomToConjunction(a:Atom):Conjunction = new &:(a, Empty)

  def createRule(h:Head,b:Body,g:Guard,scope:List[Variable]):Rule = new CHAMRule(h, b, g, scope)

//  def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
//    new CHAM with Guard{
//      initRules(ruleDefs)
//    }
//    val guard = new Guard
//    guard.initRules(ruleDefs)
//    guard
//  }

  def rules(ruleDefs:(RuleFactory => Rule)*) { initRules(ruleDefs.toList) }

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{f =>
      val r = f(this)
      processBody(processHead(r),r)
      addRule(r)
    }
  }

  def newRule(head:List[Atom], body:List[Atom]):Rule = newRule(head, body, new EmptyGuard)

  def newRule(head:List[Atom], body:List[Atom], guard:Guard):Rule={
    var headVars = List[RelVariable]()
    val rule = createRule(head,body,guard)//new CHAMRule(head, body, guard)

    if (!rule.isAxiom)
      rule.head.foreach{
        case a:rule.HeadAtom =>
          relation(a.relName) match{
            case Some(r) =>
              a.relation = r
              r.addObserver(a)
            case _ =>
              //TODO Improve this. AbstractMachine should not be aware of values
              if (!a.relName.startsWith("$")){
                log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
                a.relation = new LocalRelation("Undefined")
              }else{
                a.relation = findRelation(a.relName)
              }
          }
          a.vars.foreach{
            case rv:RelVariable => headVars :+= rv
            case _ =>
          }
        case _ =>
      }

    rule.body.foreach{a =>
      a.relation = headVars.find(hv => hv.name == a.relName) match{
        case Some(hv) => hv.relation //new LocalRelation(a.relName)
        case _=> findRelation(a.relName)
      }
      a.vars.foreach{
        case rv: RelVariable if(!headVars.contains(rv)) =>
          relations.find(_.name == rv.name) match{
            case Some(r) => rv.relation = r
            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
    addRule(rule)
    rule
  }

  def processHead(rule:Rule):List[RelVariable]={
    var headVars = List[RelVariable]()

    if (!rule.isAxiom)
      rule.head.foreach{
        case a:rule.HeadAtom =>
          relation(a.relName) match{
            case Some(r) =>
              a.relation = r
              r.addObserver(a)
            case _ =>
                log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
                a.relation = new LocalRelation("Undefined")
          }
          a.vars.foreach{
            case rv:RelVariable => headVars :+= rv
            case _ =>
          }
        case _ =>
      }
    headVars
  }

  def processBody(headVars:List[RelVariable], rule:Rule){
    rule.body.foreach{a =>
      a.relation = headVars.find(hv => hv.name == a.relName) match{
        case Some(hv) => hv.relation
        case _=> findRelation(a.relName)
      }
      a.vars.foreach{
        case rv: RelVariable if(!headVars.contains(rv)) =>
          relation(rv.name) match{
            case Some(r) => rv.relation = r
            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
  }

//object Guard{
//  def apply(sttr:Atom, ruleDefs:(RuleFactory => Rule)*):Guard = {
//    val g = new Guard(sttr,ruleDefs.toList)
//    g
//  }
//}

  class CHAMRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{//(head, body, guard){
    def this() = this(List(), List(), new EmptyGuard, List())

    scope = scp
    val head:List[HeadAtom] = h.map{h => new HeadAtom(h)}

    def notifyRelationObservers(atom:Atom){
      if (atom.relation != null)
        atom.relation.notifyObservers(atom)
      else
        relation(atom.relName) match{
          case Some(relation) =>
            relation.notifyObservers(atom)
  //          if (relation.isInstanceOf[RemoteRelation])
          case _ => log(WARNING, this.getClass, "notifyRelationObservers", "Undefined relation " + atom.relName)
        }
    }

    def execute (subs:List[Substitution]):Boolean = {
      var matches = List[Atom]()
      var result = false

      if (this.isAxiom ||
              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
        log("[Rule.execute] {"+this+"} Substitutions: " + subs)
        log("[Rule.execute] solution= " + solution)
        log("[Rule.execute] Found Matches: " + matches + " for rule " + this)
        levelDown
        val subs2 = subs.union(getSubstitutions(matches))
        if (guard.empty || guard.eval(solution, subs2)){
          levelUp
          applyReaction(solution, subs2)
          result=true
        }else
          levelUp
      }

      //Activate atoms that were inactivated but not eliminated
      solution.revert
      result
    }
  }

  def Var:Variable = {
    index += 1
    new Variable("x"+index)
  }

  case class Tok() extends LocalRelation("$X"+index,true){
    def apply(vars:Variable*):Atom = new Atom(name, vars.toList)
  }

  case class Rel(n:String) extends LocalRelation(n,true){
    addRelation(this)

    def apply(vars:Variable*):Atom = new Atom(name, vars.toList)

    override def equals(that:Any):Boolean = that match{
      case r:Relation => this.name == r.name
      case _ => false
    }
  }

//  object NativeRelation{
//    def apply(n:String)(f:(Atom) => Unit)=new NativeRelation(n,f)
//  }
  def NativeRelation(n:String)(f:(Atom,Solution) => Unit)= {
    val natRel = new NativeRelation(n,this.solution,f)
    addRelation(natRel)
    natRel
  }

/*
  case class NativeRelation(rn:String, sol:Solution, f:(Atom,Solution) => Unit) extends Rel(rn){
    override def notifyObservers(a:Atom)= a match{
      case Atom(this.name, _) =>
        log("[Relation("+name+").notifyObservers] notified by " + a)
        f(a, sol)
//        a.inactivate
        solution.inactivate(a)
        solution.cleanup
      case _ => super.notifyObservers(a)
    }
  }
*/

  class ChamGuard(outerCM:CHAM, sttr:Atom, ruleDefs:List[RuleFactory => Rule]) extends CHAM with Guard{

    val TopRel:Relation = Rel("true")
    val falseRel:Relation = Rel("false")
    val starter = sttr

    initRules(ruleDefs)

    def empty:Boolean = rules.isEmpty

    //Copy relations
    def initRelations(){
      for(r <- outerCM.relations){
        r match{
          case nr:NativeRelation => addRelation(new NativeRelation(nr.name, this.solution, nr.f))
          case _ => addRelation(new LocalRelation(r.name,r.isMultiRel))
        }
      }
//      outerCM.relations.foreach{
//        case NativeRelation(n, _, f) => addRelation(new NativeRelation(n, this.solution, f))
//        case r @ _ => addRelation(new LocalRelation(r.name,r.isMultiRel))
//      }
    }

    def eval(sol:Solution, subs:List[Substitution]):Boolean ={
      log("------------------------------------------------------")
      log("[Guard.eval] Begin " + this.toString)
      log("[Guard.eval] substitutions: " + subs)

      this.solution.revert
      this.solution.update(sol.copy(this))
      levelDown
//      introduceAtom(starter.applySubstitutions(subs))
      this.solution.addAtom(starter.applySubstitutions(subs))
      levelUp

      log("[Guard.eval] finished with solution: " + this.solution)
      log("------------------------------------------------------")
      this.solution.exists(a => a.relName == TopRel.name)
    }

    override def relation(relName:String):Option[Relation] = {
      relations.find(_.name == relName) match{
        case sr:Some[_] => sr
        case _ =>
          val rel = new LocalRelation(relName,false)
          addRelation(rel)
          Some(rel)
      }
    }

    override def toString:String = {
      starter + ":" + rules.mkString("",";","")
    }
  }

//  /*(new Top(atom.vars))*/{
//    starter = new Top(atom.vars)
//    rules(
//      (new Top(atom.vars) &: atom) ==> F
//    )
//  }
}