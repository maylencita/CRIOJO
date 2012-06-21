package fr.emn.criojo.network

import fr.emn.criojo.core.Converters._
import fr.emn.criojo.core.Atom
import fr.emn.criojo.ext.expression.Relation.ChannelLocation
import fr.emn.criojo.ext.expression.Relation.constructor.{LocalRelation, OutChannel}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt

import org.junit.Test
import javax.management.relation.Relation
import fr.emn.criojo.lang.Molecule

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/14/12
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */

class NetworkTest {

  @Test
  def TypesTest() {
    MessageParser.parseExp("\"a\"")
    MessageParser.parseExp("\"asa\"")
    MessageParser.parseExp("1")
    MessageParser.parseExp("122")
    MessageParser.parseExp("true")
    MessageParser.parseExp("false")
    MessageParser.parseExp("\"@a\"")
    MessageParser.parseExp("\"@asa\"")
    MessageParser.parseExp("\"@a.b\"")
    MessageParser.parseExp("\"@asa.c.d.e\"")
  }

  @Test
  def PropertiesTest() {
    MessageParser.parseProp("\"to\":\"cham2\"")
    MessageParser.parseProp("\"from\":\"cham1\"")
    MessageParser.parseProp("\"channel\":\"Request\"")
    MessageParser.parseProp("\"args\":[true,1,2,true,\"abcd\",4]")
  }

  @Test
  def MessageArgsTest() {
    MessageParser.parseArgs("[true,1,2,true,\"abcd\",4]")
    MessageParser.parseArgs("[]")
    MessageParser.parseArgs("[1]")
    MessageParser.parseArgs("[\"@a.b.c\"]")
    MessageParser.parseArgs("[\"@a\"]")
    MessageParser.parseArgs("[\"a\"]")
  }

  @Test
  def MessageJSONTest() {
    Message.parse("{\"to\":\"cham2\", \"from\":\"cham1\", \"channel\":\"Request\",\"args\":[true,1,2,true,\"abcd\",4]}")
    Message.parse("{\"to\":\"@serverchat\", \"from\":\"@user.jonathan.in\", \"channel\":\"Request\",\"args\":[true,1,2,true,\"abcd\",4]}")
    Message.parse("{\"to\":\"server.chat\", \"from\":\"@user.jonathan.in\", \"channel\":\"@server.chat.inscription\",\"args\":[true,1,2,true,\"abcd\",4]}")
    Message.parse("{\"to\":\"server.chat\", \"from\":\"@user.jonathan.in\", \"channel\":\"@server.chat.inscription\",\"args\":[\"@user.jonathan.in\"]}")
  }

  @Test
  def MessageTest() {
    val message:Message = Message.parse("{\"to\":\"cham2\", \"from\":\"cham1\", \"channel\":\"Request\",\"args\":[true,1,2,true,\"abcd\",4]}")

    assert(message.to.get == "cham2")
    assert(message.from.get == "cham1")
    assert(message.channel.get == "Request")
    //assert(message.args.toString == "Some([true,1,2,true,\"abcd\",4])")
  }

  @Test
  def MessageConversionTest() {

    val a:Atom = LocalRelation("abc")("1",2,3).head
    val aToStr = Message.atomToMessage("look", "lack", a)
    var result = Message.parse(aToStr)
    val aGenerated = result.atom.get
    assert(a.toString() == aGenerated.toString())
  }

  @Test
  def MessageGetAtomTest() {
    val message:Message = Message.parse("{\"to\":\"cham2\", \"from\":\"cham1\", \"channel\":\"Request\",\"args\":[true,1,2,true,\"abcd\",4]}")

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

    var outChannel:OutChannel = new OutChannel("cham1Tocham2", new ChannelLocation("cham1"))

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

    var outChannel:OutChannel = new OutChannel("cham1Tocham2", new ChannelLocation("cham1"))

    while (true) {
      println(result)
      cham1.send("cham2", outChannel(WrapScalaInt(1), WrapScalaInt(2)).head)
      Thread.sleep(1000)
    }

    assert(true)
  }
}
