package fr.emn.criojo.ext.expression.CriojoMap.operation

import fr.emn.criojo.core.datatype.{Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.CriojoMap.{NoSuchKeyException, CriojoMap}
import fr.emn.criojo.ext.expression.CriojoMap.constructor.WrapScalaMap

case class UpdateCriojoMap[A <: Pattern with Expression,
  B <: Pattern with Expression] (k: A, up: B => B, m: CriojoMap[A, B])
  extends CriojoMap[A, B] {

  override def applyValuation(valuation: Valuation): Expression = {
    val exprKey = k.applyValuation(valuation).asInstanceOf[A]
    val exprMap = m.applyValuation(valuation).asInstanceOf[CriojoMap[A, B]]

    new UpdateCriojoMap[A, B](exprKey, up, exprMap)
  }

  override def reduce(): Expression = {
    val optB: Option[B] = m.getValue.get(k)

    val b: B = if (!optB.isEmpty) {
      optB.get
    } else {
      throw new NoSuchKeyException(k.toString)
    }

    // Update b and reduce it
    val mm = m.getValue + (k -> up(b).reduce().asInstanceOf[B])

    WrapScalaMap[A,B](mm)
  }
}
