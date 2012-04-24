package fr.emn.criojo.ext.expression.ScalaString

import fr.emn.criojo.core.datatype.Var

case class VarScalaString(n: String) extends Var[ScalaString](n)
    with ScalaString {
  def this() = this("")
}
