package fr.emn.criojo.ext.expression

import CriojoTuple2.constructor.ArrowAssocCriojoTuple2
import CriojoTuple2.CriojoTuple2
import fr.emn.criojo.core.datatype.{Expression, Pattern}

object CriojoPredef {
  // ************************************************************* ArrowAssoc **
  final class CriojoArrowAssoc[A <: Pattern with Expression](x: A) {
    @inline def ->[B <: Pattern with Expression](y: B): CriojoTuple2[A, B] =
      ArrowAssocCriojoTuple2(x, y)
  }
  implicit def any2CriojoArrowAssoc[A <: Pattern with Expression](x: A): CriojoArrowAssoc[A] =
    new CriojoArrowAssoc(x)
}
