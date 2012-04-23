package fr.emn.criojo.ext.expression.ScalaString.operation

import fr.emn.criojo.core.{Valuation, Expression, PatternNotMatchingException}
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaString.ScalaString

case class EqualScalaString(x: ScalaString, y: ScalaString) extends ScalaBoolean {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: EqualScalaString => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 Equal expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue() == y.getValue())
  }

  override def matches(expr: Expression): Boolean = expr match {
    case expr: EqualScalaString => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

