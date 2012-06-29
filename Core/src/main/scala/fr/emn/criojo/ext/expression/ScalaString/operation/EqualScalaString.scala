package fr.emn.criojo.ext.expression.ScalaString.operation

import fr.emn.criojo.core.datatype.{Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaString.ScalaString

case class EqualScalaString(x: ScalaString, y: ScalaString) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 <=> expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue == y.getValue)
  }
}

