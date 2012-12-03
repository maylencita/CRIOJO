package fr.emn.criojo.expression.map

import fr.emn.criojo.core.model.{Wrapper, Pattern, Expression}
import operation._
import fr.emn.criojo.expression.scala.ScalaBoolean
import fr.emn.criojo.expression.NoValueDefined

trait CriojoMap[A <: Pattern with Expression, +B <: Pattern with Expression]
  extends Pattern with Expression {

  // Operations
  final def update[B1 >: B <: Pattern with Expression](key: A, up: B1 => B1): CriojoMap[A, B1] =
    new UpdateCriojoMap[A, B1](key, up, this)

  final def request[U <: Pattern with Expression](key: A, f: B => U): Wrapper[U] =
    new RequestCriojoMap[A, B, U](key, f, this)

  final def forall(p: ((A, B)) => ScalaBoolean): ScalaBoolean =
    new ForAllCriojoMap[A, B](p, this)

  final def foldLeft[U <: Pattern with Expression](z: U)(op: (U, (A, B)) => U): Wrapper[U] =
    new FoldLeftCriojoMap[A, B, U](z)(op, this)

  // Wrap
  def value: Map[A, B] = throw new NoValueDefined()

  final def getValue: Map[A, B] = reduce() match {
    case m: CriojoMap[A, B] => m.value
    case _ => throw new NoValueDefined()
  }
}

