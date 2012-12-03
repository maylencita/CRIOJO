package fr.emn.criojo.expression.map.constructor

import fr.emn.criojo.core.model.{Valuation, Expression, Pattern}
import fr.emn.criojo.expression.map.CriojoMap

case class WrapScalaMap[A <: Pattern with Expression,
B <: Pattern with Expression](m: Map[A, B])
  extends CriojoMap[A, B] {

  override def value = m

  /**
   * With type map, only WrapScalaMap could be in solution
   * because ContainsKeyValueCriojoMap is reduce as WrapScalaMap even if it's
   * a Constructor.
   */
  override def matches(expr: Expression): Boolean = expr match {
    case wsm: WrapScalaMap[A, B] => m == wsm.value
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def toString: String = m.toString()
}
