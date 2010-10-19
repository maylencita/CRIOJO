package fr.emn.criojo.ext

import fr.emn.criojo.core._
import EqVM._

import collection.mutable.HashSet

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 15, 2010
 * Time: 11:27:15 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Extended CHAM with support for constant values, pretty-print, etc.
 */
//TODO Add other CHAM traits.. for example: with NumberCHAM, DateCHAM...
trait ExtendedCHAM extends CHAM with IntVM with StringVM{

  val nullVars:EqClass = HashSet[Variable]()

  /**********************************************************************
  * VM definition:
  */
  val Print = NativeRelation("Print")(printAtom(_) )
  val NullRel = NativeRelation("Null")( addNull(_) )
  /***********************************************************************/

  def getValue(x:Variable):ValueVariable[_]={
    getStrValue(x) getOrElse (getIntValue(x) getOrElse null) match{
      case y if(y != null) => new Value(y)
      case _ if (nullVars contains x) => Null
      case _ => NoValue
    }
  }

  def getPrintVariable(v:Variable):Variable = getValue(v) match{
    case value:Value[_] => value
    case NoValue => v
  }

  def prettyPrint:String = {
    solution.filter(! _.relName.startsWith("$")).
            map(a => new Atom(a.relName, a.vars.map(getPrintVariable(_)))).mkString("<",",",">")
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

  override def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
    val guard = new ExtendedGuard(this)
    guard.initRules(ruleDefs)
    guard
  }

}

class ExtendedGuard (outherVM: ExtendedCHAM) extends Guard with ExtendedCHAM{
  eqClasses = outherVM.eqClasses
  override val intEqClasses = outherVM.intEqClasses
  override val strEqClasses = outherVM.strEqClasses
}
