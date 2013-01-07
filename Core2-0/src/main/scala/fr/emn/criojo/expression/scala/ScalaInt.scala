package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._
import fr.emn.criojo.core.PatternNotMatchingException
import fr.emn.criojo.expression.NoValueDefined

/** Wrap Scala Int type */
trait ScalaInt extends Expression {
  def value: Int = {
    throw new NoValueDefined()
  }

  final def +(that: ScalaInt): ScalaInt = new AddScalaInt(this, that)

  final def -(that: ScalaInt): ScalaInt = new MinusScalaInt(this, that)

  final def *(that: ScalaInt): ScalaInt = new CrossScalaInt(this, that)

  final def /(that: ScalaInt): ScalaInt = new DivideScalaInt(this, that)

  final def <=>(that: ScalaInt): ScalaBoolean = new EqualScalaInt(this, that)

  final def !<=>(that: ScalaInt): ScalaBoolean = !(this <=> that)

  final def >(that: ScalaInt): ScalaBoolean = new GreaterThanScalaInt(this, that)

  final def >=(that: ScalaInt): ScalaBoolean = >(that) || (this <=> that)

  final def <(that: ScalaInt): ScalaBoolean = !(this >= that)

  final def <=(that: ScalaInt): ScalaBoolean = !(this > that)

  final def getValue: Int = reduce() match {
    case i: ScalaInt => i.value
    case _ => throw new NoValueDefined()
  }
}

object ScalaInt {
  def Min(listOfInt: ScalaInt*) = new MinScalaInt(listOfInt.toList)
}

case class AddScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 + expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue + y.getValue)
  }
}

case class EqualScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 <=> expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue == y.getValue)
  }
}

case class CrossScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 * expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue * y.getValue)
  }
}

case class DivideScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 / expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue / y.getValue)
  }
}

case class GreaterThanScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 > expr2
  }

  override def reduce(): Expression = {
    WrapScalaBoolean(x.getValue > y.getValue)
  }
}

case class MinScalaInt(listOfInt: List[ScalaInt]) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression =
    MinScalaInt(listOfInt.map(s =>
      s.applyValuation(valuation).asInstanceOf[ScalaInt]).toList)

  override def reduce(): Expression = {
    def min(s1: ScalaInt, s2: ScalaInt): ScalaInt = (s1.reduce(), s2.reduce()) match {
      case (WrapScalaInt(i), WrapScalaInt(j)) => if (i < j) s1 else s2
      case _ => throw new PatternNotMatchingException
    }

    listOfInt.reduceRight(min(_, _))
  }
}

case class MinusScalaInt(x: ScalaInt, y: ScalaInt) extends ScalaInt {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: MinusScalaInt => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 - expr2
  }

  override def reduce(): Expression = {
    WrapScalaInt(x.getValue - y.getValue)
  }

  override def matches(expr: Expression): Boolean = expr match {
    case expr: MinusScalaInt => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

