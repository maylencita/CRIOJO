package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 8, 2010
 * Time: 3:03:16 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import Eq._

import collection.mutable.HashSet

/**
 * Extended CHAM with support for constant values, pretty-print, etc.
 */
//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
abstract class VirtualMachine extends CHAM with IntVM with StringVM
{
  val nullVars:EqClass = HashSet[Variable]()

  /**********************************************************************
  * VM definition:
  */
  val Print = NativeRelation("Print")(printAtom(_) )
  val NullRel = NativeRelation("Null")( addNull(_) )
  /***********************************************************************/

  def execute{
      while (rules.exists(r => r.isAxiom && r.execute)){}
  }

  def newRemoteRelation(remoteName:String,url:String):RemoteRelation

  def prettyPrint:String = {
    solution.filter(! _.relName.startsWith("$")).
            map(a => new Atom(a.relName, a.vars.map(getPrintVariable(_)))).mkString("<",",",">")
  }

  def getValueVariable(x:Variable):ValueVariable[_]={
    getStrValue(x) getOrElse (getIntValue(x) getOrElse null) match{
      case y if(y != null) => new Value(y)
      case _ if (nullVars contains x) => Null
      case _ => NoValue
    }
  }

  def getPrintVariable(v:Variable):Variable = getValueVariable(v) match{
    case value:Value[_] => value
    case NoValue => v
  }

  private def printAtom(a:Atom)= a match{
    case Atom("Print", vars) => println( a.vars.map(v => getPrintVariable(v)).mkString(""," ","") )
    case _ => //Nothing
  }

  private def addNull(a:Atom) = a match{
    case Atom("Null", v::_) =>
      if (nullVars isEmpty){
        eqClasses add nullVars
      }
      nullVars add v
      a.inactivate
      solution.cleanup
    case _ => //Nothing
  }

  override def toString:String = {
    rules.mkString("","\n","")
  }


}