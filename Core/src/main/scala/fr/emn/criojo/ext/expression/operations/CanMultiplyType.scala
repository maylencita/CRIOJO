package fr.emn.criojo.ext.expression.operations

import fr.emn.criojo.ext.expression.CriojoType

trait CanMultiplyType {

  def multiply(v:CriojoType):CriojoType
}