package fr.emn.criojo.ext.expression.ScalaString.operation

import fr.emn.criojo.ext.expression.ScalaString.ScalaString
import fr.emn.criojo.core.{PatternNotMatchingException, Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString

case class AddScalaString(x: ScalaString, y: ScalaString) extends ScalaString {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: AddScalaString => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 + expr2
  }

  override def reduce(): Expression = {
    WrapScalaString(x.getValue() + y.getValue())
  }

  override def matches(expr:Expression):Boolean =  expr match {
    case expr: AddScalaString => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

