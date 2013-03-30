package fr.emn.criojo.core.engine

import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.model.relation.{RemoteMessage,ChannelLocation}

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/26/12
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
trait AtomGateway {
  def exportAtom(atom:RemoteMessage)
}
