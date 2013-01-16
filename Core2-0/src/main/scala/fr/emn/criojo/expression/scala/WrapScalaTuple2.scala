package fr.emn.criojo.expression.scala

import fr.emn.criojo.core.model._

class WrapScalaTuple2[T1 <: Expression, T2 <: Expression](t: (T1, T2))
  extends ScalaTuple2[T1, T2] with WrappedValue[(T1,T2)]{

  val _1 = t._1

  val _2 = t._2

  def self = t

  override def reduce(): Expression = this

  override def toString: String = t.toString()
}

object WrapScalaTuple2{
  def apply[A <: Expression,B <: Expression](x:A,y:B):WrapScalaTuple2[A,B] = new WrapScalaTuple2((x,y))
}