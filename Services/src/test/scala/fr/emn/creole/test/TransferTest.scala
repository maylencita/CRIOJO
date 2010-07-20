package fr.emn.creole.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 18, 2010
 * Time: 5:19:12 PM
 * To change this template use File | Settings | File Templates.
 */
import fr.emn.creole.model._
import fr.emn.creole.util._
import fr.emn.creole.core._

import junit.framework._;
import Assert._;
import com.sun.jersey.api.client._, config._
import javax.ws.rs.core.MediaType;

import com.sun.grizzly.http.SelectorThread
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory
import java.util._
import java.net.URI;
import java.io.IOException
import javax.ws.rs.core.UriBuilder;

object TransferTest{
  def suite: Test = {
      val suite = new TestSuite(classOf[TransferTest]);
      suite
  }

  def main(args : Array[String]) {
      junit.textui.TestRunner.run(suite);
  }
}

class TransferTest extends TestCase("transfer"){

  def testTransfer{
    val threadSelector =  LocalServer.startServer()

    val a = new Atom("PhotoCloning", new Variable("X") :: new Variable("y") :: Nil)

    val config = new DefaultClientConfig
    val client = Client.create(config)
    val service = client.resource("http://localhost:9998/PhotoCloning")

    val vlst = """{"vlst":[{"name":"X","relation":{"name":"X","url":"http://localhost:9999"}},{"name":"a","relation":"null"}]}"""
    
    val resp = try{
      service.entity(vlst.getBytes, MediaType.APPLICATION_JSON_TYPE).
              put(classOf[String])
    }catch{
      case e => println("Error: " + e)
    }

    println("Response: " + resp)
    threadSelector.stopEndpoint();

    assertTrue(true)
  }
}


