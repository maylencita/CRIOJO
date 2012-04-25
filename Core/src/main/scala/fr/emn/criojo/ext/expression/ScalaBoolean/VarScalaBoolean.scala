package fr.emn.criojo.ext.expression.ScalaBoolean

import fr.emn.criojo.core.datatype.Var

case class VarScalaBoolean(n: String) extends Var[ScalaBoolean](n) with ScalaBoolean {
}

object VarScalaBoolean {
  private var instanceNum = 0

  def apply() = new VarScalaBoolean("VarScalaBoolean@" + getInstanceNum)

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}
