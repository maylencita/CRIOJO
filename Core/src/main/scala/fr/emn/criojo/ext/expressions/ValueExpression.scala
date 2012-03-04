package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.{Term, ValueTerm}


/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 01/03/12
 * Time: 00:21
 */

//Define support for basic operations
abstract class ValueExpression[+T](value: T) extends ValueTerm[T](value) with TerminalExpr {

}

//Basic types... we can add other types with other operations ?
case class IntExpression(num: Int) extends ValueExpression[Int](num) {
  override def add(that: Term): Expression = that match {
    case IntExpression(n) => new IntExpression(num + n)
    case _ => StrExpression(num + that.toString)
  }

  override def sub(that: Term): Expression = that match {
    case IntExpression(n) => new IntExpression(num - n)
    case _ => StrExpression(num + that.toString)
  }

  override def div(that: Term): Expression = that match {
    case IntExpression(n) => new IntExpression(num / n)
  }

  override def mult(that: Term): Expression = that match {
    case IntExpression(n) => new IntExpression(num * n)
  }

  override def isEqual(that: Term): Expression = that match {
    case IntExpression(n) => new BooleanExpression(num == n)
  }

  override def greaterThan(that: Term): Expression = that match {
    case IntExpression(n) => new BooleanExpression(num > n)
  }

  override def lessThan(that: Term): Expression = that match {
    case IntExpression(n) => new BooleanExpression(num < n)
  }
}

case class StrExpression(str: String) extends ValueExpression[String](str) {
  override def add(that: Term): Expression = StrExpression(str + that.toString)
}

case class BooleanExpression(bool: Boolean) extends ValueExpression[Boolean](bool) {
  override def add(that: Term): Expression = that match {
    case BooleanExpression(b) => new BooleanExpression(b || bool)
    case IntExpression(n) => new BooleanExpression(bool || n>0)
    case _ => StrExpression(bool + that.toString)
  }
}