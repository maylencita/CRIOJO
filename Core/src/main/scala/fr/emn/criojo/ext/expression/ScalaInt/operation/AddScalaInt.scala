package fr.emn.criojo.ext.expression.ScalaInt.operation

import fr.emn.criojo._
import core.datatype.{PatternNotMatchingException, Expression, Valuation}
import ext.expression.ScalaInt.constructor.WrapScalaInt
import ext.expression.ScalaInt.{VarScalaInt, ScalaInt}

case class AddScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: AddScalaInt => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => (x,y) match {
      case (x:VarScalaInt, y:VarScalaInt) => throw new PatternNotMatchingException()
      case (x:VarScalaInt, e:ScalaInt) => {
        var result = x.getValuation((expr.asInstanceOf[ScalaInt]-e).reduce())
        result
      }
      case (e:ScalaInt, y:VarScalaInt) => y.getValuation((expr.asInstanceOf[ScalaInt]-e).reduce())
    }
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 + expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue() + y.getValue())
  }

  override def matches(expr:Expression):Boolean =  expr match {
    case expr: AddScalaInt => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

