package fr.emn.criojo.core

import datatype.{Term, Valuation, ValuationList, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaString.constructor.WrapScalaString
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.ext.expression.ScalaString.ScalaString
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.Relation.constructor.Channel
import fr.emn.criojo.ext.expression.Relation.constructor.OutChannel
import fr.emn.criojo.ext.expression.Relation.{VarChannel, Relation, ChannelLocation}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/20/12
 * Time: 12:38 AM
 * To change this template use File | Settings | File Templates.
 */

object Converters {

  implicit def LazyGuard(x: => Expression):CriojoGuard = {
    val g = new CriojoGuard{
      override def eval(vals: Valuation) = {
        val valuation = x.applyValuation(vals).reduce()
        valuation.isInstanceOf[WrapScalaBoolean] && valuation.asInstanceOf[WrapScalaBoolean].value
      }
      val valuations = new ValuationList()
      val observed = Set[Relation]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  implicit def LazyExpression(x: => Expression):Expression = {
    val g = new Expression {

      def matches(that: Term): Boolean = false

      override def reduce(): Expression = x.reduce()

      override def applyValuation(vals:Valuation): Expression = x.applyValuation(vals)

      override def getValuation(exp:Expression):Valuation = x.getValuation(exp)
    }
    g
  }

  implicit def intToExpression(i:Int):ScalaInt = WrapScalaInt(i)
  implicit def stringToExpression(s:String):ScalaString = WrapScalaString(s)
  implicit def booleanToTerm(b:Boolean):ScalaBoolean = WrapScalaBoolean(b)

  implicit def TermToRelation(t:Term):Relation = t match {
    case cl:ChannelLocation => ChannelLocationToRelation(cl)
    case _ => null
  }

  def TermToChannel(t:Term):Channel = t match {
    case cl:ChannelLocation => ChannelLocationToChannel(cl)
    case _ => null
  }
  implicit def ChannelLocationToChannel(cl:ChannelLocation):Channel = new Channel(cl.url, cl)

  def TermToOutChannel(t:Term):OutChannel = t match {
    case cl:ChannelLocation => ChannelLocationToOutChannel(cl)
    case _ => null
  }

  def ChannelLocationToRelation(cl:ChannelLocation):Relation = if (cl.url contains ".")
    ChannelLocationToOutChannel(cl)
  else
    ChannelLocationToChannel(cl)

  implicit def ChannelLocationToOutChannel(cl:ChannelLocation):OutChannel = new OutChannel(cl.url, cl)

  implicit def ChannelToChannelLocation(c:Channel):ChannelLocation = c.location
  implicit def OutChannelToChannelLocation(oc:OutChannel):ChannelLocation = oc.location
}
