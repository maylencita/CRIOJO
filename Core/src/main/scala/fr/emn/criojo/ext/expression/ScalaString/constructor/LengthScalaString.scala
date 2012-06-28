package fr.emn.criojo.ext.expression.ScalaString.constructor

import fr.emn.criojo.ext.expression.ScalaString.ScalaString
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.core.datatype.{PatternNotMatchingException, Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

case class LengthScalaString(s: ScalaString) extends ScalaInt {
  override def matches(expr: Expression):Boolean =  expr match {
    case LengthScalaString(ss) => s.matches(ss)
    case _ => false
  }

  override def getValuation(expr: Expression): Valuation = expr match {
    case LengthScalaString(ss) => s.getValuation(ss)
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr = s.applyValuation(valuation).asInstanceOf[ScalaString]

    expr.length
  }

  override def reduce(): Expression = {
    WrapScalaInt(s.getValue().length)
  }
}
