package fr.emn.creole.ext

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 23, 2010
 * Time: 2:08:51 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.core.Creole._

abstract class TypedAtom[T](relName:String, value:T, val variable:Variable) extends Atom(relName, List(variable)){
  def unapply(ta:TypedAtom[_]) = ta match{
    case StringAtom(sval, strVar) => Option((sval, strVar))
    case IntAtom(num, intVar) => Option((num, intVar))
    case NullAtom(v) => Option((null, v))
    case _ => None
  }
}

case class StringAtom(sval:String, strVar:Variable) extends TypedAtom("$str_"+sval, sval, strVar){

  def str:String = this.sval

  override def clone:Atom = {
    val a = new StringAtom(str,variable)
    a.active = this.active
    a.relation = this.relation
    a
  }

  override def applySubstitutions(subs:List[Substitution]):Atom = {
    this
  }
}

case class IntAtom(num:Int, intVar:Variable) extends TypedAtom("$int_"+num, num, intVar){
  def number:Int = this.num

  override def clone:Atom = {
    val a = new IntAtom(number,variable)
    a.active = this.active
    a.relation = this.relation
    a
  }

  override def applySubstitutions(subs:List[Substitution]):Atom = {
    def replace(variable:Variable):Variable = {
      subs.find(s => s._1.name == variable.name) match{
        case Some(nv) => nv._2
        case _ => Undef
      }
    }

    this
  }
}

case class NullAtom(v:Variable) extends TypedAtom("Null", null, v){
  override def clone:Atom = {
    val a = new NullAtom(v)
    a.active = this.active
    a.relation = this.relation
    a
  }

  override def applySubstitutions(subs:List[Substitution]):Atom = this
}