package fr.emn.criojo.core.model

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 12/4/12
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
trait WrappedValue[T] {
  def self:T
}

object WrappedValue{
  def unapply[T](wv:WrappedValue[T]):Option[T] = Some(wv.self)
}
