package fr.emn.criojo.ext.expression.ScalaBoolean

import fr.emn.criojo.core.datatype.Var

/** Boolean Variable */
case class VarScalaBoolean(n: String) extends Var[ScalaBoolean](n)
    with ScalaBoolean {
}

object VarScalaBoolean {
  private var instanceNum = 0

  def apply(): VarScalaBoolean = VarScalaBoolean("VarScalaBoolean@" + getInstanceNum)

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}
