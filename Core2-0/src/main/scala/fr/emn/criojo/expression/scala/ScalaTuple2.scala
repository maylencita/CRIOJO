package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._
import scala.Tuple2

trait ScalaTuple2[+T1 <: Term,+T2 <: Term] extends Expression{
//  extends Tuple2[T1, T2](a,b) with Pattern {

  def _1:T1
  def _2:T2

  override def reduce():Expression = CriojoArrow(_1.reduce(), _2.reduce())
}

case class CriojoArrow[T1 <: Term,T2 <: Term](val _1:T1, val _2:T2) extends ScalaTuple2[T1,T2] with Pattern{
  /**
   * Verifies if this tuple matches an expression of
   * underlying type ScalaTuple2.
   */
  override def matches(expr: Expression): Boolean = expr match {
    case wst:ScalaTuple2[Expression,Expression] => this._1.matches(wst._1) && this._2.matches(wst._2)
    case _ => false
  }

  /**
   * Gets a valuation from an expression,
   * given that the underlying type is also a tuple.
   */
  override def getValuation(expr: Expression): Valuation = expr match {
    case wst: ScalaTuple2[Expression,Expression] =>
      val leftValuation = this._1 match{
        case x:Variable => Valuation(x -> wst._1)
        case _ => Valuation()
      }
      val rightValuation = this._2 match{
        case y:Variable => Valuation(y -> wst._2)
        case _ => Valuation()
      }
      leftValuation.union(rightValuation)

    case _ => Valuation()
  }

  /**
   * Transforms ScalaTuple2 < Pattern to ScalaTuple2 < Expression.
   */
  override def applyValuation(valuation: Valuation): Expression = {
    val _1Expr = _1.applyValuation(valuation)
    val _2Expr = _2.applyValuation(valuation)

    CriojoArrow(_1Expr,_2Expr)
  }

}