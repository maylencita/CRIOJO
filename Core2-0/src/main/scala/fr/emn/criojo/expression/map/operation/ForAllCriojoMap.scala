package fr.emn.criojo.expression.map.operation

import fr.emn.criojo.core.model.{Valuation, Pattern, Expression}
import fr.emn.criojo.expression.map.CriojoMap
import fr.emn.criojo.expression.scala.{WrapScalaBoolean, ScalaBoolean}

class ForAllCriojoMap[A <: Pattern with Expression,
B <: Pattern with Expression](p: ((A, B)) => ScalaBoolean, m: CriojoMap[A, B])
  extends ScalaBoolean {

  override def applyValuation(valuation: Valuation): Expression = {
    val expr = m.applyValuation(valuation).asInstanceOf[CriojoMap[A, B]]

    new ForAllCriojoMap[A, B](p, expr)
  }

  override def reduce(): Expression = {
    // Scala list required Boolean and not Scala Boolean so :
    //   * f: A => ScalaBoolean
    //   * g: ScalaBoolean => Boolean
    //   * g o f
    def g(b: ScalaBoolean): Boolean = b.getValue

    WrapScalaBoolean(m.getValue.forall(g _ compose p))
  }
}
