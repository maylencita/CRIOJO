package fr.emn.criojo.core

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

//  def name:String = this.n

//  def matches(that:Variable):Boolean = that match{
//    case Undef => true
//    case _ => this.equals(that)
//  }
  def matches(that:Term) = true
//    that match{
//    case Variable(n) if(n == name) => true
//    case _ => false
//  }

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

//  def apply(vlst:Variable*):Atom = {
//    val at = new Atom(this.name, vlst.toList)
//    at.relation = this.relation
//    at
//  }

  override def toString = if (relation == null) this.name else relation.toString
}

//trait ValueVariable[+T]

//@serializable
//case class Value[+T](value:T) //extends Variable(if (value == null) "_" else value.toString) with ValueVariable[T]{
//  extends Function(if (value == null) "_" else value.toString) with ValueVariable[T]{
//
//  override def equals(that: Any) = that match{
//    case v:Value[_] => this.value == v.value
//    case _ => false
//  }
//
//  override def toString = value match{
//    case s:String => "\"" + s + "\""
//    case _ => value.toString
//  }
//}

object Undef extends Variable("_"){
  //Undef matches anything
  override def matches(that:Term) = true
}