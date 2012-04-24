package fr.emn.criojo.ext.expression.ScalaString

import fr.emn.criojo.core.Var


case class VarScalaString(name:String) extends Var[ScalaString] with ScalaString {
  override def toString():String = name
}
