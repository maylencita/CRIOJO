package fr.emn.criojo.ext.expression.ScalaString.operation

import fr.emn.criojo.ext.expression.ScalaString.ScalaString
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.core.datatype.{Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

case class LengthScalaString(s: ScalaString) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr = s.applyValuation(valuation).asInstanceOf[ScalaString]

    expr.length
  }

  override def reduce(): Expression = {
    WrapScalaInt(s.getValue.length)
  }
}
