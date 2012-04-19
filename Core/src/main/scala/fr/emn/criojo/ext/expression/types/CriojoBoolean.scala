package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.operations.{CanMultiplyType, CanAddType}
import fr.emn.criojo.ext.expression.{Expression, CriojoType}
import fr.emn.criojo.core.Variable

abstract class CriojoBoolean extends CriojoType with Expression {

  def internReduce():CriojoBooleanValue = this.reduce() match {
    case csv:CriojoBooleanValue => csv
    case x:Any => throw(new Exception("incorrect match"))
  }

  def ||(i:CriojoBoolean):CriojoBoolean = CriojoBooleanCriojoBooleanOrCriojoBoolean(this,i)
  def &&(i:CriojoBoolean):CriojoBoolean = CriojoBooleanCriojoBooleanAndCriojoBoolean(this,i)
}

object BooleanVariable {
  var index=0;

  def createVariable(n:String):BooleanVariable = {
    var result:BooleanVariable = new BooleanVariable()
    result.name = n
    result
  }
}
case class BooleanVariable() extends CriojoBoolean with Variable {
   var name = "Boolean"+BooleanVariable.index
}
case class CriojoBooleanValue(v:Boolean) extends CriojoBoolean {
}

case class CriojoBooleanCriojoBooleanOrCriojoBoolean(i1:CriojoBoolean, i2:CriojoBoolean) extends CriojoBoolean {
  override def reduce():Expression = CriojoBooleanValue(i1.internReduce().v||i2.internReduce().v)
}

case class CriojoBooleanCriojoBooleanAndCriojoBoolean(i1:CriojoBoolean, i2:CriojoBoolean) extends CriojoBoolean {
  override def reduce():Expression = CriojoBooleanValue(i1.internReduce().v&&i2.internReduce().v)
}
