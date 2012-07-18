package fr.emn.criojo.core.datatype

trait Wrapper[+A <: Pattern with Expression] extends Pattern with Expression {

  def wrapped: A = throw new NoSuchElementException("No wrapped element")

  override def reduce(): Expression = wrapped.reduce()
}
