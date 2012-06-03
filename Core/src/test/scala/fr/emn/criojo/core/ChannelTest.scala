package fr.emn.criojo.core

import org.junit.Test
import fr.emn.criojo.core.Converters._
import fr.emn.criojo.ext.expression.Relation.{ChannelLocation, VarChannel}
import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, Channel}

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 6/1/12
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */

class ChannelTest {

  @Test
  def testChannel() {
    
    val v:VarChannel = VarChannel("varChannel")
    val inChannel:Channel = Channel("agentTest.inA", new ChannelLocation("agentTest.inA"))
    val outChannel:OutChannel = OutChannel("agentTest.outA", new ChannelLocation("agentTest.outA"))
    
    assert(v.matches(inChannel))
    assert(v.matches(outChannel))

    assert(v.getValuation(inChannel).size!=0)
    assert(v.getValuation(outChannel).size!=0)

    assert(true)
  }
}
