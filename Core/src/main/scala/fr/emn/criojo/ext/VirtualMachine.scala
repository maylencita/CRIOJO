package fr.emn.criojo.ext

import fr.emn.criojo.core._
import Creole.Substitution
import fr.emn.criojo.util.Logger

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.util.Logger._
import java.net.URI

//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
abstract class VirtualMachine extends CHAM with ValueVM with Eq //with Number
{
  //Initialize Print
  val print = Print

//  this.solution = new StandardSolution()
  
//  var rules:List[Rule] = List()
//  var solution:Solution = new Solution() //Set())
//  var relations:List[Relation] = List()

  //TODO Document
  //TODO Add URL to variables
  //TODO Replication & multi-relations  NO
  //TODO Error when new variables not declared

//  override def introduceAtom(atom:Atom)= atom match{
//    case Atom(EQ, vlst) if (vlst.size == 2) => addEquivalence(vlst(0),vlst(1))
//    case _ => processValues(atom) foreach{super.introduceAtom(_)}
//  }

  def execute{
    var continue = true
//    while(continue)
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation

  override def toString:String = {
    rules.mkString("","\n","")
  }

  object Print extends Rel("Print"){
    override def notifyObservers(a:Atom)= a match{
      case Atom("Print", vars) => println(a.relName +"(" + a.vars.mkString("",",","") + ")")
      case _ => super.notifyObservers(a)
    }
  }

}