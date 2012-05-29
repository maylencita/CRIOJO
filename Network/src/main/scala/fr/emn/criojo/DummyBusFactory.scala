package fr.emn.criojo

import network._


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/15/12
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */

class DummyBus extends Bus {

  var receiveHandler:ReceiveHandler = null

  @throws(classOf[BusException])
  def send(s:String, s1:String) {
    if(receiveHandler != null)
      receiveHandler.onReceive(s1)
  }

  def setReceiveHandler(handler:ReceiveHandler) {
    receiveHandler = handler
  }
}

class DummyBusFactory extends BusFactory {

  @throws(classOf[BusFactoryException])
  def createBus(s:String):Bus = new DummyBus()
}
