package fr.emn.criojo.core.model

/** From atoms in rules, the terms in atoms are pattern. */
trait Pattern extends Term {

  override def matches(exp: Expression): Boolean

  override def getValuation(exp: Expression): Valuation

  override def applyValuation(valuation: Valuation): Expression
}
