package fr.emn.criojo.virtualmachine

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 16, 2010
 * Time: 10:53:09 AM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.model._
import fr.emn.creole.core._
import fr.emn.creole.util._

import scala.actors.Actor._
import scala.actors.TIMEOUT

class ClientProxy(a:Atom) {
  val cmActor = actor{
    loop{
      react{
        case toSend: Atom =>
//          Logger.log(this.getClass, "outher_loop", "Atom to send: " + toSend)
          VirtualMachineService.addAtom(toSend)
          sender ! receiveWithin(5000) {
            case response:Atom =>
//              Logger.log(this.getClass, "inner_loop", "received response on actor: "+ response)
              response
            case TIMEOUT =>
              Logger.log(this.getClass, "inner_loop", "Hit timeout!")
              null
            case other =>
//              Logger.log(this.getClass, "inner_loop", "Received wrong answer type: " + other)
              null
          }
        case _ =>
          sender ! null
      }
    }
  }

  val localAtom = new Atom(a.relName,
    a.vars.map{
      case rv:RelVariable => rv.relation = new ProxyRelation(rv.relation.name, cmActor); rv
      case other => other
    }
  )

  /**
   * A synchronous send
   */
  def send:Option[Atom] = {
    val futureResponse = cmActor !! localAtom
    //Wait for the response, then forward it
    futureResponse.inputChannel.receive{
      case a:Atom => Some(a)
      case _ => None
    }
  }

}

