package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar
import fr.emn.criojo.expression.CriojoInt

/** Integer Variable */
case class IntVar(n: String) extends TypedVar[CriojoInt](n) with ScalaInt {
}

object IntVar {
  private var instanceNum = 0

  def apply(): IntVar = IntVar("IntVar@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
