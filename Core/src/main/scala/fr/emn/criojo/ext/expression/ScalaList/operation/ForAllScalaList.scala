package fr.emn.criojo.ext.expression.ScalaList.operation

import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.core.datatype.{PatternNotMatchingException, Valuation, Expression, Pattern}
import fr.emn.criojo.ext.expression.ScalaList.ScalaList
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean

case class ForAllScalaList[A <: Pattern with Expression](f: (A) => ScalaBoolean,
    l: ScalaList[A]) extends ScalaBoolean {
  override def matches(expr: Expression): Boolean = expr match {
    case ForAllScalaList(ff, ll) => f == ff && l.matches(ll)
    case _ => false
  }

  override def getValuation(expr: Expression): Valuation = expr match {
    case ForAllScalaList(ff, ll) => l.getValuation(ll)
    case _ => throw new PatternNotMatchingException()
  }

  override def applyValuation(valuation: Valuation): Expression = {
    val expr = l.applyValuation(valuation).asInstanceOf[ScalaList[A]]

    expr.forall(f)
  }

  override def reduce(): Expression = {
    // Scala list required Boolean and not Scala Boolean so :
    //   * f: A => ScalaBoolean
    //   * g: ScalaBoolean => Boolean
    //   * g o f
    def g (b: ScalaBoolean): Boolean = b.getValue()

    WrapScalaBoolean(l.getValue.forall(g _ compose f))
  }
}


