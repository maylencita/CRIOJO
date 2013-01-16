package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{WrappedValue, Valuation, Expression}
import fr.emn.criojo.expression.CriojoBoolean

case class WrapScalaBoolean(b: Boolean) extends ScalaBoolean with WrappedValue[Boolean]{
  val  self = b
  override def value = b

  override def getValuation(expr: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def matches(expr: Expression): Boolean = expr match {
    case WrapScalaBoolean(j) if (b == j) => true
    case _ => false
  }
}

