package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 8, 2010
 * Time: 11:15:43 AM
 * To change this template use File | Settings | File Templates.
 */

import Creole._
import fr.emn.criojo.util.Logger._

abstract class CHAM extends AbstractMachine {

  val T = new Top(){ def apply(vlst:Variable*):Atom = new Top(vlst.toList) } //Atom("true") //Rel("true")
  val F = Atom("false") //Rel("false")

//  val solution = Solution() //new Solution
  var rules:List[Rule] = List()
  var relations:List[Relation] = List()

  def introduceAtom(atom:Atom){
    solution.addAtom(atom)
    findRelation(atom.relName) match{
      case r:Relation =>
        atom.relation = r
        r.notifyObservers(atom)
      case _ => //skip
    }
  }

  def addRelation(relation:Relation) = relations :+= relation

  def addRule(rule:Rule) = rules :+= rule

  def query(conj:List[Atom], subs:List[Substitution]):List[Atom] = solution.findMatches(conj, subs)

  implicit def atomToConjunction(a:Atom):Conjunction = new &:(a, Empty)

  def createRule(h:Head,b:Body,g:Guard,scope:List[Variable]):Rule = new CHAMRule(h, b, g, scope)

  def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
    val guard = new Guard
    guard.initRules(ruleDefs)
    guard
  }

  def rules(ruleDefs:(RuleFactory => Rule)*) = initRules(ruleDefs.toList)

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{f =>
      val r = f(this)
      processBody(processHead(r),r)
      addRule(r)
    }
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

  class CHAMRule(h:List[Atom], val body:List[Atom], val guard:Guard, scp:List[Variable]) extends Rule{//(head, body, guard){
    def this() = this(List(), List(), new Guard, List())

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
      log("[Rule.execute] {"+this+"} Substitutions: " + subs)
      log("[Rule.execute] solution= " + solution)
      var matches = List[Atom]()
      var result = false

      if (this.isAxiom ||
              (head.size == 1 && head.filter(_.isActive).isEmpty) ||
              !{matches= query(head.filter(_.isActive),subs); matches}.isEmpty){
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

  @serializable
  case class Rel(n:String) extends LocalRelation(n,true){
    addRelation(this)

    def apply(vars:Variable*):Atom = new Atom(name, vars.toList)

    override def equals(that:Any):Boolean = that match{
      case r:Relation => this.name == r.name
      case _ => false
    }
  }

  object NativeRelation{
    def apply(n:String)(f:(Atom) => Unit)=new NativeRelation(n,f)
  }
  @serializable
  class NativeRelation(n:String, f:(Atom) => Unit) extends Rel(n){
    override def notifyObservers(a:Atom)= a match{
      case Atom(this.name, _) =>
        log("[Relation("+name+").notifyObservers] notified by " + a)
        f(a)
//        a.inactivate
        solution.inactivate(a)
        solution.cleanup
      case _ => super.notifyObservers(a)
    }
  }
  
}