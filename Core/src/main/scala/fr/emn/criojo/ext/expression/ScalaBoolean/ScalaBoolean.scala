package fr.emn.criojo.ext.expression.ScalaBoolean

import fr.emn.criojo.core.datatype.{Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaInt.NoValueDefined
import operation.{NotScalaBoolean, AndScalaBoolean, OrScalaBoolean}

trait ScalaBoolean extends Pattern with Expression {
  def value: Boolean = {
    throw new NoValueDefined()
  }

  final def ||(that: ScalaBoolean): ScalaBoolean = new OrScalaBoolean(this, that)

  final def &&(that: ScalaBoolean): ScalaBoolean = new AndScalaBoolean(this, that)

  final def unary_! : ScalaBoolean = new NotScalaBoolean(this)

  final def getValue(): Boolean = reduce() match {
    case i: ScalaBoolean => i.value
    case _ => throw new NoValueDefined()
  }
}

