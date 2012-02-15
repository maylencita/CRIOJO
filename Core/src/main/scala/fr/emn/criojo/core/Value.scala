package fr.emn.criojo.core

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 07/11/11
 * Time: 15:19
 */

// TODO: check if it is possible to remove the covariant aspect: It is useful to modify the value of a variable!
trait Value[+T] {

  def getValue():T = {return null.asInstanceOf[T]}
}

case class ValueTerm[+T](value:T) //extends Variable(if (value == null) "_" else value.toString) with ValueVariable[T]{
  extends Function(if (value == null) "_" else value.toString, List[Term]()) with Value[T]{

  override def apply(params: List[Term]) = new ValueTerm(value)

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

    return value
  }
  /*
  def setValue(newValue:T) {
    value = newValue
  }
  */
}

case class MutableValueTerm[T](var value:T) //extends Variable(if (value == null) "_" else value.toString) with ValueVariable[T]{
  extends Function(if (value == null) "_" else value.toString, List[Term]()) with Value[T]{

  override def apply(params: List[Term]) = new ValueTerm(value)

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

    return value
  }

  def setValue(newValue:T) {
    value = newValue
  }
}

object NoValue extends Value[Nothing]

object NullVal extends ValueTerm[Any](null){
  override def toString = "null"
}
