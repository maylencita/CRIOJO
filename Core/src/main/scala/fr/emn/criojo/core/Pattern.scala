package fr.emn.criojo.core

trait Pattern extends Term {

  def matches(exp:Expression):Boolean

  def getValuation(exp: Expression): Valuation

  /**Transform variable by value */
  def applyValuation(valuation: Valuation): Expression
}
