package fr.emn.criojo.expression

//TODO Change to WrappedValue
import fr.emn.criojo.core.model._
import fr.emn.criojo.core.engine.{SuchThatGuard, Guard, CriojoGuard, TrueGuard}
import relation._
import relation.VarChannel

trait CriojoTypesPredef {

  implicit def LazyGuard(x: => Expression): CriojoGuard = {
    val g = new CriojoGuard {
      val empty = false
      val subGuard = TrueGuard
      def eval(vals: Valuation) = {
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

  implicit def atomExprPair2Guard(p:(Atom,Expression)):Guard = new SuchThatGuard(List(p._1), expr2guard(p._2))
  implicit def pair2Guard(p:(Atom,Guard)):Guard = new SuchThatGuard(List(p._1), p._2)

  def expr2guard(expr: => Expression):Guard = {
    val g = new CriojoGuard {
      val empty = false
      val subGuard = TrueGuard
      def eval(vals: Valuation) = {
        val xvalue = expr.applyValuation(vals).reduce()
        xvalue.isInstanceOf[WrappedValue[Boolean]] && xvalue.asInstanceOf[WrappedValue[Boolean]].self
      }

      val observed = Set[Relation]()

      def receiveUpdate(atom: Atom) {}
    }
    g
  }
}

class ConversionImpossible(msg:String) extends Exception(msg){}
