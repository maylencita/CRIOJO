package fr.emn.criojo.ext.expression.ScalaList.constructor

import fr.emn.criojo.core.datatype.{Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaList.ScalaList

case class WrapScalaList[A <: Pattern with Expression](l: List[A]) extends ScalaList[A] {

  /**
   * Caution, this method is applicable only when WrapScalaList is an expression
   * and not a Pattern.
   */
  def head: A = l.head

  /**
   * Caution, this method is applicable only when WrapScalaList is an expression
   * and not a Pattern.
   */
  def tail[B >: A <: Pattern with Expression]: WrapScalaList[B] =
    new WrapScalaList[B](l.tail)

  override def value = l

  /**
   * With type ScalaList, only WrapScalaList or WrapScalaNil could be in
   * solution, because WrapScalaColonColon is reduce as WrapScalaList.
   * So matches no needs to test match with WrapScalaColonColon.
   */
  override def matches(expr: Expression):Boolean = expr match {
    case WrapScalaNil => l == Nil
    case wsl: WrapScalaList[A] => l == wsl.value
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation();

  override def applyValuation(valuation: Valuation): Expression = this

  override def reduce(): Expression = this

  override def toString :String = l.toString
}

