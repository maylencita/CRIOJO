package fr.emn.criojo.ext.expression.ScalaBoolean.operation

import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.core.datatype.{PatternNotMatchingException, Valuation, Expression}

case class NotScalaBoolean(x: ScalaBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    NotScalaBoolean(expr1)
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(!x.getValue())
  }
}

