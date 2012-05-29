package fr.emn.criojo

import core.Atom
import ext.expression.Relation.ChannelLocation
import ext.expression.Relation.constructor.OutChannel
import ext.expression.ScalaInt.constructor.WrapScalaInt
import main.scala.fr.emn.criojo.BusManager
import network.ReceiveHandler
import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */

class NetworkTest {

  @Test
  def MessageArgsTest() {
    MessageArgsParser.parse("true,1,2,true,\"abcd\",4")
    MessageArgsParser.parse("")
    MessageArgsParser.parse("1")
  }

  @Test
  def MessageTest() {
    val message:Message = Message.parse("{'to':'cham2'; 'from':'cham1'; 'channel':'Request';'args':'true,1,2,true,\"abcd\",4'}")

    assert(message.to.get == "cham2")
    assert(message.from.get == "cham1")
    assert(message.channel.get == "Request")
    assert(message.args.get == "true,1,2,true,\"abcd\",4")
  }

  @Test
  def MessageGetAtomTest() {
    val message:Message = Message.parse("{'to':'cham2'; 'from':'cham1'; 'channel':'Request';'args':'true,1,2,true,\"abcd\",4'}")

    val atom:Atom = message.atom.get
    assert(atom.relation.name == "Request")
    assert(atom.patterns.size == 6)
  }

  @Test
  def CommunicationTest() {
    val busManager:BusManager = new BusManager()
    val cham1:ActorCham = new ActorCham("cham1", busManager)
    val cham2:ActorCham = new ActorCham("cham2", busManager)

    var result = false;

    cham2.receiveHandler = new AtomReceiveHandler {
      def onReceive(a:Atom) {
        result = true
        cham2.introduceAtom(a)
      }
    }

    var outChannel:OutChannel = new OutChannel("cham1Tocham2", new ChannelLocation("cham1","localhost", 80))

    cham1.send("cham2", outChannel(WrapScalaInt(1), WrapScalaInt(2)).head)

    Thread.sleep(500)

    assert(result)
  }

  def CommunicationNetworkTest() {
    val busManager:BusManager = new BusManager()
    val cham1:ActorCham = new ActorCham("cham1", busManager)

    var result = false;

    cham1.receiveHandler = new AtomReceiveHandler {
      def onReceive(a:Atom) {
        result = true
        cham1.introduceAtom(a)
      }
    }

    var outChannel:OutChannel = new OutChannel("cham1Tocham2", new ChannelLocation("cham1","localhost", 80))

    while (true) {
      println(result)
      cham1.send("cham2", outChannel(WrapScalaInt(1), WrapScalaInt(2)).head)
      Thread.sleep(1000)
    }

    assert(true)
  }
}
