package fr.emn.criojo.ext.expression.ScalaBoolean.operation

import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.core.{PatternNotMatchingException, Valuation, Expression}

case class NotScalaBoolean(x: ScalaBoolean) extends ScalaBoolean {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: NotScalaBoolean => x.getValuation(expr.x)
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    NotScalaBoolean(expr1)
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(!x.getValue())
  }

  override def matches(expr: Expression): Boolean = expr match {
    case expr: NotScalaBoolean => x.matches(expr.x)
    case _ => false
  }
}

