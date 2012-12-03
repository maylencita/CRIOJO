package fr.emn.criojo.expression.tuple2

import fr.emn.criojo.core.model.{Expression, Pattern}
import fr.emn.criojo.expression.NoValueDefined

trait CriojoTuple2[+T1 <: Pattern with Expression,
+T2 <: Pattern with Expression] extends Pattern with Expression {

  // Operations
  // Wrap
  def value: (T1, T2) = throw new NoValueDefined()

  final def getValue: (T1, T2) = reduce() match {
    case t: CriojoTuple2[T1, T2] => t.value
    case _ => throw new NoValueDefined()
  }
}
