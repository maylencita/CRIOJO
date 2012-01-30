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

abstract class CHAM {//extends RuleFactory{
  this:Engine =>

  protected var index = 0

//  val T = new Top(){ def apply(vlst:Variable*):Atom = new Top(vlst.toList) }
//  val F = Atom("false")

//  protected var rules:List[Rule] = List()
//  var relations:List[Relation] = List()

//  val solution:Solution = Solution(this)

//  def Guard(sttr:Atom, ruleDefs:(RuleFactory => Rule)*):Guard = new ChamGuard(this, sttr, ruleDefs.toList)
//  def Abs(atom:Atom):Guard = new ChamGuard(this, new Top(atom.terms), List((new Top(atom.terms) &: atom) ==> F))

//  def addRelation(relation:Relation) { relations :+= relation }
//
//  def addRule(rule:Rule) { rules :+= rule }

//  def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = solution.findMatches(conj, subs)

  /**
   * Gets a relation with name "relName", if found.
   * Returns a new relation named "Undefined" if no relation is found.
   */
  def getRelation(relName:String):Relation = findRelation(relName) match{
      case Some(r) => r
      case _ =>
        if(relName startsWith ("$"))
          new LocalRelation(relName)
        else{
          log(WARNING, this.getClass, "findRelation","Undefined relation " + relName)
          new LocalRelation("Undefined")
        }
  }

//  def executeRules(){
//      while (rules.exists(r => r.isAxiom && r.executeRules)){}
//  }

  def newLocalRelation(name:String,public:Boolean):LocalRelation = {
    new LocalRelation(name, public)
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation = {
    throw new IllegalAccessException("Unimplemented method newRemoteRelation.")
  }

//  def introduceAtom(atom:Atom){
//    solution.addAtom(atom)
//  }

//  def receiveUpdate(atom:Atom){
//    getRelation(atom.relName) match{
//      case r:Relation =>
//        atom.relation = r
//        r.notifyObservers(atom)
//      case _ => //skip
//    }
//  }

//  def querySansEffect(conj:List[Atom]):List[Atom] = {
//    val lstresult= query(conj, conj.flatMap(a=>a.vars.zip(a.terms)).filterNot(p => p._1 == Undef))
//    solution.revert
//    lstresult
//  }

//  def createRule(h:Head,b:Body,g:Guard,scope:List[Variable]):Rule = new CHAMRule(h, b, g, scope)

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{ f => initRule(f)  }
  }

  def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    processRuleBody(processRuleHead(rule), rule)
    addRule(rule)
  }

//  def newRule(head:List[Atom], body:List[Atom]):Rule = newRule(head, body, EmptyGuard)

