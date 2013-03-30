package fr.emn.criojo.core.model.relation

import fr.emn.criojo.core.model.Atom

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
abstract class LocalMessage(val relation:Relation) extends Atom{}

abstract class RemoteMessage(val relation:Relation) extends Atom{
  assert(!relation.name.isEmpty, "Channel name cannot be empty!")
  def destination:ChannelLocation
}
