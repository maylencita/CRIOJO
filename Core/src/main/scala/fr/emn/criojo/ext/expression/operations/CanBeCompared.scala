package fr.emn.criojo.ext.expression.operations

import fr.emn.criojo.ext.expression.CriojoType

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/12/12
 * Time: 2:23 AM
 * To change this template use File | Settings | File Templates.
 */

trait CanBeCompared {

  def isEqual(v:CriojoType):CriojoType
  def isDifferent(v:CriojoType):CriojoType
  def lessThan(v:CriojoType):CriojoType
  def lessOrEquals(v:CriojoType):CriojoType
  def greaterThan(v:CriojoType):CriojoType
  def greaterOrEquals(v:CriojoType):CriojoType
}
