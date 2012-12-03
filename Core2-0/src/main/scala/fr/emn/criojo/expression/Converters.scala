package fr.emn.criojo.expression

import fr.emn.criojo.core.model._
import relation._
import fr.emn.criojo.core.engine.CriojoGuard
import scala._
import scala.WrapScalaBoolean

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/20/12
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */

trait Converters {

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

  implicit def intToExpression(i: Int): ScalaInt = WrapScalaInt(i)

  implicit def stringToExpression(s: String): ScalaString = WrapScalaString(s)

  implicit def booleanToTerm(b: Boolean): ScalaBoolean = WrapScalaBoolean(b)

  implicit def variable2VarChannel(v: Variable): VarChannel = v.asInstanceOf[VarChannel]

  //  implicit def TermToRelation(t:Term):Relation = t match {
  //    case cl:ChannelLocation => ChannelLocationToRelation(cl)
  //    case _ => null
  //  }

  //  def TermToChannel(t:Term):Channel = t match {
  //    case cl:ChannelLocation => ChannelLocationToChannel(cl)
  //    case _ => null
  //  }
  //  implicit def ChannelLocationToChannel(cl:ChannelLocation):Channel = new Channel(cl.url, cl)

  //  def TermToOutChannel(t:Term):OutChannel = t match {
  //    case cl:ChannelLocation => ChannelLocationToOutChannel(cl)
  //    case _ => null
  //  }

  //  def ChannelLocationToRelation(cl:ChannelLocation):Relation = if (cl.url contains ".")
  //    ChannelLocationToOutChannel(cl)
  //  else
  //    ChannelLocationToChannel(cl)

  //  implicit def ChannelLocationToOutChannel(cl:ChannelLocation):OutChannel = new OutChannel(cl.url, cl)

  implicit def ChannelToChannelLocation(c: Channel): ChannelLocation = c.location

  implicit def OutChannelToChannelLocation(oc: OutChannel): ChannelLocation = oc.location
}
