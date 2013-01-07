package fr.emn.criojo.expression.tuple2

import fr.emn.criojo.core.model.{Term, Expression, Pattern}
import fr.emn.criojo.expression.NoValueDefined

trait CriojoTuple2[+T1 <: Term, +T2 <: Term] {//extends Pattern{ //with Expression {

  def _1:T1
  def _2:T2

  // Operations
  // Wrap
//  def value: (T1, T2) = throw new NoValueDefined()
//
//  final def getValue: (T1, T2) = reduce() match {
//    case t: CriojoTuple2[T1, T2] => t.value
//    case _ => throw new NoValueDefined()
//  }
}
