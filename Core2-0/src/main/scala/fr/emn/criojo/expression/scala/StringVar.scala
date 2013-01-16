package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar
import fr.emn.criojo.expression.CriojoString

/** String Variable */
case class StringVar(n: String) extends TypedVar[CriojoString](n)
with ScalaString {
}

object StringVar {
  private var instanceNum = 0

  def apply() = new StringVar("StringVar@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}

