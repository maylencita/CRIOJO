package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.operations._
import fr.emn.criojo.ext.expression.{Expression, CriojoType}
import fr.emn.criojo.core.Variable

abstract class CriojoInteger extends CriojoType with Expression {

  def internReduce():CriojoIntegerValue = this.reduce() match {
    case csv:CriojoIntegerValue => csv
    case x:Any => throw(new Exception("incorrect match"))
  }

  def +(i:CriojoInteger):CriojoInteger = CriojoIntegerCriojoIntegerAddCriojoInteger(this,i)
  def -(i:CriojoInteger):CriojoInteger = CriojoIntegerCriojoIntegerSubCriojoInteger(this,i)
  def *(i:CriojoInteger):CriojoInteger = CriojoIntegerCriojoIntegerMultiplyCriojoInteger(this,i)
  def /(i:CriojoInteger):CriojoInteger = CriojoIntegerCriojoIntegerDivideCriojoInteger(this,i)
}

object IntegerVariable {
  var index=0;

  def createVariable(n:String):IntegerVariable = {
    var result:IntegerVariable = new IntegerVariable()
    result.name = n
    result
  }
}
case class IntegerVariable() extends CriojoInteger with Variable {
  var name = "Integer"+IntegerVariable.index
}

case class CriojoIntegerValue(v:Int) extends CriojoInteger {
}

case class CriojoIntegerCriojoIntegerAddCriojoInteger(i1:CriojoInteger, i2:CriojoInteger) extends CriojoInteger {
  override def reduce():Expression = CriojoIntegerValue(i1.internReduce().v+i2.internReduce().v)
}

case class CriojoIntegerCriojoIntegerSubCriojoInteger(i1:CriojoInteger, i2:CriojoInteger) extends CriojoInteger {
  override def reduce():Expression = CriojoIntegerValue(i1.internReduce().v-i2.internReduce().v)
}

case class CriojoIntegerCriojoIntegerMultiplyCriojoInteger(i1:CriojoInteger, i2:CriojoInteger) extends CriojoInteger {
  override def reduce():Expression = CriojoIntegerValue(i1.internReduce().v*i2.internReduce().v)
}

case class CriojoIntegerCriojoIntegerDivideCriojoInteger(i1:CriojoInteger, i2:CriojoInteger) extends CriojoInteger {
  override def reduce():Expression = CriojoIntegerValue(i1.internReduce().v/i2.internReduce().v)
}