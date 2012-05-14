package main.scala.fr.emn.criojo

import fr.emn.criojo.network.{ReceiveHandler, Bus, BusFactory}
import java.util.HashMap
import scala.Option
import fr.emn.criojo.{Message, AtomReceiveHandler, ActorCham}


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */

class BusManager {

  var mapReceiver:HashMap[String, AtomReceiveHandler] = new HashMap[String, AtomReceiveHandler]()
    
  // TODO: add here a call to the BusFactory implementation
  var busFactory:BusFactory = null

  // TODO: change the arguments to match with those of the BusFactory implementation
  var bus:Bus = busFactory.createBus("url:231.7.7.7")

  bus.setReceiveHandler(new ReceiveHandler {
    def onReceive(p1: String) {

      var message:Message = Message.parse(p1)

      mapReceiver.get(message.to.get) match {
        case (handler:AtomReceiveHandler) => handler.onReceive(message.atom.get)
        case _ =>
      }
    }
  })
  
  def addActor(actorCham:ActorCham) {
    mapReceiver.put(actorCham.name, actorCham.receiveHandler)
  }
}
