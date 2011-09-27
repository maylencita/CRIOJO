package fr.emn.criojo.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 21/01/11
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.criojo.core._
import EqClass._

import collection.mutable.HashSet

trait NullCHAM extends /*CHAM with*/ EqCHAM{
  val nullVars:EqClass = HashSet[Variable]()
  private val x,y = Var

  /**********************************************************************
  * VM definition:
  */
  private val f = new RelVariable("false")
  private val t = new RelVariable("true")

  val NullRel = NativeRelation("Null")( addNull )
  val Null_ask = NativeRelation("Null_ask")(askNull)

  /***********************************************************************/

  private def addNull(a:Atom, s:Solution) = a match{
    case Atom("Null", v::_) =>
      if (nullVars isEmpty){
        eqClasses add nullVars
      }
      nullVars add v
//      a.inactivate
      s.inactivate(a)
      s.cleanup
    case _ => //Nothing
  }

  private def askNull(a:Atom, s:Solution) = a match{
    case Atom(_, v::kplus::kminus::_) =>
      if (nullVars contains (v))
        s.addAtom(Atom(kplus.name, v))
      else
        s.addAtom(Atom(kminus.name, v))
    case _ =>
  }

  def NotNul(variable:Variable):Guard = {
//    val g = new NullGuard (this, T(variable), T(x) ==> Null_ask(x, f, t))
//    g
    Guard (T(variable), T(x) ==> Abs(Null_ask())? Null_ask(x, f, t))
  }

  def Nul(variable:Variable):Guard = {
    Guard (T(variable), T(x) ==> Abs(Null_ask())? Null_ask(x, t, f))
  }

}

//class NullGuard(owner:NullCHAM, sttr:Atom, ruleDefs:(RuleFactory => Rule)*) extends Guard(sttr) with NullCHAM{
//  this.eqClasses = owner.eqClasses
//  override val nullVars = owner.nullVars
//  initRules(ruleDefs.toList)
//}