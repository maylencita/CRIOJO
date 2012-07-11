package fr.emn.criojo.ext.expression.ScalaList.constructor

import fr.emn.criojo.ext.expression.ScalaList.ScalaList
import fr.emn.criojo.core.datatype.{Valuation, Expression}

case object WrapScalaNil extends ScalaList[Nothing] {
  override def value = Nil;

  def tail: ScalaList[Nothing] =
    throw new UnsupportedOperationException("tail of empty list")

  override def matches(expr: Expression): Boolean = expr match {
    case WrapScalaNil => true
    case WrapScalaList(l) => l == Nil
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation();

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def toString :String = Nil.toString
}
