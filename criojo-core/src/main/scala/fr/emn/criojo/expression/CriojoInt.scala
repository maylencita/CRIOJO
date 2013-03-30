package fr.emn.criojo.expression

import fr.emn.criojo.core.model.Expression

/** Wrap Scala Int type */
trait CriojoInt extends Expression {
  def value: Int = {
    throw new NoValueDefined()
  }

  def +(that: CriojoInt): CriojoInt

  def -(that: CriojoInt): CriojoInt

  def *(that: CriojoInt): CriojoInt

  def /(that: CriojoInt): CriojoInt

  def <=>(that: CriojoInt): CriojoBoolean

  def !<=>(that: CriojoInt): CriojoBoolean

  def >(that: CriojoInt): CriojoBoolean

  def >=(that: CriojoInt): CriojoBoolean

  def <(that: CriojoInt): CriojoBoolean

  def <=(that: CriojoInt): CriojoBoolean

}
