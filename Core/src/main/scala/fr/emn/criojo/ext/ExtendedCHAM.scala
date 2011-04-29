package fr.emn.criojo.ext

import fr.emn.criojo.core._
import EqClass._

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
abstract class ExtendedCHAM extends CHAM with IntCHAM with StrCHAM with NullCHAM{

  private val x,y = Var

  /**********************************************************************
  * VM definition:
  */
  val Print = Rel("Print") //NativeRelation("Print")(printAtom(_) )

  private val Null_print = NativeRelation("Null_print"){(a,s) => println("Null")}

  rules(
    Print(x) ==> NotNul(x) ? (Int_print(x) &: Str_print(x)),
    Print(x) ==> Nul(x) ? Null_print(x)
  )
  /***********************************************************************/

  def getValue(v:Variable):ValueVariable[_]={
    if (nullVars contains v)
      Null
    else{
      getStrValue(v) getOrElse (getIntValue(v) getOrElse null) match{
        case v2 if(v2 != null) => new Value(v2)
        case _ => NoValue
      }
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

/*
  override def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
    val owner:ExtendedCHAM = this
    new ExtendedCHAM with Guard { //with IntCHAM with StrCHAM with NullCHAM{
      this.eqClasses = owner.eqClasses
      this.disjClasses = owner.disjClasses
      override val nullVars = owner.nullVars
      initRules(ruleDefs)

//      def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = new EmptyGuard
    }
  }
*/

//  override def createGuard(ruleDefs:List[RuleFactory => Rule]):Guard = {
//    val guard = new ExtendedGuard(this)
//    guard.initRules(ruleDefs)
//    guard
//  }

}

//class ExtendedGuard (owner: ExtendedCHAM) extends CHAM with IntCHAM with StrCHAM with NullCHAM with Guard {
//  def this(owner: ExtendedCHAM, sttr:Atom, ruleDefs:(RuleFactory => Rule)*) {
//    this(owner)
//    starter = sttr
//    initRules(ruleDefs.toList)
//  }
//
//  eqClasses = owner.eqClasses
//  override val intEqClasses = owner.intEqClasses
//  override val strEqClasses = owner.strEqClasses
//  override val nullVars = owner.nullVars
//}
