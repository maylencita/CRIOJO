package fr.emn.criojo.expression.tuple2.constructor

import fr.emn.criojo.core.model._
import scala.Tuple2

class WrapScalaTuple2[T1 <: Expression, T2 <: Expression](t: (T1, T2))
  extends Tuple2[T1, T2](t._1,t._2) with Expression with WrappedValue[(T1,T2)]{

  /**
   * Caution, this method is applicable only when WrapScalaTuple2 is an expression
   * and not a Pattern.
   */
//  def _1: T1 = t._1

  /**
   * Caution, this method is applicable only when WrapScalaTuple2 is an expression
   * and not a Pattern.
   */
//  def _2: T2 = t._2

  def value = t
  def self = t

  /**
   * With type tuple2, only WrapScalaTuple2 could be in
   * solution, because ArrowAssocCriojoTuple2 is reduce as WrapScalaTuple2.
   * So matches no needs to test match with ArrowAssocCriojoTuple2.
   */
  override def matches(expr: Expression): Boolean = expr match {
    case wst: WrapScalaTuple2[T1, T2] => t == wst.value
    case _ => false
  }

  override def getValuation(exp: Expression): Valuation = Valuation()

  override def applyValuation(valuation: Valuation): Expression = WrapScalaTuple2(_1.applyValuation(valuation), _2.applyValuation(valuation))

  override def reduce(): Expression = WrapScalaTuple2(_1.reduce(), _2.reduce())

  override def toString: String = t.toString()
}

object WrapScalaTuple2{
  def apply[A <: Expression,B <: Expression](x:A,y:B):WrapScalaTuple2[A,B] = new WrapScalaTuple2((x,y))
}