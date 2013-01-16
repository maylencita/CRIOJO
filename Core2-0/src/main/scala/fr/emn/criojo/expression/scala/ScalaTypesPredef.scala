package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Term, Expression, TypedVar}
import fr.emn.criojo.expression._
import list.{ListVar, CriojoList}

//import fr.emn.criojo.expression.tuple2.CriojoTuple2
//import fr.emn.criojo.expression.tuple2.constructor.ScalaTuple2

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

  implicit def typedVar2VarScalaInt(v:TypedVar[CriojoInt]):IntVar = IntVar(v.name)

  implicit def typedVar2VarScalaString(v:TypedVar[CriojoString]):StringVar = StringVar(v.name)

  implicit def typedVar2ListVar[A <: Expression](v:TypedVar[CriojoList[A]]):CriojoList[A] = ListVar(v.name)

  implicit def intToExpression(i: Int): CriojoInt = WrapScalaInt(i)

  implicit def stringToExpression(s: String): CriojoString = WrapScalaString(s)

  implicit def booleanToTerm(b: Boolean): CriojoBoolean = WrapScalaBoolean(b)

  final class CriojoArrowAssoc[A <: Term](val __leftOfArrow:A){
    def -> [B <: Term](y:B):ScalaTuple2[A,B] = CriojoArrow(__leftOfArrow, y)
  }

  implicit def any2CriojoArrowAssoc[A <: Term](x:A):CriojoArrowAssoc[A] = new CriojoArrowAssoc(x)

//  implicit def tuple2CriojoTuple2Pattern[A <: Pattern, B <: Pattern](t:(A,B)):ScalaTuple2[A,B] =
//    new ScalaTuple2[A,B](t._1,t._2)

//  implicit def tuple2CriojoTuple2Expr[A <: Expression, B <: Expression](t:(A,B)):WrapScalaTuple2[A,B] =
//    new WrapScalaTuple2[A,B](t)

  implicit def list2WrapedCriojoList[A <: Expression](lst:List[A]):WrapScalaList[A] = WrapScalaList[A](lst)
}

