package fr.emn.criojo.ext.expression.ScalaBoolean

import fr.emn.criojo.core.Var


case class VarScalaBoolean(name: String) extends Var[ScalaBoolean] with ScalaBoolean {
  override def toString():String = name
}
