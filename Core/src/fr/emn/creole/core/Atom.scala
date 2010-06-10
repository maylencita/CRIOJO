package fr.emn.creole.core

import Creole.Substitution
/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:47:03 PM
 * To change this template use File | Settings | File Templates.
 */

class Atom (val relName:String, val vars: List[Variable]) {

  var active:Boolean = true

  def isTrue:Boolean = relName == "true"

  def arity = vars.size

  def applySubstitutions(subs:List[Substitution]):Atom = {
    def replace(variable:Variable):Variable = {
      val newVar = subs.find(s => s._1.name == variable.name)
      newVar match{
        case Some(nv) => nv._2
        case _ => Undef()
      }
    }

    val newVars = this.vars.map(v => replace(v))
    new Atom(this.relName, newVars)
  }

  def matches(that: Atom) : Boolean = {
    this.relName == that.relName &&
    this.arity == that.arity &&
    this.vars.zip(that.vars).forall(p => p._1.matches(p._2))
  }

  override def equals(that: Any):Boolean = that match{
    case a:Atom => this.relName == a.relName //&& this.vars. == a.vars
    case _ => false
  }

  override def toString =
    relName + vars.mkString("(",",",")")

}
