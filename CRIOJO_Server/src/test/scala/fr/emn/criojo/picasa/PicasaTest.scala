package fr.emn.criojo.picasa

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Sep 21, 2010
 * Time: 5:29:54 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.criojo.core._
import fr.emn.criojo.net.ConnectedVM
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

  logLevel = INFO
  PicasaParams.url = UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()

  @Test
  def testClientLogin{
    val auth = PicasaClient.login("maylelacouture@gmail.com","dr2g9n21r")
    assertTrue("Empty auth token.", auth != "")
  }

  @Test
  def testClientAlbum{
    print(PicasaClient.getAlbums(Variable("1"), "maylelacouture").mkString("","\n",""))
  }

  @Test
  def testLogin{
    var result = false
    val auth = PicasaClient.login("maylelacouture@gmail.com","dr2g9n21r")

    val c_vm = new ConnectedVM(UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()){
      val Token = NativeRelation("Tok"){
        case Atom("Tok", id::token::_) => result=true
        case a => fail("Received wrong answer. Expected: " + auth + " .Actual: " + a)
      }
    }

    val tok = new RelVariable("Tok"); tok.relation = c_vm.Token
    val x = Variable("x")
    val user = Variable("maylelacouture@gmail.com")
    val pwd = Variable("dr2g9n21r")
    PicasaVM.introduceAtom(Atom("Login", x,tok,user,pwd))

    assertTrue(result)
  }

  @Test //(timeout=1000)
  def testAlbumCloning{
//    logLevel = DEBUG
    var result = false
    var i = 0
    val user = "maylelacouture"
    val s = Variable("1")

    val c_vm = new ConnectedVM(UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()){
      val PAlbum:Relation = NativeRelation("PAlbum"){
        case Atom("PAlbum", ses::id::_) =>
          if (PicasaVM.getValue(id) != Null){
            println("Album: " + id)
            i += 1
            PicasaVM.loadSolution
            PicasaVM.addAtom(Atom("AlbumCloning", s,Value(user),RelVariable(PAlbum)))
          }else{
            result=(i > 0)
            println("Total albums: " + i)            
          }
        case a => fail("Received wrong answer. Expected: PAlbum(...) .Actual: " + a)
      }

    }
    val Al = RelVariable(c_vm.PAlbum)
    println("PicasaVM: " + PicasaVM.relations + "\n" + PicasaVM)

    PicasaVM.loadSolution
    PicasaVM.addRelation(c_vm.PAlbum)
    PicasaVM.addAtom(Atom("AlbumCloning", s,Value(user),Al))

//    info(this.getClass, "testAlbumCloning", "PicasaVM: " + PicasaVM.solution.elems.mkString("","\n",""))
    info(this.getClass, "testAlbumCloning", "intValues: " + PicasaVM.intEqClasses)
    assertTrue("PicasaVM did not return an answer.", result)
  }
}


