package fr.emn.criojo.expression

import fr.emn.criojo.core.model.Expression
import scala.{WrapScalaInt, ScalaBoolean, ScalaInt}

/** Wrap Scala String type in Criojo */
trait CriojoString extends Expression {
  def value: String = {
    throw new NoValueDefined()
  }

  def length: ScalaInt

  def size: ScalaInt = this.length

  def +(that: CriojoString): CriojoString

  def <=>(that: CriojoString): CriojoBoolean

  def !<=>(that: CriojoString): CriojoBoolean = !(this <=> that)

  def isEmpty: CriojoBoolean = this.length <=> WrapScalaInt(0)

}
