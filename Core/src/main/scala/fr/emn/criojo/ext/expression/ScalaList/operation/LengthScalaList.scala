package fr.emn.criojo.ext.expression.ScalaList.operation

import fr.emn.criojo.ext.expression.ScalaList.ScalaList
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.core.datatype.{Pattern, PatternNotMatchingException, Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

case class LengthScalaList[A <: Pattern with Expression](l: ScalaList[A])
    extends ScalaInt {
  override def matches(expr: Expression): Boolean = expr match {
    case LengthScalaList(ll) => l.matches(ll)
    case _ => false
  }

  override def applyValuation(valuation: Valuation): Expression = {
    l.applyValuation(valuation).asInstanceOf[ScalaList[A]].length
  }

  override def reduce(): Expression = {
    WrapScalaInt(l.getValue.length)
  }
}
