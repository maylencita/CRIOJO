package fr.emn.criojo.network

import java.util.HashMap
import fr.emn.criojo.core.engine.{Cham, AtomGateway}
import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.model.relation.RemoteMessage

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */

//class BusManager(){
//
//  val mapBus:HashMap[String, Cham] = new HashMap[String, Cham]()
//
//  def addAgent(agent:Cham){
//    agent.gateway match {
//      case cg:BusGateway =>
//         cg.bus.setReceiveHandler(new ReceiveHandler {
//           def onReceive(message: String) {
//             val inAtom = Message.parse(message).atom.get
//             agent.introduceAtom(inAtom)
//           }
//         })
//      case _ => //Won't work
//    }
//    mapBus.put(agent.location, agent)
//  }
//
//  def exportAtom(atom: RemoteMessage) {
//    bus.send(Message.atomToMessage(owner.location, atom.destination, atom), atom)
//  }


//  var mapBus:HashMap[String, BusConnector] = new HashMap[String, BusConnector]()
////  var busFactory:BusConnectorFactory = new BusConnectorLocalHornetQFactory()
//
//  @throws(classOf[BusConnectorException])
//  def send(from:String, to:String, atom:Atom) {
//    mapBus.get(from) match {
//      case b:BusConnector => b.send(Message.atomToMessage(from, to, atom), to)
//      case _ =>
//    }
//  }
//
//  @throws(classOf[BusConnectorException])
//  def addActor(actorCham:ActorCham) {
//    val bus:BusConnector = busFactory.createConnector("5445:"+actorCham.name)
//
//    bus.setReceiveHandler(new ReceiveHandler {
//      def onReceive(p1: String) {
//        actorCham.receiveHandler.onReceive(Message.parse(p1).atom.get)
//      }
//    })
//
//    mapBus.put(actorCham.name, bus)
//  }

//}
