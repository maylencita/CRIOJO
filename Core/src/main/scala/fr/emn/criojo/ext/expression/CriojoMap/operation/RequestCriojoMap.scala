package fr.emn.criojo.ext.expression.CriojoMap.operation

import fr.emn.criojo.core.datatype.{Wrapper, Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.CriojoMap.{NoSuchKeyException, CriojoMap}

case class RequestCriojoMap[A <: Pattern with Expression,
  B <: Pattern with Expression, U <: Pattern with Expression]
  (k: A, f: B => U, m: CriojoMap[A, B]) extends Wrapper[U] {

  override def applyValuation(valuation: Valuation): Expression = {
    val exprKey = k.applyValuation(valuation).asInstanceOf[A]
    val exprMap = m.applyValuation(valuation).asInstanceOf[CriojoMap[A, B]]

    new RequestCriojoMap[A, B, U](exprKey, f, exprMap)
  }

  /** Reduce to Wrapper[B] */
  override def reduce(): Expression = {
    val v: Option[B] = m.getValue.get(k)

    val u: U = if (!v.isEmpty) {
      f(v.get)
    } else {
      throw new NoSuchKeyException(k.toString)
    }

    new Wrapper[U] {
      override def wrapped = u
    }
  }
}
