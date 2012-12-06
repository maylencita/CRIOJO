package fr.emn.criojo.network

import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.engine.Cham


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */

class AtomReceiveHandler(owner:Cham) extends ReceiveHandler{

  def onReceive(message:String){
    val inAtom = Message.parse(message).atom.get
    owner.introduceAtom(inAtom)
  }
}
