package fr.emn.criojo.expression

//TODO Change to WrappedValue
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.engine.CriojoGuard
import relation._
import relation.VarChannel

trait CriojoTypesPredef {
  type PatternExpr = Pattern with Expression

  // ************************************************************* ArrowAssoc **
//  final class CriojoArrowAssoc[A <: Term](x: A) {
//    @inline def ->[B <: Term](y: B): CriojoTuple2[A, B] =
//      ScalaTuple2[A, B](x, y)
//  }

//  implicit def any2CriojoArrowAssoc[A <: Term](x: A): CriojoArrowAssoc[A] =
//    new CriojoArrowAssoc[A](x)

//  implicit def anyTuple2CriojoTuple[A <: Term,B <: Term](t:(A,B)):Term =
//    ScalaTuple2[A, B](t._1, t._2)

  implicit def LazyGuard(x: => Expression): CriojoGuard = {
    val g = new CriojoGuard {
      override def eval(vals: Valuation) = {
        val xvalue = x.applyValuation(vals).reduce()
        xvalue.isInstanceOf[WrappedValue[Boolean]] && xvalue.asInstanceOf[WrappedValue[Boolean]].self
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

  implicit def variable2VarChannel(v:TypedVar[ChannelLocation]): VarChannel = VarChannel(v.name)

  implicit def ChannelToChannelLocation(c: Channel): ChannelLocation = c.location

  implicit def OutChannelToChannelLocation(oc: OutChannel): ChannelLocation = oc.location

  implicit def wrapped2Value[T](wval:WrappedValue[T]):T = wval.self

  implicit def expr2Variable(v:Term):Variable = v match{
    case x:Variable => x
    case _ =>  throw new ConversionImpossible("Cannot convert " + v + " to Variable")
  }

}

class ConversionImpossible(msg:String) extends Exception(msg){}
