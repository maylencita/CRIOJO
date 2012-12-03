package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar

/** Integer Variable */
case class VarScalaInt(n: String) extends TypedVar[ScalaInt](n) with ScalaInt {
}

object VarScalaInt {
  private var instanceNum = 0

  def apply(): VarScalaInt = VarScalaInt("VarScalaInt@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
