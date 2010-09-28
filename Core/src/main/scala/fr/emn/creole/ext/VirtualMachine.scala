package fr.emn.creole.ext

import fr.emn.creole.core._ 
import Creole.Substitution
import fr.emn.creole.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.util.Logger._

//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
class VirtualMachine extends StandardCHAM with Eq with Number
{
//  this.solution = new StandardSolution()
  
//  var rules:List[Rule] = List()
//  var solution:Solution = new Solution() //Set())
//  var relations:List[Relation] = List()

  //TODO Document
  //TODO Add URL to variables
  //TODO Replication & multi-relations  NO
  //TODO Error when new variables not declared

  override def introduceAtom(atom:Atom)= atom match{
    case Atom(EQ, vlst) if (vlst.size == 2) => addEquivalence(vlst(0),vlst(1))
    case _ => processValues(atom) foreach{super.introduceAtom(_)}
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