package fr.emn.criojo

import network._


/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/15/12
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */

class DummyBus extends BusConnector {

  var receiveHandler:ReceiveHandler = null

  @throws(classOf[BusConnectorException])
  def send(s:String, s1:String) {
    if(receiveHandler != null)
      receiveHandler.onReceive(s1)
  }

  def setReceiveHandler(handler:ReceiveHandler) {
    receiveHandler = handler
  }
  
  def getName:String = "dummy"
  def isDisconected:Boolean = false
  def disconnect() {}
}

class DummyBusFactory extends BusConnectorFactory {

  @throws(classOf[BusConnectorFactoryException])
  def createConnector(s:String):BusConnector = new DummyBus()
}
