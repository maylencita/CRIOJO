package fr.emn.criojo

import core.Atom
import core.factory.DefaultFactory
import lang.Cham
import main.scala.fr.emn.criojo.BusManager

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

  override def introduceAtom(atom : fr.emn.criojo.core.Atom) {
    super.introduceAtom(atom)
  }
  
  var receiveHandler:AtomReceiveHandler = new AtomReceiveHandler {
    def onReceive(a: Atom) {
      if(filterRules.contains(name+"."+a.relation.name)) {
        introduceAtom(a)
      }
    }
  }

  def send(to:String, a:Atom) {
    busManager.send(name, to, a)
  }
}
