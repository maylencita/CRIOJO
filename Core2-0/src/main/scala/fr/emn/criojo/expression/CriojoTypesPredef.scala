package fr.emn.criojo.expression

import scala.{ScalaInt, WrapScalaBoolean}

//TODO Change to WrappedValue
import tuple2.CriojoTuple2
import tuple2.constructor.ArrowAssocCriojoTuple2
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.engine.CriojoGuard
import relation._
import relation.VarChannel

trait CriojoTypesPredef {
  type PatternExpr = Pattern with Expression

  // ************************************************************* ArrowAssoc **
//  final class CriojoArrowAssoc[A <: Term](x: A) {
//    @inline def ->[B <: Term](y: B): CriojoTuple2[A, B] =
//      ArrowAssocCriojoTuple2[A, B](x, y)
//  }

//  implicit def any2CriojoArrowAssoc[A <: Term](x: A): CriojoArrowAssoc[A] =
//    new CriojoArrowAssoc[A](x)

//  implicit def anyTuple2CriojoTuple[A <: Term,B <: Term](t:(A,B)):Term =
//    ArrowAssocCriojoTuple2[A, B](t._1, t._2)

  implicit def LazyGuard(x: => Expression): CriojoGuard = {
    val g = new CriojoGuard {
      override def eval(vals: Valuation) = {
        val xvalue = x.applyValuation(vals).reduce()
        xvalue.isInstanceOf[WrapScalaBoolean] && xvalue.asInstanceOf[WrapScalaBoolean].value
      }

      val observed = Set[Relation]()

      def receiveUpdate(atom: Atom) {}
    }
    g
  }

  implicit def LazyExpression(x: => Expression): Expression = {
    val g = new Expression {

      def matches(that: Term): Boolean = false

      override def reduce(): Expression = x.reduce()

      override def applyValuation(vals: Valuation): Expression = x.applyValuation(vals)

      override def getValuation(exp: Expression): Valuation = x.getValuation(exp)
    }
    g
  }

  implicit def variable2VarChannel(v:TypedVar[ChannelLocation]): VarChannel = v.asInstanceOf[VarChannel]

  implicit def ChannelToChannelLocation(c: Channel): ChannelLocation = c.location

  implicit def OutChannelToChannelLocation(oc: OutChannel): ChannelLocation = oc.location

  implicit def wrapped2Value[T](wval:WrappedValue[T]):T = wval.self

  implicit def expr2Variable(v:Term):Variable = v match{
    case x:Variable => x
    case _ =>  throw new ConversionImpossible("Cannot convert " + v + " to Variable")
  }

}

class ConversionImpossible(msg:String) extends Exception(msg){}
