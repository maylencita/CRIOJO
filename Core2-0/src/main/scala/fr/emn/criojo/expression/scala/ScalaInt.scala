package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._
import fr.emn.criojo.core.PatternNotMatchingException
import fr.emn.criojo.expression.{CriojoTypesPredef, CriojoBoolean, NoValueDefined, CriojoInt}

trait ScalaInt extends CriojoInt {

  final def +(that: CriojoInt): CriojoInt = new AddScalaInt(this, that)

  final def -(that: CriojoInt): CriojoInt = new MinusScalaInt(this, that)

  final def *(that: CriojoInt): CriojoInt = new CrossScalaInt(this, that)

  final def /(that: CriojoInt): CriojoInt = new DivideScalaInt(this, that)

  final def <=>(that: CriojoInt): CriojoBoolean = new EqualScalaInt(this, that)

  final def !<=>(that: CriojoInt): CriojoBoolean = !(this <=> that)

  final def >(that: CriojoInt): CriojoBoolean = new GreaterThanScalaInt(this, that)

  final def >=(that: CriojoInt): CriojoBoolean = >(that) || (this <=> that)

  final def <(that: CriojoInt): CriojoBoolean = !(this >= that)

  final def <=(that: CriojoInt): CriojoBoolean = !(this > that)

  final def getValue: Int = reduce() match {
    case i: ScalaInt => i.value
    case _ => throw new NoValueDefined()
  }

}

object ScalaInt extends CriojoTypesPredef{
  def Min(listOfInt: CriojoInt*) = new MinScalaInt(listOfInt.toList)

  def applyBinOp(x:CriojoInt, y:CriojoInt, op:(Int,Int)=>Int): Expression = (x.reduce(), y.reduce()) match{
    case (_x:WrappedValue[Int],_y:WrappedValue[Int]) => WrapScalaInt(op(_x,_y))
    case _ => throw new NoValueDefined()
  }

  def compare(x:CriojoInt, y:CriojoInt, op:(Int,Int)=>Boolean): Expression = (x.reduce(), y.reduce()) match{
    case (_x:WrappedValue[Int],_y:WrappedValue[Int]) => WrapScalaBoolean(op(_x,_y))
    case _ => throw new NoValueDefined()
  }
}

import ScalaInt.{applyBinOp,compare}

case class AddScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 + expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Int,b:Int)=>a+b)
}

case class EqualScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 <=> expr2
  }

  override def reduce(): Expression = compare(x,y,(a:Int,b:Int)=>a == b)
}

case class CrossScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 * expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Int,b:Int)=>a*b)
}

case class DivideScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 / expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Int,b:Int)=>a/b)
}

case class GreaterThanScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 > expr2
  }

  override def reduce(): Expression = compare(x,y,(a:Int,b:Int)=>a > b)
}

case class MinScalaInt(listOfInt: List[CriojoInt]) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression =
    MinScalaInt(listOfInt.map(s =>
      s.applyValuation(valuation).asInstanceOf[ScalaInt]).toList)

  override def reduce(): Expression = {
    def min(s1: CriojoInt, s2: CriojoInt): CriojoInt = (s1.reduce(), s2.reduce()) match {
      case (WrapScalaInt(i), WrapScalaInt(j)) => if (i < j) s1 else s2
      case _ => throw new PatternNotMatchingException
    }

    listOfInt.reduceRight(min(_, _))
  }
}

case class MinusScalaInt(x: CriojoInt, y: CriojoInt) extends ScalaInt {
  override def getValuation(expr: Expression): Valuation = expr match {
    case expr: MinusScalaInt => x.getValuation(expr.x).union(y.getValuation(expr.y))
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaInt]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaInt]

    expr1 - expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Int,b:Int)=>a-b)

  override def matches(expr: Expression): Boolean = expr match {
    case expr: MinusScalaInt => x.matches(expr.x) && y.matches(expr.y)
    case _ => false
  }
}

