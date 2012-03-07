package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/11/11
 * Time: 15:19
 */


trait Value[+T] {

  def getValue():T = {null.asInstanceOf[T]}
}

case class ValueTerm[+T](value:T) extends Term with Value[T]{

  def name = value.toString

  //Values are constants so: c[x/y] = c
  def applyValuation(valuation:Valuation):Term = this

  override def matches(that:Term) = that match{
    case ValueTerm(n) if(n == value) => true
    case _ => false
  }

  override def equals(that: Any) = that match{
    case v:ValueTerm[_] => this.value == v.value
    case _ => false
  }

  override def toString = value match{
    case s:String => "\"" + s + "\""
    case _ => value.toString
  }

  override def getValue():T = {
    value
  }
}

object NoValue extends Value[Nothing]

object NullVal extends ValueTerm[Any](null){
  override def toString = "null"
}
