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

class VirtualMachine extends CHAM with StandardCHAM{
//  var rules:List[Rule] = List()
//  var solution:Solution = new Solution() //Set())
//  var relations:List[Relation] = List()

  //TODO Document
  //TODO CREOLEX: Constants
  //TODO CREOLEX: Pretty print
  //TODO Real new variable creation (like with ids and stuff)
  //TODO Replication & multi-relations  NO
  //TODO Error when new variables not declared

  def execute{
    var continue = true
//    while(continue)
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  override def toString:String = {
    rules.mkString("","\n","")
  }
}