package fr.emn.criojo.ext.expression.ScalaInt.operation

import fr.emn.criojo._
import core.datatype.{PatternNotMatchingException, Expression, Valuation}
import ext.expression.ScalaInt.constructor.WrapScalaInt
import ext.expression.ScalaInt.ScalaInt

case class AddScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 + expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue() + y.getValue())
  }
}

