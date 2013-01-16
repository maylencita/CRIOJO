package fr.emn.criojo.network


import org.junit.{Assert, Test}
import Assert._

import fr.emn.criojo.expression.scala.{WrapScalaInt, IntVar}
import fr.emn.criojo.dsl.Empty

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

    val busFactory:BusConnectorFactory = new BusConnectorLocalHornetQFactory()

    //An agent without rules
    val sender = new BusClientAgent("sender", "5445",busFactory){
      val GoodChannel = OutputChannel("goodChannel","receiver")
      val BadChannel = OutputChannel("badChannel","receiver")
      val T1,T2 = LocalRel

      rules(
        Empty --> Abs(T1()) ?: (GoodChannel(1) & T1()),
        Empty --> Abs(T2()) ?: (BadChannel(2) & T2())
      )
    }

    var testOk:Int = 0

//    val firewall:Firewall = new Firewall {
//
//      filterRules = List("receiver.goodChannel")
//
//      val receiver = new BusClientAgent("receiver", "5445", busFactory){ //with ActorChamDebug {
//
//        val goodChannel = InputChannel("goodChannel")
//        val badChannel = InputChannel("badChannel")
//        val Nat = NativeRel {
//          case WrapScalaInt(i)::_ => testOk = i
//          case _ =>
//        }
//        val B = LocalRel
//        val x = Var[Int]
//
//        rules(
//          goodChannel(x) --> Nat(x),
//          badChannel(x) --> Nat(x)
//        )
//      }
//
////      receiver.enableSolutionTrace()
////      receiver.enableStreamingTrace()
//
////      receiver.introduceAtom(receiver.goodChannel(WrapScalaInt(2)).head)
////      receiver.executeRules()
//      children = List(receiver)
//    }
//
//    firewall.sendFilterRules()

//    val goodOutChannel:OutChannel = new OutChannel("goodChannel", new ChannelLocation("goodChannel"))
//    val badOutChannel:OutChannel = new OutChannel("badChannel", new ChannelLocation("badChannel"))
//
//    sender.send("receiver", goodOutChannel(WrapScalaInt(1)).head)
//    sender.send("receiver", badOutChannel(WrapScalaInt(1)).head)

    sender.executeRules()

    Thread.sleep(500)

    assertEquals(1,testOk)
  }
}
