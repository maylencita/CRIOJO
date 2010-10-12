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

/**
 * Extended CHAM with support for constant values, pretty-print, etc.
 */
//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
abstract class VirtualMachine extends CHAM with IntVM with StringVM/*with Eq with ValueVM*/
{
  //Initialize Print
  val print = Print

  //TODO Document
  //TODO Add URL to variables
  //TODO Replication & multi-relations  NO
  //TODO Error when new variables not declared

  def execute{
    var continue = true
//    while(continue)
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation

  def prettyPrint:String = solution.toString
  
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