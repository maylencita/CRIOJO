package fr.emn.criojo.ext.expression.ScalaInt

import fr.emn.criojo.core.datatype.Var

/** Integer Variable */
case class VarScalaInt(n: String) extends Var[ScalaInt](n) with ScalaInt {

  override def toString():String = n
}

object VarScalaInt {
  private var instanceNum = 0
  
  def apply() = new VarScalaInt("VarScalaInt@" + getInstanceNum)
  
  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}
