package fr.emn.criojo.actors

import org.specs2.mutable._
import fr.emn.criojo.core.{ValueTerm, Variable}
import fr.emn.criojo.ext.StrCHAM
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.lang.Cham

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/03/12
 * Time: 16:34
 */

trait Lookup {
  this: Cham =>
  override def lookupChannel(name:String):String = name match  {
    case "p" => "localhost:9090:Agent2"
    case "ping" => "localhost:9090:Agent2"
    case _ => ""
  }
}

class SendMessageSpecs extends Specification{
  "Agent2" should {
    "receive message from Agent1" in {
      var res = false
      val agent1 = new ActorCham("localhost",9999,"Agent1") with Lookup{
        val Send = Rel
        val p = OutChannel("p") //,"localhost:9090:Agent2")
        val x,y,z = Var

        rules(
          Send(x) --> p(x)
        )
      }
      val agent2 = new ActorCham("localhost",9090,"Agent2") with Lookup{
        val p = InChannel("p")
        val Res = NativeRel{a =>
          println(this + " - atom: "+a)
          res = true
        }
        val x = Var

        rules(
          p(x) --> Res(x)
        )
      }
      agent2.start()
      agent1.introduceMolecule(agent1.Send(Variable("x")))
      agent1.executeRules()

      res must beTrue.eventually
    }
  }
}

class CommunicationSpec extends Specification{
  "Agent1" should{
    "send message to Agent2 and receive an answer" in{
      var passed = false
      val agent1 = new ActorCham("localhost",9999,"Agent1") with StrCHAM with Lookup{
        val Send = Rel("Send")
        val pong = InChannel("pong")
        val ping = OutChannel("ping") //,"localhost:9090:Agent2")
        val x = Var

        val Pass = NativeRel{a =>
          println(this + " - Pass: " + a)
          passed = true
        }

        rules(
          Send(x) --> (ping(x,pong)),
          pong(x) --> Pass(x)
        )
      }
      val agent2 = new ActorCham("localhost",9090,"Agent2") with ActorDebugger with Lookup{
        DEBUG_DIRECT_MODE = true
        val ping = InChannel("ping")
        val x = Var
        val k = VarR("k")

        rules(
          ping(x,k) --> k(x)
        )
      }
      agent2.start()
      agent1.start()
      agent1.introduceMolecule(agent1.Send(ValueTerm(1234)))
      agent1.executeRules()

      agent2.printTrace()

      passed must beTrue.eventually
    }
  }
}

