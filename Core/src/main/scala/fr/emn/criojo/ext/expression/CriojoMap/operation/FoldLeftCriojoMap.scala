package fr.emn.criojo.ext.expression.CriojoMap.operation

import fr.emn.criojo.core.datatype.{Valuation, Wrapper, Expression, Pattern}
import fr.emn.criojo.ext.expression.CriojoMap.CriojoMap

case class FoldLeftCriojoMap[A <: Pattern with Expression,
  B <: Pattern with Expression, U <: Pattern with Expression]
  (z: U)(op: (U, (A, B)) => U, m: CriojoMap[A, B]) extends Wrapper[U] {

  override def applyValuation(valuation: Valuation): Expression = {
    val exprZ = z.applyValuation(valuation).asInstanceOf[U]
    val exprMap = m.applyValuation(valuation).asInstanceOf[CriojoMap[A, B]]

    new FoldLeftCriojoMap[A, B, U](exprZ)(op, exprMap)
  }

  /** Reduce to Wrapper[B] */
  override def reduce(): Expression = {
    val u: U = m.getValue.foldLeft(z.reduce().asInstanceOf[U])(op)

    new Wrapper[U] {
      override def wrapped = u
    }
  }
}
