package fr.emn.criojo.expression.map

import fr.emn.criojo.core.model.{TypedVar, Pattern, Expression}

/** map Variable */
case class VarCriojoMap[A <: Pattern with Expression,
B <: Pattern with Expression](n: String)
  extends TypedVar[CriojoMap[A, B]](n) with CriojoMap[A, B] {
}

object VarCriojoMap {
  private var instanceNum = 0

  def apply[A <: Pattern with Expression, B <: Pattern with Expression](): VarCriojoMap[A, B] =
    VarCriojoMap[A, B]("VarCriojoMap@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}

