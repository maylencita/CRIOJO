package fr.emn.criojo.examples

import fr.emn.criojo.core.engine.AtomGateway
import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.core.model.relation.RemoteMessage
import scala.collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/29/12
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
object LocalGateway extends AtomGateway{
  val agents = mutable.HashMap[String,Agent]()

  def exportAtom(atom: RemoteMessage) {

    agents.get(extractAgentLocation(atom.destination.url)) match{
      case Some(agent) =>
        println("[Gateway] Sending " + atom + " to " + agent)
        agent ! atom
      case _ => println("Agent " + atom.destination.url + " not found!")
    }
  }

  def addAgent(name:String, agent:Agent){
    agents.update(name,agent)
  }

  private def extractAgentLocation(url:String):String = {
    url.substring(0, url.lastIndexOf("."))
  }
}

