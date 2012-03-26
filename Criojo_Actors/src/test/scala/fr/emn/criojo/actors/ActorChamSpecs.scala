package fr.emn.criojo.actors

import org.specs2.mutable._
import fr.emn.criojo.core.{ValueTerm, Variable}
import fr.emn.criojo.ext.StrCHAM
import fr.emn.criojo.ext.debug.DebugCham

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/03/12
 * Time: 16:34
 */

class SendMessageSpecs extends Specification{
  "Agent2" should {
    "receive message from Agent1" in {
      var res = false
      val agent1 = new ActorCham("localhost",9999,"Agent1"){
        val Send = Rel
        val p = Channel("p","localhost:9090:Agent2")
        val x,y,z = Var

        rules(
          Send(x) --> p(x)
        )
      }
      val agent2 = new ActorCham("localhost",9090,"Agent2"){
        val p = Rel("p")
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
      val agent1 = new ActorCham("localhost",9999,"Agent1") with StrCHAM{
        val Send = Rel("Send")
        val pong = Rel("pong")
        val ping = Channel("ping","localhost:9090:Agent2")
        val x = Var

        val Pass = NativeRel{a =>
          println(this + " -Pass: " + a)
          passed = true
        }

        rules(
          Send(x) --> (ping(x,pong)),
          pong(x) --> Pass(x)
        )
      }
      val agent2 = new ActorCham("localhost",9090,"Agent2") with DebugCham{
        DEBUG_DIRECT_MODE = true
        val ping = Rel("ping")
        val x = Var
        val k = VarR("k")

        rules(
          ping(x,k) --> k(x)
        )
      }
      agent2.start()
//      agent1.start()
      agent1.introduceMolecule(agent1.Send(ValueTerm(1234)))
      agent1.executeRules()

      agent2.printTrace()

      passed must beTrue.eventually
    }
  }
}

