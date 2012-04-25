package fr.emn.criojo.ext.expression.ScalaString

import fr.emn.criojo.core.datatype.Var

/** String Variable */
case class VarScalaString(n: String) extends Var[ScalaString](n)
    with ScalaString {
}

object VarScalaString {
  private var instanceNum = 0

  def apply() = new VarScalaString("VarScalaString@" + getInstanceNum)

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}

