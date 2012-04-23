package fr.emn.criojo.core

trait Expression extends Term {
  def reduce(): Expression
}
