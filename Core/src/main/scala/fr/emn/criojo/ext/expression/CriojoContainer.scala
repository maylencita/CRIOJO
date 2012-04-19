package fr.emn.criojo.ext.expression

import fr.emn.criojo.core._
import types._

trait Expression extends Term {

  def reduce():Expression = this
}

object converters {
//  implicit def StringToCriojoType(s:String):CriojoType = CriojoString(s)
//  implicit def IntegerToCriojoType(i:Int):CriojoType = CriojoInteger(i)
//  implicit def BooleanToCriojoType(b:Boolean):CriojoType = CriojoBoolean(b)
//  implicit def CriojoTypeToCriojoContainer[T <% CriojoType](ct:T):CriojoContainer[T] = CriojoContainer(ct)
//  implicit def CriojoTypeToExpression[T <% CriojoType](ct:T):Expression = CriojoContainer(ct)

  implicit def intToExpression(n:Int):Expression = CriojoIntegerValue(n)
  implicit def stringToExpression(s:String):Expression = CriojoStringValue(s)
  implicit def booleanToExpression(b:Boolean):Expression = CriojoBooleanValue(b)


  implicit def LazyGuard(x: => Expression):CriojoGuard = {
    val g = new CriojoGuard{
      override def eval(vals: Valuation) = {
        val truc = x.applyValuation(vals).reduce()
        x.applyValuation(vals).reduce().matches(CriojoBooleanValue(true))
      }

      val valuations = new ValuationList()
      val observed = Set[String]()
      def receiveUpdate(atom: Atom){}
    }
    g
  }

  implicit def LazyExpression(x: => Expression):Expression = {
    val g = new Expression {

      def name = x.name
      def matches(that: Term): Boolean = false

      override def reduce(): Expression = x.reduce()

      def applyValuation(vals:Valuation): Expression = x.applyValuation(vals) match {
        case e:Expression => e
        case _ => throw new Exception("Boom!")
      }

      def getValuation(t:Term):Valuation = x.getValuation(t)
    }
    g
  }
}