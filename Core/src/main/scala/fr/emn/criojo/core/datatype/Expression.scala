package fr.emn.criojo.core.datatype

/** From atoms in solution, the terms in atoms are expression. */
trait Expression extends Term {
  override def reduce(): Expression
}
