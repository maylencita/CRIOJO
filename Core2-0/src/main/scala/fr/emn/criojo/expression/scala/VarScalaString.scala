package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar

/** String Variable */
case class VarScalaString(n: String) extends TypedVar[ScalaString](n)
with ScalaString {
}

object VarScalaString {
  private var instanceNum = 0

  def apply() = new VarScalaString("VarScalaString@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}

