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

trait AbstractMachine extends RuleFactory{
  protected var index = 0

  def solution:Solution
  def rules:List[Rule]
  def relations:List[Relation]

  def addRule(rule:Rule)
  def addRelation(relation:Relation)
  def query(conj:List[Atom], subs:List[Substitution]):List[Atom]

  def introduceAtom(atom:Atom)

  def findRelation(relName:String):Relation = relation(relName) match{
      case Some(r) => r
      case _ =>
        if(relName startsWith ("$"))
          new LocalRelation(relName)
        else{
          Logger.log(Logger.WARNING, this.getClass, "findRelation","Undefined relation " + relName)
          new LocalRelation("Undefined")          
        }
  }

  def relation(relName:String):Option[Relation] = relations.find(_.name == relName)

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
                Logger.log(Logger.WARNING, this.getClass, "addRule","Undefined relation " + a.relName)
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
            case _ => Logger.log(Logger.WARNING, this.getClass, "addRule", "Undefined relation variable " + rv.name);
          }
        case _ =>
      }
    }
    addRule(rule)
    rule  
  }

  def newRule(head:List[Atom], body:List[Atom]):Rule = newRule(head, body, new Guard)

}