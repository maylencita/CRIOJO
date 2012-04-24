package fr.emn.criojo.actors.PraticalTest

import dao.model.Picture
import dao.{MockFlickrDao, DaoTrait}
import org.specs2.mutable._
import fr.emn.criojo.ext.StrCHAM
import fr.emn.criojo.actors.{ActorDebugger, ActorCham}
import fr.emn.criojo.ext.expressions.StrExpression
import fr.emn.criojo.ext.debug.DebugCham
import fr.emn.criojo.core.datatype.Variable
import fr.emn.criojo.core.{Solution, Atom, ValueTerm, Variable}

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



      val client = new ActorCham("localhost", 9001, "Client") with StrCHAM with Lookup with DebugCham {
        val GetPictureCount = Rel("GetPictureCount")

        val login, mdp = Var

        val count = InChannel("count")
//        val RequestPictureCount = OutChannel("requestPictureCount") //,"localhost:9002:RequestPictureCount")
        val RequestPictureCount = Channel("requestPictureCount", "localhost:9002:Adapter")
        rules(
          GetPictureCount(login, mdp) --> RequestPictureCount(login, mdp)
        )
      }




      val adapter = new ActorCham("localhost", 9002, "Adapter") with StrCHAM with Lookup with DebugCham {
        val requestPictureCount = InChannel("requestPictureCount")
        val sendCount = InChannel("sendCount")
        val total = Rel("total")

        val login, mdp, t, c = Var

//        val flickrIn = OutChannel("flickrIn") //,"localhost:9003:FlickrWrapper")
//        val picasaIn = OutChannel("PicasaIn") //,"localhost:9004:PicassaWrapper")

        val flickrIn = Channel("requestPhotoCount", "localhost:9004:FlickrWrapper")
        val picasaIn = Channel("PicasaIn", "localhost:9003:PicassaWrapper")

        rules(
          requestPictureCount(login, mdp) --> (flickrIn(login, mdp) & total(0)),
          (sendCount(c) & total(t)) --> total(c.add(1))
        )
      }




      val picassaWrapper = new ActorCham("localhost", 9003, "PicassaWrapper") with StrCHAM with Lookup with DebugCham {
        val PicasaIn = InChannel("PicasaIn")

        //val sendCount = OutChannel("sendCount") //,"localhost:9002:sendCount")
        val sendCount = Channel("sendCount", "localhost:9002:sendCount")

        rules(
        )
      }





      val flickrWrapper = new ActorCham("localhost", 9004, "FlickrWrapper") with ActorDebugger with Lookup with DebugCham {
        val requestPhotoCount = InChannel("requestPhotoCount")

        //val sendCount = OutChannel("sendCount") //,"localhost:9002:Adapter")
        val sendCount = Channel("sendCount", "localhost:9002:Adapter")

        val login, mdp = Var
        val x, y, count = Var

        val reponse = Rel("reponse")

        val getAllPicture = NativeRelation("getAllPicture") {

          case (Atom(_, (x:ValueTerm[String])::(y:ValueTerm[String])::_), s:Solution) => {
            val daoFlick:DaoTrait = new MockFlickrDao()
            daoFlick.connect(x.getValue,y.getValue)

            val listOfPictures:List[Picture] = daoFlick.getPictures()
            this.introduceAtom(reponse(listOfPictures.size))
            this.executeRules()
          }
          case _ => println("ici")
        }

        rules(
          requestPhotoCount(login,mdp) --> getAllPicture(login, mdp),
          reponse(count) --> sendCount(count)
        )
      }

      import client.str2fun

      client.enableStreamingTrace()
      adapter.enableStreamingTrace()
      picassaWrapper.enableStreamingTrace()
      flickrWrapper.enableStreamingTrace()

      client.start()
      adapter.start()
      picassaWrapper.start()
      flickrWrapper.start()

      client.introduceMolecule(client.GetPictureCount("login", "password"))
      client.executeRules()

      while(true) {
        Thread.sleep(1000)
      }
      
      true
    }
  }
}

