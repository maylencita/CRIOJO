package fr.emn.criojo.ext.expression.types

import fr.emn.criojo.ext.expression.CriojoType
import fr.emn.criojo.ext.expression.operations.{CanMultiplyType, CanAddType}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/11/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */

case class CriojoBoolean(b:Boolean) extends CriojoType with CanAddType with CanMultiplyType {

  def add(o:CriojoType):CriojoType = o match {
    case CriojoBoolean(v:Boolean) => CriojoBoolean(v || b)
  }

  def multiply(o:CriojoType):CriojoType = o match {
    case CriojoBoolean(v:Boolean) => CriojoBoolean(v && b)
  }

  def matches(that:CriojoType):Boolean = that match {
    case CriojoBoolean(v:Boolean) => v == b
    case _ => false
  }
}
