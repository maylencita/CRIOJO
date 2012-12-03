package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Valuation, Expression}

case class WrapScalaInt(i: Int) extends ScalaInt {
  override def value = i

  override def getValuation(expr: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def matches(expr: Expression): Boolean = expr match {
    case WrapScalaInt(j) if (i == j) => true
    case _ => false
  }

  override def toString: String = value.toString
}
