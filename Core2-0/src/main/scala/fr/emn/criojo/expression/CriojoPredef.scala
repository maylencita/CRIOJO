package fr.emn.criojo.expression

import tuple2.CriojoTuple2
import tuple2.constructor.ArrowAssocCriojoTuple2
import fr.emn.criojo.core.model.{Expression, Pattern}

object CriojoPredef {

  // ************************************************************* ArrowAssoc **
  final class CriojoArrowAssoc[A <: Pattern with Expression](x: A) {
    @inline def ->[B <: Pattern with Expression](y: B): CriojoTuple2[A, B] =
      ArrowAssocCriojoTuple2[A, B](x, y)
  }

  implicit def any2CriojoArrowAssoc[A <: Pattern with Expression](x: A): CriojoArrowAssoc[A] =
    new CriojoArrowAssoc[A](x)
}
