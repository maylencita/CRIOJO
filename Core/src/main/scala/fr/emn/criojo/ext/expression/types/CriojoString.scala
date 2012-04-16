package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.CriojoType
import fr.emn.criojo.ext.expression.operations.CanAddType

case class CriojoString(s:String) extends CriojoType with CanAddType {

  def add(o:CriojoType):CriojoType = CriojoString(o.toString()+s)

  def matches(that:CriojoType):Boolean = that match {
    case CriojoString(s2:String) => s.equals(s2)
    case _ => false
  }
}