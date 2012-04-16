package fr.emn.criojo.ext.expression.operations

import fr.emn.criojo.ext.expression.CriojoType

trait CanSubType {

  def sub(v:CriojoType):CriojoType
}