package fr.emn.criojo.core.model

trait Wrapper[+A <: Pattern with Expression] extends Pattern with Expression {

  def wrapped: A = throw new NoSuchElementException("No wrapped element")

  override def reduce(): Expression = wrapped.reduce()
}
