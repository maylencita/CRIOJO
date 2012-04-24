package fr.emn.criojo.ext.expression.ScalaInt

import fr.emn.criojo.core.datatype.Var

case class VarScalaInt(n: String) extends Var[ScalaInt](n) with ScalaInt {
  def this() = this("")
}
