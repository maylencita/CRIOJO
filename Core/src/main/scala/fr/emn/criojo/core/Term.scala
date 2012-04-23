package fr.emn.criojo.core

trait Term {

  def matches(exp:Expression): Boolean = throw new Exception("Not implemented in TERM")

  def getValuation(exp: Expression): Valuation = throw new Exception("Not implemented in TERM")

  def applyValuation(valuation: Valuation): Expression = throw new Exception("Not implemented in TERM")

  def reduce(): Expression = throw new Exception("Not implemented in TERM")
}
