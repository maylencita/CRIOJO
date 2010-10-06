package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 30, 2010
 * Time: 3:04:17 PM
 * To change this template use File | Settings | File Templates.
 */

import Creole._
import fr.emn.criojo.util.Logger

trait AbstractMachine{
  def solution:Solution
  def rules:List[Rule]
  def relations:List[Relation]

  def addRule(rule:Rule)
  def addRelation(relation:Relation)
  def query(conj:List[Atom], subs:List[Substitution]):List[Atom]

  def introduceAtom(atom:Atom)

  def findRelation(relName:String):Relation = {
    relations.find(_.name == relName) match {
      case Some(r) => r
      case _ => if (relName.startsWith("$")){
                  new LocalRelation(relName,false)
                }else{
                  Logger.log(Logger.WARNING, this.getClass, "findRelation","Undefined relation " + relName)
                  new LocalRelation("Undefined")
                }
    }
  }

  def relation(relName:String):Option[Relation] = relations.find(_.name == relName)

  def newRule(head:List[Atom], body:List[Atom], guard:Guard):Rule={
    var headVars = List[RelVariable]()
    val rule = new CHAMRule(head, body, guard)

//    if (!rule.guard.empty)
//      rule.guard.addRelations(this.relations)

    if (!rule.isAxiom)
      rule.head.foreach{
        case a:rule.HeadAtom =>
          relation(a.relName) match{
            case Some(r) =>
              a.relation = r
              r.addObserver(a)
            case _ =>
              if (!a.relName.startsWith("$")){
                Logger.log(Logger.WARNING, this.getClass, "findRelation","Undefined relation " + a.relName)
                a.relation = new LocalRelation("Undefined")
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
            case _ => Logger.log(Logger.WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
    addRule(rule)
    rule  
  }

  def newRule(head:List[Atom], body:List[Atom]):Rule = newRule(head, body, new Guard)

  def Relation(name:String):Rel = {
    val r = new Rel(name)
//    addRelation(r)
    r
  }

  case class Rel(n:String) extends LocalRelation(n){
    addRelation(this)

    def apply(vars:String*):Atom = new Atom(name, vars.toList.map(v => Variable(v)))

    override def equals(that:Any):Boolean = that match{
      case r:Relation => this.name == r.name
      case _ => false
    }
  }

  class CHAMRule(h:List[Atom], val body:List[Atom], val guard:Guard) extends Rule{//(head, body, guard){
    def this() = this(List(), List(), new Guard)

    val head:List[HeadAtom] = h.map{h => new HeadAtom(h)}

    def execute (subs:List[Substitution]):Boolean = {
      Logger.log("[Rule.execute] {"+this+"} Substitutions: " + subs)
      Logger.log("[Rule.execute] solution= " + solution)
      var matches = List[Atom]()
      var result = false

      if (this.isAxiom ||
              (head.size == 1 && head.filter(_.active).isEmpty) || 
              !{matches= query(head.filter(_.active),subs); matches}.isEmpty){
        Logger.log("[Rule.execute] Found Matches: " + matches + " for rule " + this)
        Logger.levelDown
        val subs2 = subs.union(getSubstitutions(matches))
        if (guard.empty || guard.eval(solution, subs2)){
          Logger.levelUp
          applyReaction(solution, subs2)
          result=true
        }else
          Logger.levelUp
      }

      //Activate atoms that were inactivated but not eliminated
      solution.revert
      result
    }
  }

}