package fr.emn.criojo.ext.expression.ScalaBoolean

import fr.emn.criojo.core.datatype.Var

case class VarScalaBoolean(n: String) extends Var[ScalaBoolean](n)
    with ScalaBoolean {
  def this() = this("")
}
