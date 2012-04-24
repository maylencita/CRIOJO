package fr.emn.criojo.ext.expression.ScalaInt

import fr.emn.criojo.core.Var


case class VarScalaInt(name:String) extends Var[ScalaInt] with ScalaInt {
  override def toString():String = name
}
