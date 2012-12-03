package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Variable, TypedVar}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/30/12
 * Time: 2:36 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Implicit conversions for TypedVariable to scala.Var<type>
 */
trait ScalaTypesPredef {

  implicit def typedVar2VarScalaInt(v:Variable):VarScalaInt = v.asInstanceOf[VarScalaInt]

  implicit def typedVar2VarScalaString(v:TypedVar[ScalaString]):VarScalaString = v.asInstanceOf[VarScalaString]
}

class ConversionImpossible(msg:String) extends Exception(msg){}