//  def newRule(head:List[Atom], body:List[Atom], guard:Guard):Rule={
//    var headVars = List[RelVariable]()
//    val rule = createRule(head,body,guard)
//
//    if (!rule.isAxiom)
//      rule.head.foreach{
//        case a:rule.HeadAtom =>
//          relation(a.relName) match{
//            case Some(r) =>
//              a.relation = r
//              r.addObserver(a)
//            case _ =>
//              //TODO Improve this. AbstractMachine should not be aware of values
//              if (!a.relName.startsWith("$")){
//                log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
//                a.relation = new LocalRelation("Undefined")
//              }else{
//                a.relation = findRelation(a.relName)
//              }
//          }
//          a.terms.foreach{
//            case rv:RelVariable => headVars :+= rv
//            case _ =>
//          }
//        case _ =>
//      }
//
//    rule.body.foreach{a =>
//      a.relation = headVars.find(hv => hv.name == a.relName) match{
//        case Some(hv) => hv.relation
//        case _=> findRelation(a.relName)
//      }
//      a.terms.foreach{
//        case rv: RelVariable if(!headVars.contains(rv)) =>
//          relations.find(_.name == rv.name) match{
//            case Some(r) => rv.relation = r
//            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
//          }
//        case _ =>
//      }
//    }
//    addRule(rule)
//    rule
//  }

  def processRuleHead(rule:Rule):List[RelVariable]={
    var headVars = List[RelVariable]()

    if (!rule.isAxiom)
      rule.head.foreach{a =>
        findRelation(a.relName) match{
          case Some(r) => r.addObserver(rule)
          case _ =>
            log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
            a.relation = new LocalRelation("Undefined")
        }

//        case a:rule.HeadAtom =>
//          findRelation(a.relName) match{
//            case Some(r) =>
//              a.relation = r
//              r.addObserver(a)
//            case _ =>
//                log(WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
//                a.relation = new LocalRelation("Undefined")
//          }
          a.terms.foreach{
            case rv:RelVariable => headVars :+= rv
            case _ =>
          }
//        case _ =>
      }
    headVars
  }

  def processRuleBody(headVars:List[RelVariable], rule:Rule){
    rule.body.foreach{a =>
      a.relation = headVars.find(hv => hv.name == a.relName) match{
        case Some(hv) => hv.relation
        case _=> getRelation(a.relName)
      }
      a.terms.foreach{
        case rv: RelVariable if(!headVars.contains(rv)) =>
          findRelation(rv.name) match{
            case Some(r) => rv.relation = r
            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
  }

  def printRules: String = rules.mkString("","\n","")

//  class CHAMRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{
//    def this() = this(List(), List(), EmptyGuard, List())
//
//    scope = scp
//    val head:List[HeadAtom] = h.map{h => new HeadAtom(h)}
//
//    def notifyCham(atom:Atom){
//      if (atom.relation != null)
//        atom.relation.notifyObservers(atom)
//      else
//        findRelation(atom.relName) match{
//          case Some(relation) =>
//            relation.notifyObservers(atom)
//          case _ => log(WARNING, this.getClass, "notifyCham", "Undefined relation " + atom.relName)
//        }
//    }
//
//    def executeRules (subs:List[Substitution]):Boolean = {
//      var matches = List[Atom]()
//      var result = false
//
//      if (this.isAxiom ||
//              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
//              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
//        log("[Rule.executeRules] {"+this+"} Substitutions: " + subs)
////        log("[Rule.executeRules] solution= " + solution)
////        log("[Rule.executeRules] Found Matches: " + matches + " for rule " + this)
//        levelDown
//        val subs2 = subs.union(getHeadSubstitutions(matches))
//        if (guard.empty || guard.eval(solution, subs2)){
//          levelUp
//          applyReaction(solution, subs2)
//          result=true
//        }else
//          levelUp
//      }
//
//      //Activate atoms that were inactivated but not eliminated
//      solution.revert
//      result
//    }
//  }

//  class ChamGuard(outerCM:CHAM, sttr:Atom, ruleDefs:List[RuleFactory => Rule]) extends CHAM with Guard{
//
//    val TopRel:Relation = new LocalRelation("true")
//    val falseRel:Relation = new LocalRelation("false")
//    val starter = sttr
//
//    initRules(ruleDefs)
//
//    def empty:Boolean = rules.isEmpty
//
//    //Copy relations from external CHAM
//    def initRelations(){
//      for(r <- outerCM.relations){
//        r match{
//          case nr:NativeRelation => addRelation(new NativeRelation(nr.name, this.solution, nr.f))
//          case _ => addRelation(new LocalRelation(r.name,r.isMultiRel))
//        }
//      }
//    }
//
//    def eval(sol:Solution, subs:List[Substitution]):Boolean ={
//      log("------------------------------------------------------")
//      log("[Guard.eval] Begin " + this.toString)
//      log("[Guard.eval] substitutions: " + subs)
//
//      this.solution.revert
//      this.solution.update(sol.copy(this))
//      levelDown
//      this.solution.addAtom(starter.applySubstitutions(subs))
//      levelUp
//
//      log("[Guard.eval] finished with solution: " + this.solution)
//      log("------------------------------------------------------")
//      this.solution.exists(a => a.relName == TopRel.name)
//    }
//
//    override def findRelation(relName:String):Option[Relation] = {
//      relations.find(_.name == relName) match{
//        case sr:Some[_] => sr
//        case _ =>
//          val rel = new LocalRelation(relName,false)
//          addRelation(rel)
//          Some(rel)
//      }
//    }
//
//    override def toString:String = {
//      starter + ":" + rules.mkString("",";","")
//    }
//  }

}