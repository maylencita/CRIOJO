package fr.emn.criojo.ext.expression.ScalaInt.operation

import fr.emn.criojo.core.datatype.{PatternNotMatchingException, Valuation, Expression}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt

case class DivideScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: DivideScalaInt => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 / expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue() / y.getValue())
  }

  override def matches(expr:Expression):Boolean =  expr match {
    case expr: DivideScalaInt => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

