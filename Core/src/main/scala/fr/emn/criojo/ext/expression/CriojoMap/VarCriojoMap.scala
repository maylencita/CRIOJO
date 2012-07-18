package fr.emn.criojo.ext.expression.CriojoMap

import fr.emn.criojo.core.datatype.{Var, Pattern, Expression}

/** CriojoMap Variable */
case class VarCriojoMap[A <: Pattern with Expression,
  B <: Pattern with Expression](n: String)
  extends Var[CriojoMap[A, B]](n) with CriojoMap[A, B] {
}

object VarCriojoMap {
  private var instanceNum = 0

  def apply[A <: Pattern with Expression, B <: Pattern with Expression](): VarCriojoMap[A, B] =
    VarCriojoMap[A, B]("VarCriojoMap@" + getInstanceNum)

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}

