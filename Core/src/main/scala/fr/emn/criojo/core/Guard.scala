package fr.emn.criojo.core

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jun 16, 2010
 * Time: 11:32:59 AM
 * To change this template use File | Settings | File Templates.
 */

trait Guard {
  def empty:Boolean
  def eval(valuation:Valuation):Boolean
}

object EmptyGuard extends Guard{
  override def empty = true
  override def eval(vals:Valuation):Boolean = true
}

