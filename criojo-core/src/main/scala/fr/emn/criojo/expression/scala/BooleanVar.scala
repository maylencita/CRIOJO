package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar
import fr.emn.criojo.expression.CriojoBoolean

/** Boolean Variable */
case class BooleanVar(n: String) extends TypedVar[CriojoBoolean](n)
with ScalaBoolean {
}

object BooleanVar {
  private var instanceNum = 0

  def apply(): BooleanVar = BooleanVar("BooleanVar@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
