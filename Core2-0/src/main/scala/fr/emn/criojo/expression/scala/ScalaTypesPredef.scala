package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Pattern, Expression, TypedVar}
//import fr.emn.criojo.expression.tuple2.CriojoTuple2
//import fr.emn.criojo.expression.tuple2.constructor.ArrowAssocCriojoTuple2

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

//  final class CriojoArrowAssoc[A <: Pattern with Expression](x: A) {
//    @inline def ->[B <: Pattern with Expression](y: B): CriojoTuple2[A, B] =
//      ArrowAssocCriojoTuple2[A, B](x, y)
//  }

  implicit def typedVar2VarScalaInt(v:TypedVar[ScalaInt]):VarScalaInt = VarScalaInt(v.name)

//  implicit def intTypedVar2CriojoArrowAssoc(v:TypedVar[Pattern with Expression]):CriojoArrowAssoc[VarScalaInt] = new CriojoArrowAssoc[VarScalaInt](v.asInstanceOf[VarScalaInt])
//
  implicit def typedVar2VarScalaString(v:TypedVar[ScalaString]):VarScalaString = VarScalaString(v.name)
//
//  implicit def stringTypedVar2CriojoArrowAssoc(v:TypedVar[ScalaString]):CriojoArrowAssoc[VarScalaString] = new CriojoArrowAssoc[VarScalaString](v.asInstanceOf[VarScalaString])

  implicit def intToExpression(i: Int): ScalaInt = WrapScalaInt(i)

  implicit def stringToExpression(s: String): ScalaString = WrapScalaString(s)

  implicit def booleanToTerm(b: Boolean): ScalaBoolean = WrapScalaBoolean(b)

}

