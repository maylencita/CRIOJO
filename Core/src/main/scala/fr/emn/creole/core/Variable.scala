package fr.emn.creole.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 9, 2010
 * Time: 5:52:26 PM
 * To change this template use File | Settings | File Templates.
 */

object Variable{
  def apply(n: String):Variable = new Variable(n)  
}

class Variable (n: String){

  def name:String = this.n

  def matches(that:Variable):Boolean = that match{
    case Undef => true
    case _ => this.equals(that)
  }

  def +(index:Any):Variable = {
     new Variable(this.n+index)
  }

  override def hashCode = this.name.hashCode

  override def equals(that: Any):Boolean = that match{
    case v:Variable => this.name == v.name
    case _ => false
  }
  override def toString = name
}

case class RelVariable(n:String) extends Variable(n){
  var relation:Relation = _ //TODO Initialize in constructor -> make method CHAM.newRelation()
}

trait ValueVariable[+T]

case class Value[+T](value:T) extends Variable(if (value == null) "_" else value.toString) with ValueVariable[T]{

  override def equals(that: Any) = that match{
    case v:Value[_] => this.value == v.value
    case _ => false
  }

  override def toString = value match{
    case s:String => "\"" + s + "\""
    case _ => value.toString
  }
}

//TODO Why not extends ValueVariable[Nothing]? 
object Null extends Value[Any](null){
  override def toString = "null"
}

object NoValue extends ValueVariable[Nothing]

object Undef extends Variable("_")