package fr.emn.creole.model

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 18, 2010
 * Time: 2:21:37 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core._
import fr.emn.creole.client._

import java.net.URI;

class RemoteRelation(name:String,url:URI) extends Relation(name, false){

  override def notifyObservers(a: Atom){
    val client = new CreoleClient()
    client.exportAtom(a, url)
  }
}