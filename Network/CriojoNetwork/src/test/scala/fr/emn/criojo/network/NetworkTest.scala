package fr.emn.criojo.network

//import fr.emn.criojo.core.Atom
//import fr.emn.criojo.ext.expression.Relation.ChannelLocation
//import fr.emn.criojo.ext.expression.Relation.constructor.OutChannel
//import fr.emn.criojo.ext.expression.CriojoInt.constructor.WrapScalaInt

import org.junit.Test
import fr.emn.criojo.core.model.Atom
import fr.emn.criojo.core.model.relation.Channel

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
    MessageArgsParser.parse("a.b.c")
    MessageArgsParser.parse("@a")
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
    assert(atom.terms.size == 6)
  }

  @Test
  def CommunicationTest() {
    var result = false
    val busFactory:BusConnectorFactory = new BusConnectorLocalHornetQFactory()
    val cham1 = new BusClientAgent("cham1", "5446", busFactory){
      val Ping = OutputChannel("Ping","cham2")
      val Pong = InputChannel("Pong")
      val Start = LocalRel
      val Log = NativeRel(l => println(l.mkString(" ")))
      val AssertEq = NativeRel{
        case x::y::_ => result = x.equals(y)
        case _ =>
      }
      val x = Var[Int]

      rules(
        Start() --> (Ping(Pong,1) & Log("[cham1] Sending 1")),
        Pong(x) --> (Log("[cham1] Received", x) & AssertEq(x,2))
      )
    }

    val cham2 = new BusClientAgent("cham2", "5446", busFactory){
      val Ping = InputChannel("Ping")
      val Log = NativeRel(l => println(l.mkString(" ")))
      val x = Var[Int]
      val k = Var[Channel]

      rules(
        Ping(k,x) --> k(x+1)
      )
    }

    cham1 ! cham1.Start()

    Thread.sleep(1000)
    assert(result)
  }

//  def CommunicationNetworkTest() {
//    val busManager:BusManager = new BusManager()
//    val cham1:ActorCham = new ActorCham("cham1", busManager)
//
//    var result = false;
//
//    cham1.receiveHandler = new AtomReceiveHandler {
//      def onReceive(a:Atom) {
//        result = true
//        cham1.introduceAtom(a)
//      }
//    }
//
//    var outChannel:OutChannel = new OutChannel("cham1Tocham2", new ChannelLocation("cham1"))
//
//    while (true) {
//      println(result)
//      cham1.send("cham2", outChannel(WrapScalaInt(1), WrapScalaInt(2)).head)
//      Thread.sleep(1000)
//    }
//
//    assert(true)
//  }
}
