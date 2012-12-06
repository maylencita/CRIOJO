package fr.emn.criojo.network

import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.core.engine.AtomGateway
import fr.emn.criojo.core.model.relation.RemoteMessage

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 12/3/12
 * Time: 8:20 PM
 * To change this template use File | Settings | File Templates.
 */
class BusClientAgent(override val location:String, val port:String, val busFactory:BusConnectorFactory) extends Agent with NetworkObject{
  val bus =  busFactory.createConnector(port+":"+this.location)
  bus.setReceiveHandler(new AtomReceiveHandler(this))

  val origin = this.location
  override val gateway = new AtomGateway {
    def exportAtom(atom: RemoteMessage) {
      val message = Message.atomToMessage(origin, atom.destination.url, atom)
      val destination = atom.destination.url.substring(0, atom.destination.url.lastIndexOf("."))
      bus.send(message, destination)
    }
  }

  this.start()

  def getName = this.location

//  override protected def finalize(){
//    println("[CHAM] Disconnecting agent "+location+".")
//    bus.disconnect()
//  }
}
