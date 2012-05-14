package fr.emn.criojo

import core.Atom
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
}
