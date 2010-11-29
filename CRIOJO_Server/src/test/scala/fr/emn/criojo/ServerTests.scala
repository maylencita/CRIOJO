package fr.emn.criojo

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 4, 2010
 * Time: 2:22:06 PM
 * To change this template use File | Settings | File Templates.
 */

import virtualmachine._
import fr.emn.criojo.loader.ScriptLoader._
import fr.emn.criojo.util.Logger._
import fr.emn.criojo.core._
import fr.emn.criojo.util._

import org.junit._
import Assert._

import javax.ws.rs.core._
import java.net.InetAddress
import java.net.URI

import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig

import actors._

class ServerTests{
  logLevel = INFO

  val uri = try{
    CriojoClient.registerClient
  }catch{
    case e => log(WARNING, this.getClass, "testConnectedVM", "Proxy service unavailable.")
      UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()
  }
  val vm = new CriojoClient(uri)
  
  @Test
  def testConnectedVM{

    load(vm,
    """
      (public:_;
      private:
        R,
        S@"http://localhost:8080/VM")
      !T => new(x) S(x)
    """)
    assertTrue("Relation S not found in " + vm.relations, vm.relations.exists(r => r.name == "S"))

    vm.execute
    assertTrue(vm.solution.findMatches(List(Atom("S", List(Undef))), List()).isEmpty)
    assertEquals(1, vm.solution.size)    
  }

  @Test
  def testPing{
    load(vm,
      """
      (public:P; private:R@"http://localhost:8080/relation")
      !T => new(x)R(x,P)
      |
      P(x) => Print(x)
      """)

    vm.start
    Thread.sleep(10000)
    assertFalse(vm.solution.findMatches(List(Atom("Print", List(Undef))), List()).isEmpty)
  }

  @Test
  def testPicasaLogin{
    load(vm,
      """
      (public:Tok; private:Session, Login@"http://localhost:8080/vm/P-VM")
      !T => new(x) Login(x,Tok,"maylelacouture@gmail.com","dr2g9n21r"),Session(x) 
      |
      Tok(x,tok),Session(x) => Print(tok)
      """)

    vm.start
    Thread.sleep(10000)
//    assertFalse(vm.solution.findMatches(List(Atom("Print", List(Undef))), List()).isEmpty)
  }

  @Test //(timeout=35000)
  def testPicasaAlbum{
//    logLevel = DEBUG
    val pvmhost = "http://localhost:8080/vm/PVM"
//    val pvmhost = "http://criojo.appspot.com/vm/PVM"
    var result = false
    val vm2 = new CriojoClient(uri){
      val ACloning = Required("AlbumCloning",pvmhost)
      val Session = Rel("Session")
      val Start = Rel("Start")
      val Count = Rel("Count")
      val PAlbum = Provided("PAlbum")

      val Ok = NativeRelation("Ok"){
        case Atom("Ok", s::num::_) =>
          info(this.getClass, "testPicasaAlbum", "Received: " + getValue(num) + " albums.")
          result = true
        case failatom => fail("Unexpected atom: " + failatom)
      }
      val id,u,n,m,s = Var
      val palbum = RelVariable("PAlbum")

      rules(
        Start(n,u) ==> Nu(s)(ACloning(s,u,palbum) &: Count(s,n,u)),
        (PAlbum(s,id) &: Count(s,n,u) ) ==> {NotNul(id)} ?: Nu(m)(ACloning(s,u,palbum) &: Suc(n,m) &: Count(s,m,u)),
        (PAlbum(s,id) &: Count(s,n,u) ) ==> {Nul(id)} ?: Ok(s,n)
      )
    }
    vm2.start

    val palbum = RelVariable("PAlbum"); palbum.relation = vm2.PAlbum
    vm2.addAtom(Atom("Start",Value(0),Value("maylelacouture")))

    Thread.sleep(120000)
    assertTrue("Finished with solution: " + vm2.prettyPrint, result)
  }

  @Test
  def testPicasaAlbumScript{
//    logLevel = DEBUG
    load(vm,
    """(
//    required:AlbumCloning@"http://criojo.appspot.com/vm/PVM";
    required:AlbumCloning@"http://localhost:8080/vm/PVM";
    provided:PAlbum;
    local:Session,Count,Start)

    !T => new(s)Start(s,"maylelacouture") |
    Start(s,u) => AlbumCloning(s,u,PAlbum),Count(s,0,u) |
    PAlbum(s,id), Count(s,n,u) => [Not(Null(id))]? new(m) AlbumCloning(s,u,PAlbum), Suc(n,m), Count(s,m,u) |
    PAlbum(s,id), Count(s,n,u) => [Null(id)]? Print(n)
    """
    )
    vm.start

    Thread.sleep(120000)    
  }
}

object CriojoClient{
  val myclient:Client = Client.create(new DefaultClientConfig)
  val PROXY_URL = "http://criojo.appspot.com/proxy"
//  val PROXY_URL = "http://localhost:8080/proxy"

  @throws(classOf[Exception])
  def registerClient:URI = {
      val proxyservice = myclient.resource(PROXY_URL).path("register")
      val resp:String = proxyservice.get(classOf[String])
      println("Virtual address: " + resp)

      UriBuilder.fromUri(resp).build()
  }

}

class CriojoClient(url:URI) extends ConnectedVM(url) with Actor{
  def this(scriptUri:String, url:URI)={
    this(url)
    load(this, io.Source.fromFile(scriptUri).mkString)
  }

  def act{
    this.execute
    while(true){
      try{
        getMessages
      }catch{
        case e=>
          log(WARNING, this.getClass,"act","Could not connect to proxy: " + e)
          e.printStackTrace
      }

      Thread.sleep(500)
    }
  }

  @throws(classOf[Exception])
  def getMessages:String = {
    val proxyservice = CriojoClient.myclient.resource(url)
    val resp = proxyservice.get(classOf[String])

    if (resp != "[]"){
      for (a <- Json2Criojo(this).parseList(resp)){
        debug(this.getClass,"getMessages", "Received atom: " + a)
        addAtom(a)
      }
    }
    resp
  }
}
