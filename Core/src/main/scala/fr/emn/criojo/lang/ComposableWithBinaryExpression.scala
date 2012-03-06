package fr.emn.criojo.lang

import fr.emn.criojo.ext.expressions._
import fr.emn.criojo.core.Term


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 3/1/12
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */

trait ComposableWithBinaryExpression extends Term
{
  def add(that:Term):Expression = new SumExpr(this, that)
  def sub(that:Term):Expression = new SubExpr(this, that)
  def mult(that:Term):Expression = new MultExpr(this, that)
  def div(that:Term):Expression = new DivExpr(this, that)
  def isEqual(that:Term):Expression = new EqualExpr(this, that)
  def greaterThan(that:Term):Expression = new GreaterThanExpr(this, that)
  def lessThan(that:Term):Expression = new LessThanExpr(this, that)
}
