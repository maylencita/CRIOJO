package fr.emn.criojo.ext.expression.CriojoTuple2

import fr.emn.criojo.core.datatype.{Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

trait CriojoTuple2[+T1 <: Pattern with Expression,
  +T2 <: Pattern with Expression] extends Pattern with Expression {

  // Operations
  // Wrap
  def value: Tuple2[T1, T2] = throw new NoValueDefined()

  final def getValue: Tuple2[T1, T2] = reduce() match {
    case t: CriojoTuple2[T1, T2] => t.value
    case _ => throw new NoValueDefined()
  }
}
