package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{Valuation, Expression, Pattern}
import fr.emn.criojo.expression.NoValueDefined

/** Wrap Scala String type in Criojo */
trait ScalaString extends Expression {
  def value: String = {
    throw new NoValueDefined()
  }

  final def length: ScalaInt = new LengthScalaString(this)

  final def size: ScalaInt = this.length

  final def +(that: ScalaString): ScalaString = new AddScalaString(this, that)

  final def <=>(that: ScalaString): ScalaBoolean = new EqualScalaString(this, that)

  final def !<=>(that: ScalaString): ScalaBoolean = !(this <=> that)

  final def isEmpty: ScalaBoolean = this.length <=> WrapScalaInt(0)

  final def getValue: String = reduce() match {
    case i: ScalaString => i.value
    case _ => throw new NoValueDefined()
  }
}

case class AddScalaString(x: ScalaString, y: ScalaString) extends ScalaString {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 + expr2
  }

  override def reduce(): Expression = {
    WrapScalaString(x.getValue + y.getValue)
  }
}

case class EqualScalaString(x: ScalaString, y: ScalaString) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 <=> expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue == y.getValue)
  }
}

case class LengthScalaString(s: ScalaString) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr = s.applyValuation(valuation).asInstanceOf[ScalaString]

    expr.length
  }

  override def reduce(): Expression = {
    WrapScalaInt(s.getValue.length)
  }
}

