package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 17/11/11
 * Time: 17:18
 */

import fr.emn.criojo.util.Logger._
import fr.emn.criojo.ext.expression.Relation.constructor.{LocalRelation, OutChannel}
import fr.emn.criojo.ext.expression.Relation.{Relation}
import fr.emn.criojo.ext.debug.Solution

trait Engine extends RuleFactory{
  protected var rules:List[Rule] = List()
  protected var relations:List[Relation] = List()

  def reactionStrategy:ReactionStrategy

  def executeRules()
  def introduceAtom(atom:Atom)

  def notifyRelationObservers(atom: Atom){
    if (atom.relation != null && atom.relation.isInstanceOf[LocalRelation])
      atom.relation.notifyObservers(atom)
    else
      findRelation(atom.relation.name) match {
        case Some(relation) => relation.notifyObservers(atom)
        case _ => log(WARNING, this.getClass, "notifyRelationObservers", "Undefined relation " + atom.relation.name)
      }
  }

  def addRelation(relation:Relation) { relations :+= relation }
  def addRule(rule:Rule) {
    
    rule.body.foreach(a => addRelation(a.relation))
    rule.head.foreach(a => addRelation(a.relation))

    rules :+= rule
  }

  /**
   * Searches for a relation. If found, Some[Relation] is returned.
   * Otherwise, it will return Nothing
   */
  def findRelation(relName:String):Option[Relation] = relations.find(_.name == relName)

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

  def initRules(ruleDefs:List[RuleFactory => Rule]){
    ruleDefs.foreach{ f => initRule(f)  }
  }

  def initRule(rf: RuleFactory => Rule){
    val rule = rf(this)
    processRuleHead(rule)
    processRuleBody(rule)
    addRule(rule)
  }

  def processRuleHead(rule:Rule) {
    if (!rule.isAxiom)
      rule.head.foreach{ a => a.relation.addObserver(rule)}
  }

  def processRuleBody(rule:Rule)  {
    rule.guard match{
      case cg:CriojoGuard =>
        cg.observed.foreach{ relation => relation.addObserver(cg) }
      case _ =>
    }
    rule.body.foreach{a =>
//--- Normaly, not necessary
//      a.relation = headVars.find(hv => hv.name == a.relName) match{
//        case Some(hv) => hv.relation
//        case _=> getRelation(a.relName)
//      }
//      a.patterns.foreach{
//        case rv: VarChannel if(!headVars.contains(rv)) =>
//          findRelation(rv.name) match{
//            case Some(r) => rv.relation = r
//            case _ => log(WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
//          }
//        case _ =>
//      }
    }
  }

  def printRules: String = rules.mkString("","\n","")
}

