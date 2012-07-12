package fr.emn.criojo.ext.expression.ScalaBoolean.operation

import fr.emn.criojo._
import core.datatype.{Expression, PatternNotMatchingException, Valuation}
import ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean

case class AndScalaBoolean(x: ScalaBoolean, y: ScalaBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 && expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue() && y.getValue())
  }
}

