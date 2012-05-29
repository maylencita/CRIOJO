package fr.emn.criojo

import core.Atom
import core.factory.DefaultFactory
import lang.Cham
import main.scala.fr.emn.criojo.BusManager
import network.{Bus, ReceiveHandler}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */

class ActorCham(val name:String, val busManager:BusManager) extends Cham  with DefaultFactory {

  busManager.addActor(this)

  var receiveHandler:AtomReceiveHandler = new AtomReceiveHandler {
    def onReceive(a: Atom) {
      introduceAtom(a)
    }
  }

  def send(to:String, a:Atom) {
    busManager.send(name, to, a)
  }
}
