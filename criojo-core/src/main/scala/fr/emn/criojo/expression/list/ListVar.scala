package fr.emn.criojo.expression.list

import fr.emn.criojo.core.model._

/** List Variable */
case class ListVar[A <: Expression](n: String)
  extends TypedVar[CriojoList[A]](n) with CriojoList[A] {

  def isEmpty:Boolean =
  throw new UnsupportedOperationException("ListVar cannot be used as Expression")

  def head =
    throw new UnsupportedOperationException("ListVar cannot be used as Expression")

  def tail =
    throw new UnsupportedOperationException("ListVar cannot be used as Expression")
}

object ListVar {
  private var instanceNum = 0

  def apply[A <: Expression](): ListVar[A] =
    ListVar[A]("VarScalaList@" + getInstanceNum)

  private def getInstanceNum = {
    instanceNum += 1
    instanceNum
  }
}
