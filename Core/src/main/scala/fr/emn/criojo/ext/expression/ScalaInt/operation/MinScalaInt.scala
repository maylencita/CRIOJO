package fr.emn.criojo.ext.expression.ScalaInt.operation

import fr.emn.criojo.core.{Expression, Valuation}
import fr.emn.criojo.core.PatternNotMatchingException
import fr.emn.criojo.ext.expression.ScalaInt.ScalaInt
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

case class MinScalaInt(listOfInt:List[ScalaInt]) extends ScalaInt {
  override def getValuation(expr: Expression): Valuation = throw new PatternNotMatchingException

  override def applyValuation(valuation: Valuation): Expression = MinScalaInt(listOfInt.map(s => s.applyValuation(valuation).asInstanceOf[ScalaInt]).toList)

  override def reduce(): Expression = {
    def min(s1:ScalaInt, s2:ScalaInt):ScalaInt = (s1.reduce(), s2.reduce()) match {
      case (WrapScalaInt(i), WrapScalaInt(j)) => if (i<j) s1 else s2
      case _ => throw new PatternNotMatchingException
    }

    listOfInt.reduceRight(min(_, _))
  }

  override def matches(expr: Expression): Boolean = throw new PatternNotMatchingException
}

