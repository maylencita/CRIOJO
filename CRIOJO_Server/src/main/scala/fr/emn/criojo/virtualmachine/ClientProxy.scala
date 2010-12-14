package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 16, 2010
 * Time: 10:53:09 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.model._
import fr.emn.criojo.core._
import fr.emn.criojo.util._

import java.net.URI

/**
 * For each request a ClientProxy is created.
 * A ClientProxy reacts when atoms should be send in the response.
 */
class ClientProxy(clientURI: URI) extends RelationObserver{
  var response:List[Atom] = List()

  override def receiveUpdate(atom:Atom){
    response = response :+ atom
  }

}

