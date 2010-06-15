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

  //TODO Real parallel execution
  //TODO Sequential execution
  //TODO Replication & multi-relations
  //TODO Second order relations
  //TODO Error when new variables not declared

  def addRule(rule:Rule){
    rule.solution = this.solution
    rule.relations = this.relations

    rule.head.foreach{
      a => relations.find(_.name == a.relName) match {
        case Some(r) => r.addObserver(rule)
        case _ => println("Undefined relation " + a.relName)
      }
    }

    rules :+= rule
    rule.execute
  }

  def addRelation(relation:Relation){
    relations :+= relation
  }

}