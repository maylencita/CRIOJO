package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Valuation, Expression}

case class WrapScalaString(s: String) extends ScalaString {
  override def value = s

  override def getValuation(expr: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def matches(expr: Expression): Boolean = expr match {
    case WrapScalaString(s2) if (s == s2) => true
    case _ => false
  }

  override def toString: String = s.toString
}
