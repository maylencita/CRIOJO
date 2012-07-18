package fr.emn.criojo.ext.expression.CriojoMap.operation

import fr.emn.criojo.core.datatype.{Valuation, Pattern, Expression}
import fr.emn.criojo.ext.expression.ScalaBoolean.ScalaBoolean
import fr.emn.criojo.ext.expression.CriojoMap.CriojoMap
import fr.emn.criojo.ext.expression.ScalaBoolean.constructor.WrapScalaBoolean

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
    def g (b: ScalaBoolean): Boolean = b.getValue()

    WrapScalaBoolean(m.getValue.forall(g _ compose p))
  }
}
