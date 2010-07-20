package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:52:26 PM
 * To change this template use File | Settings | File Templates.
 */

class Variable (n: String){

  def name:String = this.n

  def matches(that:Variable):Boolean = that match{
    case Undef() => true
    case _ => this.equals(that)
  }

  def +(index:Any):Variable = {
     new Variable(this.n+index)
  }

  override def equals(that: Any):Boolean = that match{
    case v:Variable => this.name == v.name
    case _ => false
  }
  override def toString = name
}

case class RelVariable(n:String) extends Variable(n){
  var relation:Relation = _
}


case class Undef() extends Variable("_")