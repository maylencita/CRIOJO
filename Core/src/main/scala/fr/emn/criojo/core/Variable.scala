package fr.emn.criojo.core

import Criojo.Valuation

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:52:26 PM
 * To change this template use File | Settings | File Templates.
 */

//object Variable{
//  def apply(n: String):Variable = new Variable(n)
//}

@serializable
case class Variable (name: String) extends Term{

  def applyValuation(valuation:Valuation):Term = {
    //TODO Implement
    this
  }

  def matches(that:Term) = true

  def +(index:Any):IdTerm = {
     new IdTerm(this.name+index)
  }

  override def hashCode = this.name.hashCode

  override def equals(that: Any):Boolean = that match{
    case v:Variable => this.name == v.name
    case _ => false
  }
  override def toString = name

  def toInt:Int = toString.toInt
}

object RelVariable{
  def apply(r:Relation):RelVariable = {
    val rv = new RelVariable(r.name)
    rv.relation = r
    rv
  }
}

@serializable
class RelVariable(name:String) extends Variable(name){
  @transient
  var relation:Relation = _ //TODO Initialize in constructor -> make method CHAM.newRelation()

  override def toString = if (relation == null) this.name else relation.toString
}

object Undef extends Variable("_"){
  //Undef matches anything
  override def matches(that:Term) = true
}