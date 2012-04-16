package fr.emn.criojo.ext.expression.operations

import fr.emn.criojo.ext.expression.CriojoType

trait CanAddType {

  def add(v:CriojoType):CriojoType
}