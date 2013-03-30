package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model.{WrappedValue, Valuation, Expression, Pattern}
import fr.emn.criojo.expression.{CriojoTypesPredef, CriojoBoolean, NoValueDefined}

trait ScalaBoolean extends CriojoBoolean with CriojoTypesPredef{

  final def ||(that: CriojoBoolean): CriojoBoolean = new OrScalaBoolean(this, that)

  final def &&(that: CriojoBoolean): CriojoBoolean = new AndScalaBoolean(this, that)

  final def unary_! : CriojoBoolean = new NotScalaBoolean(this)

  final def getValue: Boolean = reduce() match {
    case i: ScalaBoolean => i.value
    case _ => throw new NoValueDefined()
  }

  def applyBinOp(x:CriojoBoolean, y:CriojoBoolean, op:(Boolean,Boolean)=>Boolean): Expression =
    (x.reduce(), y.reduce()) match{
    case (_x:WrappedValue[Boolean],_y:WrappedValue[Boolean]) => WrapScalaBoolean(op(_x,_y))
    case _ => throw new NoValueDefined()
  }

}

case class NotScalaBoolean(x: CriojoBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    NotScalaBoolean(expr1)
  }

  override def reduce(): Expression = x.reduce() match{
    case _x:WrappedValue[Boolean] =>  WrapScalaBoolean(!(_x.self))
    case _ => println("No value defined for " + x)
      throw new NoValueDefined()
  }
}

case class AndScalaBoolean(x: CriojoBoolean, y: CriojoBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 && expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Boolean,b:Boolean)=>a && b)
}

case class OrScalaBoolean(x: CriojoBoolean, y: CriojoBoolean) extends ScalaBoolean {
  override def applyValuation(valuation: Valuation): Expression = {
    val expr1 = x.applyValuation(valuation).asInstanceOf[ScalaBoolean]
    val expr2 = y.applyValuation(valuation).asInstanceOf[ScalaBoolean]

    expr1 || expr2
  }

  override def reduce(): Expression = applyBinOp(x,y,(a:Boolean,b:Boolean)=>a || b)
}


