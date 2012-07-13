package fr.emn.criojo.network

import fr.emn.criojo.core.factory.DefaultFactory
import fr.emn.criojo.lang.Cham
import fr.emn.criojo.core.{ReactionStrategy, Atom}
import scala.fr.emn.criojo.network.NetworkReactionStrategy
import fr.emn.criojo.ext.expression.Relation.constructor.Channel

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */

class ActorCham(val name:String, val busManager:BusManager) extends Cham  with NetworkObject with DefaultFactory {

  busManager.addActor(this)

  def getName:String = name

  override def reactionStrategy:ReactionStrategy = new NetworkReactionStrategy()

  override def introduceAtom(atom : fr.emn.criojo.core.Atom) {
    super.introduceAtom(atom)
    executeRules()
  }
  
  var receiveHandler:AtomReceiveHandler = new AtomReceiveHandler {
    def onReceive(a: Atom) {
      //if(filterRules.contains(name+"."+a.relation.name)) {
        val convertAtom:Atom = new Atom(new Channel(a.relation.name), a.patterns)
        introduceAtom(convertAtom)
      //}
    }
  }

  def send(to:String, a:Atom) {
    busManager.send(name, to, a)
  }
}
