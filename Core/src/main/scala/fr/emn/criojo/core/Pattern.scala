package fr.emn.criojo.core

import fr.emn.criojo.ext.expression.Expression

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 4/18/12
 * Time: 5:28 PM
 * To change this template use File | Settings | File Templates.
 */

trait Pattern {
  def matches(e:Expression):Valuation
  def apply(vals:Valuation):Expression
}
