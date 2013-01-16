package fr.emn.criojo.expression

import fr.emn.criojo.core.model.{Expression, Pattern}

/** Wrap Scala Boolean type */
trait CriojoBoolean extends Expression {
  def value: Boolean = {
    throw new NoValueDefined()
  }

  def ||(that: CriojoBoolean): CriojoBoolean

  def &&(that: CriojoBoolean): CriojoBoolean

  def unary_! : CriojoBoolean

}
