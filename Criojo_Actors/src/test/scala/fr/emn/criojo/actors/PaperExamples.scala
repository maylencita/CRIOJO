package fr.emn.criojo.actors

import org.specs2.mutable._
import fr.emn.criojo.lang.{Cham,Empty}
import fr.emn.criojo.core.factory.DefaultFactory
import fr.emn.criojo.ext.IntegerCham
import fr.emn.criojo.ext.expressions.IntExpression

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 03/04/12
 * Time: 11:44
 */

trait PrintCham extends Cham with DefaultFactory{
  var out:String = ""
  val Print = NativeRel{tlst =>
    out += tlst.mkString(""," ","")
  }
}

object PongCham extends ActorCham("localhost",9999,"Client") with PrintCham{
  val pong = InChannel("pong")
  val ping = OutChannel("ping")
  val Begin,Wait,End = Rel
  val n = Var

  val Debug = NativeRel{tls => print("Sent: "+tls.mkString(","))}

  rules(
    Begin() --> (ping(pong) & Wait() & Debug(pong)),
    (Wait() & pong(n)) --> (Print(n) & End())
  )

  override def lookupChannel(name:String) = name match{
    case "ping" => "localhost:9090:Server"
  }
}

object PingCham extends ActorCham("localhost",9090,"Server") with PrintCham with IntegerCham{
  val Counter,Begin = Rel
  val ping = InChannel("ping")
  val n = Var
  val k = VarR("k")

  val Debug = NativeRel{tls => print("Received: "+tls.mkString(","))}

  rules(
    Begin() --> Counter(1),
    (Counter(n) & ping(k)) --> (Counter(n+1) & k(n) & Debug(n,k))
  )

  override def lookupChannel(name:String) = ""
}

class PingPongSpec extends Specification{
  "Client" should{
    "receive response 1 from Server" in {
      PongCham.start()
      PingCham.start()

      PingCham.introduceMolecule(PingCham.Begin())
      PingCham.executeRules()

      PongCham.introduceMolecule(PongCham.Begin())
      PongCham.executeRules()

      println(PongCham.out)

      PongCham.out must be_==("1").eventually
    }
  }
}