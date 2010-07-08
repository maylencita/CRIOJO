package fr.emn.creole.core

import Creole.Substitution
import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

class VirtualMachine {
  var rules:List[Rule] = List()
  var solution:Solution = new Solution() //Set())
  var relations:List[Relation] = List()

  //TODO Document
  //TODO CREOLEX: Constants
  //TODO CREOLEX: Pretty print
  //TODO Real new variable creation (like with ids and stuff)
  //TODO Replication & multi-relations  NO
  //TODO Error when new variables not declared

  def addRule(rule:Rule){
    rule.solution = this.solution
//    rule.relations = this.relations

    if (!rule.isAxiom)
      rule.head.foreach{
        a => a.relation = relations.find(_.name == a.relName) match {
                  case Some(r) => r.addObserver(rule); r
                  case _ => if (!a.relName.startsWith("$")){
                    println("Undefined relation " + a.relName)
                    null
                  }else{
                    new Relation(a.relName,false)
                  }
             }
      }

    rule.body.foreach{a =>  a.relation = relations.find(_.name == a.relName) match {
        case Some(r) => r
        case _ => if (!a.relName.startsWith("$")){
                    println("Undefined relation " + a.relName)
                    null
                  }else{
                    new Relation(a.relName,false)
                  }
      } ;
      a.vars.foreach{
        case rv: RelVariable => rv.relation = relations.find(_.name == rv.name) match{
          case Some(r) => r
          case _ => println("Undefined relation variable " + rv.name); null //TODO Handle this
        }
        case _ => //skip
      }
    }

    rules :+= rule
//    rule.execute
  }

  def addRelation(relation:Relation){
    relations :+= relation
  }

  def execute{
    var continue = true
//    while(continue)
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  override def toString:String = {
    rules.mkString("","\n","")
  }
}