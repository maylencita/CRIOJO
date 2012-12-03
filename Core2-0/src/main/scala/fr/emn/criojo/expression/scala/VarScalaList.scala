package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._

/** List Variable */
case class VarScalaList[A <: Pattern with Expression](n: String)
  extends TypedVar[ScalaList[A]](n) with ScalaList[A] {
}

object VarScalaList {
  private var instanceNum = 0

  def apply[A <: Pattern with Expression](): VarScalaList[A] =
    VarScalaList[A]("VarScalaList@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
