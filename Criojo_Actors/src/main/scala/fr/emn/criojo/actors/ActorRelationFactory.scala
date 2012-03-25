package fr.emn.criojo.actors

import fr.emn.criojo.core.factory.RelationFactory
import fr.emn.criojo.core._

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 25/03/12
 * Time: 10:02
 */
trait ActorRelationFactory extends RelationFactory{
  def createLocalRelation(name: String) = new LocalRelation(name)

  def createRemoteRelation(name: String) = null

  def createNativeRelation(name: String, f: (List[Term]) => Unit) = null
}

class ActorChannel(name:String,location:String) extends Channel{
  def notifyObservers(a: Atom){
    //Send atom to location
  }

  def copy(sol: Solution) = {
    val newChannel = new ActorChannel(name,location)
    this.observers.foreach(o => newChannel.addObserver(o))
    newChannel
  }
}