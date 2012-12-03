package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.TypedVar

/** Boolean Variable */
case class VarScalaBoolean(n: String) extends TypedVar[ScalaBoolean](n)
with ScalaBoolean {
}

object VarScalaBoolean {
  private var instanceNum = 0

  def apply(): VarScalaBoolean = VarScalaBoolean("VarScalaBoolean@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
