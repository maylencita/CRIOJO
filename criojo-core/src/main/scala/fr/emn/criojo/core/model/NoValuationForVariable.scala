package fr.emn.criojo.core.model

/** No Valuation for given variable find Exception. */
class NoValuationForVariable(variable: Variable)
  extends Exception("No valuation for variable " + variable.name) {}
