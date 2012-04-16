package fr.emn.criojo.ext.expression.operations

import fr.emn.criojo.ext.expression.CriojoType

trait CanDivideType {

  def divide(v:CriojoType):CriojoType
}