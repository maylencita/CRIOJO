package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{WrappedValue, Valuation, Expression}
import fr.emn.criojo.expression._

trait ScalaString extends CriojoString{

  final def length: ScalaInt = new LengthScalaString(this)

  final def +(that: CriojoString): CriojoString = new AddScalaString(this, that)

  final def <=>(that: CriojoString): ScalaBoolean = new EqualScalaString(this, that)

  final def getValue: String = reduce() match {
    case i: ScalaString => i.value
    case _ => throw new NoValueDefined()
  }

}

object ScalaString extends CriojoTypesPredef{
  def applyBinOp(x:CriojoString, y:CriojoString, op:(String,String)=>String): Expression = (x.reduce(), y.reduce()) match{
    case (_x:WrappedValue[String],_y:WrappedValue[String]) => WrapScalaString(op(_x,_y))
    case _ => throw new NoValueDefined()
  }

  def compare(x:CriojoString, y:CriojoString, op:(String,String)=>Boolean): Expression = (x.reduce(), y.reduce()) match{
    case (_x:WrappedValue[String],_y:WrappedValue[String]) => WrapScalaBoolean(op(_x,_y))
    case _ => throw new NoValueDefined()
  }
}

import ScalaString.{applyBinOp, compare}

case class AddScalaString(x: CriojoString, y: CriojoString) extends ScalaString {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 + expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:String,b:String)=>a+b)
}

case class EqualScalaString(x: CriojoString, y: CriojoString) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaString]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaString]

    expr1 <=> expr2
  }

  override def reduce(): Expression = compare(x,y,(a:String,b:String)=>a==b)
}

case class LengthScalaString(s: CriojoString) extends ScalaInt {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr = s.applyValuation(valuation).asInstanceOf[ScalaString]

    expr.length
  }

  override def reduce(): Expression = s match{
    case _s:WrappedValue[String] => WrapScalaInt(_s.self.length)
  }
}

