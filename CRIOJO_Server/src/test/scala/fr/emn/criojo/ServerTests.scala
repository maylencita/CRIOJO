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
import fr.emn.criojo.client._

import org.junit._
import Assert._

import scala.xml.{XML,Elem}
import javax.ws.rs.core._
import java.net.InetAddress
import java.net.URI

import com.sun.jersey.api.client._
import com.sun.jersey.api.client.config.DefaultClientConfig
import com.sun.jersey.api.representation.Form

class ServerTests{
  logLevel = INFO

  val uri = try{
    CriojoClient.registerClient
  }catch{
    case e => log(WARNING, this.getClass, "init", "Proxy service unavailable.")
      UriBuilder.fromUri("http://" + InetAddress.getLocalHost()).build()
  }
  val vm = new DirectClient("CriojoTest",9999)
//  val vm = new ProxiedClient(uri)
  
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

    vm.start
    vm.execute
    assertTrue(vm.solution.findMatches(List(Atom("S", List(Undef))), List()).isEmpty)
    assertEquals(1, vm.solution.size)    
  }

  @Test
  def testPing{
    load(vm,
      """
      (provided:P; required:R@"http://localhost:8080/vm"; local:Ok)
      !T => new(x)R(x,P)
      |
      P(x) => Print(x),Ok
      """)

    vm.start
    vm.execute
    Thread.sleep(10000)
    
    assertFalse(vm.solution.findMatches(List(Atom("Ok", List(Undef))), List()).isEmpty)
  }

  @Test
  def testDeployedVM{
    val script = """
    (provided:R)
    R(x,P) => P(x)
    """

//    EmbededServer.start(8080)
//    println("Server Started")
    val client = Client.create(new DefaultClientConfig)
    val deployService = client.resource(UriBuilder.fromUri("http://localhost:8080/webconsole/").build())

    val form = new Form
    form.add("script",script)
    val response = deployService.
            post(classOf[ClientResponse], form).getEntity(classOf[String])

    println("response: " + response)

    testPing

//    EmbededServer.stop
//    println("Server Stoped")
  }

  @Test
  def testPicasaLogin{
    load(vm,
      """
      (provided:Tok; local:Session; required:Login@"http://localhost:8080/picasa/VM")
      !T => new(x) Login(x,Tok,"maylelacouture@gmail.com","dr2g9n21r"),Session(x) 
      |
      Tok(x,tok),Session(x) => Print(tok)
      """)

    vm.start
    vm.execute
    Thread.sleep(10000)
//    assertFalse(vm.solution.findMatches(List(Atom("Print", List(Undef))), List()).isEmpty)
  }

  @Test (timeout=35000)
  def testPicasaAlbum{
//    logLevel = DEBUG
    val pvmhost = "http://localhost:8080/picasa/VM"
//    val pvmhost = "http://criojo.appspot.com/vm/PVM"
    var result = false
    val vm2 = new ProxiedClient(uri){
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
        (PAlbum(s,id) &: Count(s,n,u) ) ==> NotNul(id) ? Nu(m)(ACloning(s,u,palbum) &: Suc(n,m) &: Count(s,m,u)),
        (PAlbum(s,id) &: Count(s,n,u) ) ==> Nul(id) ? Ok(s,n)
      )
    }
    vm2.start
    vm2.execute

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
    required:AlbumCloning@"http://criojo.appspot.com/picasa/VM";
//    required:AlbumCloning@"http://localhost:8080/picasa/VM";
    provided:PAlbum;
    local:Session,Count,Start,Result)

    !T => new(s)Start(s,"maylelacouture") |
    Start(s,u) => Count(s,0,u), AlbumCloning(s,u,PAlbum) |
    PAlbum(s,id), Count(s,n,u) => [Not(Null(id))]? new(m) Suc(n,m), Count(s,m,u), AlbumCloning(s,u,PAlbum) |
    PAlbum(s,id), Count(s,n,u) => [Null(id)]? Result(n)
    """
    )
    vm.start
    vm.execute

    Thread.sleep(10000)
    println(vm.prettyPrint)
  }
}

import org.mortbay.jetty._ , nio.SelectChannelConnector, webapp.WebAppContext

object EmbededServer {

  val server = new Server();

  def start(port:Int){
    val connector:Connector = new SelectChannelConnector();
    connector.setPort(port);
    connector.setHost("localhost");
    server.addConnector(connector);

    val wac:WebAppContext = new WebAppContext();
    wac.setContextPath("/");
    wac.setWar("/users/mayleen/THESE/CRIOJO/CRIOJO_Server/target/CRIOJO_Server");    // this is path to .war OR TO expanded, existing webapp; WILL FIND web.xml and parse it
    server.setHandler(wac);
    server.setStopAtShutdown(true);

    server.start();
  }

  def stop{server.stop}
}