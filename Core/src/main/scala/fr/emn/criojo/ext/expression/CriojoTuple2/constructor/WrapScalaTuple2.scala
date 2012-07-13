package fr.emn.criojo.ext.expression.CriojoTuple2.constructor

import fr.emn.criojo.core.datatype.{Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.CriojoTuple2.CriojoTuple2

case class WrapScalaTuple2[T1 <: Pattern with Expression,
  T2 <: Pattern with Expression](t: Tuple2[T1, T2])
  extends CriojoTuple2[T1, T2] {

    /**
     * Caution, this method is applicable only when WrapScalaTuple2 is an expression
     * and not a Pattern.
     */
    def _1: T1 = t._1

    /**
     * Caution, this method is applicable only when WrapScalaTuple2 is an expression
     * and not a Pattern.
     */
    def _2: T2 = t._2

    override def value = t

    /**
     * With type CriojoTuple2, only WrapScalaTuple2 could be in
     * solution, because ArrowAssocCriojoTuple2 is reduce as WrapScalaTuple2.
     * So matches no needs to test match with ArrowAssocCriojoTuple2.
     */
    override def matches(expr: Expression):Boolean = expr match {
      case wst: WrapScalaTuple2[T1, T2] => t == wst.value
      case _ => false
    }

    override def getValuation(exp: Expression): Valuation = Valuation()

    override def applyValuation(valuation: Valuation): Expression = this

    override def reduce(): Expression = this

    override def toString :String = t.toString()
}
