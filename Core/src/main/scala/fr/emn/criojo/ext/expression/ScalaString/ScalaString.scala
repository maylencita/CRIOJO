package fr.emn.criojo.ext.expression.ScalaString

import constructor.LengthScalaString
import operation._
import fr.emn.criojo.core.datatype.{Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt

/** Wrap Scala String type in Criojo */
trait ScalaString extends Pattern with Expression {
  def value: String = {
    throw new NoValueDefined()
  }

  final def length(): ScalaInt = new LengthScalaString(this)

  final def +(that: ScalaString): ScalaString = new AddScalaString(this, that)

  final def <=>(that: ScalaString): ScalaBoolean = new EqualScalaString(this, that)

  final def !<=>(that: ScalaString): ScalaBoolean = !(this <=> that)

  final def getValue(): String = reduce() match {
    case i: ScalaString => i.value
    case _ => throw new NoValueDefined()
  }
}

