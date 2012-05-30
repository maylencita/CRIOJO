package fr.emn.criojo

import ext.expression.Relation.constructor.{OutChannel, Channel, LocalRelation}
import ext.expression.ScalaInt.constructor.WrapScalaInt
import ext.expression.ScalaInt.VarScalaInt
import ext.expression.Relation.ChannelLocation

import main.scala.fr.emn.criojo.BusManager
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

        val goodChannel = Channel("goodChannel", new ChannelLocation("receiver", "localhost", 20))
        val badChannel = Channel("badChannel", new ChannelLocation("receiver", "localhost", 20))
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

    val goodOutChannel:OutChannel = new OutChannel("goodChannel", new ChannelLocation("sender","localhost", 80))
    val badOutChannel:OutChannel = new OutChannel("badChannel", new ChannelLocation("sender","localhost", 80))

    sender.send("receiver", goodOutChannel(WrapScalaInt(1)).head)
    sender.send("receiver", badOutChannel(WrapScalaInt(1)).head)

    Thread.sleep(500)

    assert(testOk)
  }
}
