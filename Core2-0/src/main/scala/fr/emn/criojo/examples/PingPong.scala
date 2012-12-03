package fr.emn.criojo.examples

import fr.emn.criojo.core.model.relation.{Channel, ChannelLocation, RemoteMessage, VarChannel}
import fr.emn.criojo.parallel.Agent
import fr.emn.criojo.expression.Converters
import fr.emn.criojo.core.engine.AtomGateway
import collection.mutable.HashMap

/**
 * Created with IntelliJ IDEA.
 * User: mayleen
 * Date: 11/29/12
 * Time: 2:45 PM
 * To change this template use File | Settings | File Templates.
 */
object PingPong extends App with Converters{

  val agent1 = new Agent("agent1", LocalGateway){
    val Pong = InputChannel("Pong")
    val Ping = OutputChannel("Ping","agent2")
    val Start = LocalRel

    val End = NativeRel {
      case resp::_ => println("[agent1] Pong " + resp + ". Now exiting...")
        System.exit(0)
      case _ =>
    }

    val Print = NativeRel{
      case t::_ => println(t)
      case _ =>
    }

    val x = Var[Int]

    rules(
      Start() --> (Ping(Pong, 1)),
      Pong(x) --> End(x)
    )

  }

  val agent2 = new Agent("agent2", LocalGateway){
    val Ping = InputChannel("Ping")
    val k = Var[Channel] //Channel("k")
    val x = Var[Int]

    val Print = NativeRel{
      case t::_ => println(t)
      case _ =>
    }

    rules(
      Ping(k,x) --> k(x+1)
    )
  }

  LocalGateway.addAgent(agent1.location, agent1)
  LocalGateway.addAgent(agent2.location, agent2)

  agent1.start()
  agent2.start()

  agent1 ! agent1.Start()

}
