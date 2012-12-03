package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Valuation, Expression, Pattern}
import fr.emn.criojo.expression.NoValueDefined

/** Wrap Scala Boolean type */
trait ScalaBoolean extends Pattern with Expression {
  def value: Boolean = {
    throw new NoValueDefined()
  }

  final def ||(that: ScalaBoolean): ScalaBoolean = new OrScalaBoolean(this, that)

  final def &&(that: ScalaBoolean): ScalaBoolean = new AndScalaBoolean(this, that)

  final def unary_! : ScalaBoolean = new NotScalaBoolean(this)

  final def getValue: Boolean = reduce() match {
    case i: ScalaBoolean => i.value
    case _ => throw new NoValueDefined()
  }
}

case class NotScalaBoolean(x: ScalaBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    NotScalaBoolean(expr1)
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(!x.getValue)
  }
}

case class AndScalaBoolean(x: ScalaBoolean, y: ScalaBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 && expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue && y.getValue)
  }
}

case class OrScalaBoolean(x: ScalaBoolean, y: ScalaBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 || expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue || y.getValue)
  }
}


