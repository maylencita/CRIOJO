package fr.emn.criojo.ext.expression.CriojoTuple2.constructor

import fr.emn.criojo.core.datatype.{Valuation, Var, Expression, Pattern}
import fr.emn.criojo.ext.expression.CriojoTuple2.CriojoTuple2

final case class ArrowAssocCriojoTuple2[+T1 <: Pattern with Expression,
  +T2 <: Pattern with Expression](val _1: T1, val _2: T2)
  extends CriojoTuple2[T1, T2] {

  /**
   * Matches is done on element in solution. These elements are Expression, so
   * only WrapScalaTuple2 & ArrowAssocCriojoTuple2 are possible with
   * no variables.
   *
   * But the reduce of ArrowAssocCriojoTuple2 is WrapScalaTuple2, so only
   * WrapScalaTuple2 could not be in solution. This will largely simplified the
   * match algorithm.
   */
  override def matches(expr: Expression): Boolean = expr match {
    case wst: WrapScalaTuple2[T1, T2] => {
      val _1Check: Boolean = _1 match {
        case _1Var: Var[T1] => _1Var.matches(wst._1)
        case _ => _1 == wst._1
      }

      val _2Check: Boolean = _2 match {
        case _2Var: Var[T2] => _2Var.matches(wst._2)
        case _ => _2 == wst._2
      }

      _1Check && _2Check
    }
    case _ => false
  }

  /**
   * GetValuation is done on element in solution (already matches). These
   * elements are Expression, so only WrapScalaTuple2 & ArrowAssocCriojoTuple2
   * are possible with no variables.
   *
   * But the reduce of ArrowAssocCriojoTuple2 produce a WrapScalaTuple2, so only
   * WrapScalaTuple2 could be in solution. This will largely simplified the
   * match algorithm.
   */
  override def getValuation(expr: Expression): Valuation = expr match {
    case wst: WrapScalaTuple2[T1, T2] => {
     val _1Vals: Valuation = _1 match {
       case _1Var: Var[T1] => Valuation(_1Var -> wst._1)
       case _ => Valuation()
     }

      val _2Vals: Valuation = _2 match {
        case _2Var: Var[T2] => Valuation(_2Var -> wst._2)
        case _ => Valuation()
      }

      _1Vals.union(_2Vals)
    }
    case _ => Valuation()
  }

  /**
   * Transform ArrowAssocCriojoTuple2 Pattern to ArrowAssocCriojoTuple2
   * Expression.
   */
  override def applyValuation(valuation: Valuation): Expression = {
    val _1Expr = _1.applyValuation(valuation).asInstanceOf[T1]
    val _2Expr = _2.applyValuation(valuation).asInstanceOf[T2]

    new ArrowAssocCriojoTuple2[T1, T2](_1Expr, _2Expr)
  }


  /** Reduces the current constructor to a WrapScalaTuple2 constructor. */
  override def reduce(): Expression =
    WrapScalaTuple2[T1, T2](Tuple2[T1, T2](_1.reduce().asInstanceOf[T1],
      _2.reduce().asInstanceOf[T2]))
}
