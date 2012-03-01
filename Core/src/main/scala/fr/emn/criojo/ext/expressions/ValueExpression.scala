package fr.emn.criojo.ext.expressions

import fr.emn.criojo.core.ValueTerm

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 01/03/12
 * Time: 00:21
 */

//Define support for basic operations
abstract class ValueExpression[+T](value: T) extends ValueTerm[T](value) with TerminalExpr {
  def +(that: ValueExpression[Any]): Expression
}

//Basic types... we can add other types with other operations ?
case class IntExpression(num: Int) extends ValueExpression[Int](num) {
  def +(that: ValueExpression[Any]): Expression = that match {
    case IntExpression(n) => new IntExpression(num + n)
    case _ => StrExpr(num + that.toString)
  }
}

case class StrExpr(str: String) extends ValueExpression[String](str) {
  def +(that: ValueExpression[Any]): Expression = StrExpr(str + that.toString)
}
