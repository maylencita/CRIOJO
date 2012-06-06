package fr.emn.criojo.network

import fr.emn.criojo.ext.expression.Relation.constructor.{OutChannel, Channel, LocalRelation}
import fr.emn.criojo.ext.expression.ScalaInt.constructor.WrapScalaInt
import fr.emn.criojo.ext.expression.ScalaInt.VarScalaInt
import fr.emn.criojo.ext.expression.Relation.ChannelLocation

import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: jonathan
 * Date: 5/30/12
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */

class FirewallTest {

  @Test
  def FirewallTest() {

    val busManager:BusManager = new BusManager()

    val sender:ActorCham = new ActorCham("sender", busManager){
      rules()
    }

    var testOk:Boolean = false

    val firewall:Firewall = new Firewall {

      filterRules = List("receiver.goodChannel")

      val receiver = new ActorCham("receiver", busManager) with ActorChamDebug {

        val goodChannel = Channel("goodChannel", new ChannelLocation("goodChannel"))
        val badChannel = Channel("badChannel", new ChannelLocation("badChannel"))
        val Nat = NativeRel {
          case _ => testOk = true
        }
        val B = LocalRelation("B")
        val x = VarScalaInt()

        rules(
          goodChannel(x) --> Nat(x),
          badChannel(x) --> Nat(x)
        )
      }

      receiver.enableSolutionTrace()
      receiver.enableStreamingTrace()

      receiver.introduceAtom(receiver.goodChannel(WrapScalaInt(2)).head)
      receiver.executeRules()
      children = List(receiver)
    }

    firewall.sendFilterRules()

    val goodOutChannel:OutChannel = new OutChannel("goodChannel", new ChannelLocation("goodChannel"))
    val badOutChannel:OutChannel = new OutChannel("badChannel", new ChannelLocation("badChannel"))

    sender.send("receiver", goodOutChannel(WrapScalaInt(1)).head)
    sender.send("receiver", badOutChannel(WrapScalaInt(1)).head)

    Thread.sleep(500)

    assert(testOk)
  }
}
