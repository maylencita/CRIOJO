package fr.emn.criojo.ext.expression.ScalaInt

import operation._
import fr.emn.criojo.core.datatype.{Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean

/** Wrap Scala Int type */
trait ScalaInt extends Pattern with Expression {
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

  final def <(that: ScalaInt): ScalaBoolean = ! (this >= that)
  
  final def <=(that: ScalaInt): ScalaBoolean = ! (this > that)

  final def getValue(): Int = reduce() match {
    case i: ScalaInt => i.value
    case _ => throw new NoValueDefined()
  }
}

object ScalaInt {
  def Min(listOfInt: ScalaInt*) = new MinScalaInt(listOfInt.toList)
}

