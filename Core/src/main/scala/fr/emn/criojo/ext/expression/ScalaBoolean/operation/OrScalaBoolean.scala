package fr.emn.criojo.ext.expression.ScalaBoolean.operation

import fr.emn.criojo.core.{Expression, Valuation}
import fr.emn.criojo._
import ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.core.PatternNotMatchingException
import ext.expression.ScalaBoolean.constructor.WrapScalaBoolean

case class OrScalaBoolean(x: ScalaBoolean, y: ScalaBoolean) extends ScalaBoolean {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: OrScalaBoolean => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 || expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue() || y.getValue())
  }

  override def matches(expr: Expression): Boolean = expr match {
    case expr: OrScalaBoolean => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

