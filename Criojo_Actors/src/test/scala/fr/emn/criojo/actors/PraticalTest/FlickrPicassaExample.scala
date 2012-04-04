package fr.emn.criojo.actors.PraticalTest

import org.specs2.mutable._
import fr.emn.criojo.core.{ValueTerm, Variable}
import fr.emn.criojo.ext.StrCHAM
import fr.emn.criojo.actors.{ActorDebugger, ActorCham}

/*
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: 26/03/12
 * Time: 16:34
 */

trait Lookup {
  def lookupChannel(name:String):String = name match  {
    case "p" => "localhost:9090:Agent2"
    case "ping" => "localhost:9090:Agent2"
    case _ => ""
  }
}

class CommunicationSpec extends Specification {
  "Agent1" should {
    "send message to Agent2 and receive an answer" in {
      var passed = false
      val client = new ActorCham("localhost", 9001, "Client") with StrCHAM with Lookup {
        val GetPictureCount = Rel("GetPictureCount")
        val count = InChannel("count")
        val RequestPictureCount = OutChannel("requestPictureCount") //,"localhost:9002:RequestPictureCount")

        rules(
        )
      }

      val adapter = new ActorCham("localhost", 9002, "Adapter") with StrCHAM with Lookup {
        val requestPictureCount = InChannel("requestPictureCount")
        val flickrIn = OutChannel("flickrIn") //,"localhost:9003:flickrIn")
        val picasaIn = OutChannel("PicasaIn") //,"localhost:9004:picasaIn")
        val sendCount = InChannel("sendCount")

        rules(
        )
      }

      val picassaWrapper = new ActorCham("localhost", 9003, "PicassaWrapper") with StrCHAM with Lookup {
        val flickrIn = InChannel("flickrIn")
        val sendCount = OutChannel("sendCount") //,"localhost:9002:sendCount")

        rules(
        )
      }

      val flickrWrapper = new ActorCham("localhost", 9004, "FlickrWrapper") with ActorDebugger with Lookup {
        val PicasaIn = InChannel("PicasaIn")
        val sendCount = OutChannel("sendCount") //,"localhost:9002:sendCount")

        rules(
        )
      }

      client.start()
      adapter.start()

      client.introduceMolecule(client.GetPictureCount(ValueTerm(1234)))
      client.executeRules()

      true
    }
  }
}

