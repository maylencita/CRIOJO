package fr.emn.criojo.lang

import fr.emn.criojo.core.Term
import fr.emn.criojo.ext.expressions._

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/1/12
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */

trait CompositionOperators extends Term {

  def +(that: Term):Expression = new SumExpr(this, that)
  def -(that: Term):Expression = new SubExpr(this, that)
  def *(that: Term):Expression = new MultExpr(this, that)
  def /(that: Term):Expression = new DivExpr(this, that)
  def ==(that: Term):Expression = new EqualExpr(this, that)
  def >(that: Term):Expression = new GreaterThanExpr(this, that)
  def <(that: Term):Expression = new LessThanExpr(this, that)
}
