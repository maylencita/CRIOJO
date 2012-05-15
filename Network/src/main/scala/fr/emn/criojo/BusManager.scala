package main.scala.fr.emn.criojo

import java.util.HashMap
import fr.emn.criojo.{DummyBusFactory, Message, AtomReceiveHandler, ActorCham}
import fr.emn.criojo.core.Atom
import fr.emn.criojo.network._


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */

class BusManager {

  var mapBus:HashMap[String, Bus] = new HashMap[String, Bus]()
  var busFactory:BusFactory = new BusHornetQFactory()

  @throws(classOf[BusException])
  def send(from:String, to:String, atom:Atom) {
    mapBus.get(from) match {
      case b:Bus => b.send(Message.atomToMessage(from, to, atom), to)
      case _ =>
    }
  }
  
  @throws(classOf[BusException])
  def addActor(actorCham:ActorCham) {
    val bus:Bus = new BusRemoteHornetQ("localhost", 5445, actorCham.name, "guest", "guest");

    bus.setReceiveHandler(new ReceiveHandler {
      def onReceive(p1: String) { actorCham.receiveHandler.onReceive(Message.parse(p1).atom.get) }
    })

    mapBus.put(actorCham.name, bus)
  }
}
