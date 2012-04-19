package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.operations.CanAddType
import fr.emn.criojo.ext.expression.{Expression, CriojoType}
import fr.emn.criojo.core.Variable

abstract class CriojoString extends CriojoType with Expression {

  def internReduce():CriojoStringValue = this.reduce() match {
    case csv:CriojoStringValue => csv
    case x:Any => throw(new Exception("incorrect match"))
  }

  def +(i:CriojoString):CriojoString = CriojoStringCriojoStringAddCriojoString(this,i)
}

object StringVariable {
  var index=0;

  def apply(n:String):StringVariable = {
    var result:StringVariable = new StringVariable()
    result.name = n
    result
  }
}
case class StringVariable() extends CriojoString with Variable {
  var name = "String"+StringVariable.index
  def getName():String = name

  StringVariable.index = StringVariable.index+1
}

case class CriojoStringValue(v:String) extends CriojoString {
}

case class CriojoStringCriojoStringAddCriojoString(i1:CriojoString, i2:CriojoString) extends CriojoString {
  override def reduce():Expression = CriojoStringValue(i1.internReduce().v+i2.internReduce().v)
}