package fr.emn.criojo.ext.expression.ScalaInt.operation

import fr.emn.criojo.core.datatype.{PatternNotMatchingException, Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

case class GreaterThanScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaBoolean {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: GreaterThanScalaInt =>
      x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ =>
      throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 > expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue() > y.getValue())
  }

  override def matches(expr: Expression): Boolean = expr match {
    case expr: GreaterThanScalaInt => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

