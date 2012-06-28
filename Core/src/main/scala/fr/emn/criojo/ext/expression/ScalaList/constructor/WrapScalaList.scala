package fr.emn.criojo.ext.expression.ScalaList.constructor

import fr.emn.criojo.core.datatype.{Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaList.ScalaList

case class WrapScalaList[A <: Pattern with Expression](l: List[A]) extends ScalaList[A] {

  override  def value = l;

  override def getValuation(exp: Expression): Valuation = Valuation();

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def matches(expr: Expression):Boolean = expr match {
    case WrapScalaList(ll) if (l == ll) => true
    case _ => false
  }

  override def toString():String = l.toString
}

