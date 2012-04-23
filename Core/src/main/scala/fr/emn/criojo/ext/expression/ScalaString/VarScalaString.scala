package fr.emn.criojo.ext.expression.ScalaString

import fr.emn.criojo.core.Var

object VarScalaString {

}

case class VarScalaString(name:String) extends Var[ScalaString] with ScalaString {
}
