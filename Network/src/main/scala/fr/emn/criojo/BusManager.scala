package main.scala.fr.emn.criojo

import java.util.HashMap
import fr.emn.criojo.{DummyBusFactory, Message, AtomReceiveHandler, ActorCham}
import fr.emn.criojo.core.Atom
import fr.emn.criojo.network._

import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */

class BusManager {

  var mapBus:HashMap[String, BusConnector] = new HashMap[String, BusConnector]()
  var busFactory:BusConnectorFactory = new BusConnectorLocalHornetQFactory()

  @throws(classOf[BusConnectorException])
  def send(from:String, to:String, atom:Atom) {
    mapBus.get(from) match {
      case b:BusConnector => b.send(Message.atomToMessage(from, to, atom), to)
      case _ =>
    }
  }
  
  @throws(classOf[BusConnectorException])
  def addActor(actorCham:ActorCham) {
    val bus:BusConnector = busFactory.createConnector("5445:"+actorCham.name)

    bus.setReceiveHandler(new ReceiveHandler {
      def onReceive(p1: String) { actorCham.receiveHandler.onReceive(Message.parse(p1).atom.get) }
    })

    mapBus.put(actorCham.name, bus)
  }
}
