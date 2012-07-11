package fr.emn.criojo.ext.expression.ScalaList

import fr.emn.criojo.core.datatype._

/** List Variable */
case class VarScalaList[A <: Pattern with Expression](n: String)
  extends Var[ScalaList[A]](n) with ScalaList[A] {
}

object VarScalaList {
  private var instanceNum = 0

  def apply[A <: Pattern with Expression]() = {
    new VarScalaList[A]("VarScalaList@" + getInstanceNum)
  }

  private def getInstanceNum() = {
    instanceNum += 1
    instanceNum
  }
}
