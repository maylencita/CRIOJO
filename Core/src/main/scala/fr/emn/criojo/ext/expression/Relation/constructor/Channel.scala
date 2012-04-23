package fr.emn.criojo.ext.expression.Relation.constructor

import fr.emn.criojo.ext.expression.Relation.{Relation, ChannelLocation}

case class Channel (name: String, location:ChannelLocation) extends Relation {
  def this(name:String, url:String, port:Int) = this(name, new ChannelLocation(name, url, port))
}
