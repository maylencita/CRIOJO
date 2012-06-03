package fr.emn.criojo.ext.expression.Relation.constructor

import fr.emn.criojo.ext.expression.Relation.{ChannelLocation, Relation}

case class OutChannel(name: String, location:ChannelLocation) extends Relation {
  def this(name:String) = this(name, new ChannelLocation(name))
}
