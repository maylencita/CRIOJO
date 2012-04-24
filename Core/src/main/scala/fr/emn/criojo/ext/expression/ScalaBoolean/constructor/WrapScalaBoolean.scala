package fr.emn.criojo.ext.expression.ScalaBoolean.constructor

import fr.emn.criojo.core.datatype.{Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

case class WrapScalaBoolean(b: Boolean) extends ScalaBoolean {
  override def value = b;

  override def getValuation(expr: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def matches(expr: Expression): Boolean = expr match {
    case WrapScalaBoolean(j) if (b == j) => true
    case _ => false
  }
}
