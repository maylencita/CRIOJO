package fr.emn.criojo.builtin

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 21, 2010
 * Time: 5:29:54 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.ext.RemoteRelation
import fr.emn.criojo.util.Logger._

import com.google.gdata.client._
import com.google.gdata.client.photos._
import com.google.gdata.data._
import com.google.gdata.data.media._
import com.google.gdata.data.photos._

import org.junit._
import Assert._

import javax.ws.rs.core._
import java.net.InetAddress

class PicasaTest{

  @Test
  def testClientLogin{
    val auth = PicasaClient.login("maylelacouture@gmail.com","dr2g9n21r")
    assertTrue("Empty auth token.", auth != "")
  }

  @Test
  def testLogin{
    var result = false
    val auth = PicasaClient.login("maylelacouture@gmail.com","dr2g9n21r")

    val x = Variable("x")
    val user = Variable("maylelacouture@gmail.com")
    val pwd = Variable("dr2g9n21r")
    val Tok = new Relation{
      val name:String = "Tok"
      val public:Boolean = true
      val isMultiRel:Boolean = false
      def addObserver(observer:RelationObserver){}
      def notifyObservers(a: Atom){
        info(this.getClass, "testLoging", "received: " + a)
        a match{
          case Atom("Tok", id::token::_) => result=true
          case _ => fail("Received wrong answer. Expected: " + auth)
        }
      }
    }
    val tok = new RelVariable("Tok"); tok.relation = Tok

    PicasaParams.url = UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()
    PicasaVM.introduceAtom(Atom("Login", x::tok::user::pwd::Nil))

    assertTrue(result)
  }

//  import scala.actors.Actor
//  import scala.actors.remote.RemoteActor
//  import scala.actors.remote.RemoteActor._
//  import scala.actors.remote.Node
//
//  class LocalServer(port:Int) extends Actor
}


