package fr.emn.criojo.ext.expression.ScalaList.operation

import fr.emn.criojo.core.datatype._
import fr.emn.criojo.ext.expression.ScalaList.ScalaList
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

case class ContainsScalaList[A <: Pattern with Expression](elem: Pattern with Expression,
    l: ScalaList[A]) extends ScalaBoolean {
  override def matches(expr: Expression): Boolean = expr match {
    case ContainsScalaList(e, ll) => e == e && l.matches(ll)
    case _ => false
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val lExpr = l.applyValuation(valuation).asInstanceOf[ScalaList[A]]
    val elemExpr = elem.applyValuation(valuation).asInstanceOf[Pattern with Expression]

    lExpr.contains(elemExpr)
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(l.getValue.contains(elem))
  }
}

