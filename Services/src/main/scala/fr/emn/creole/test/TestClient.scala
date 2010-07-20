package fr.emn.creole.test

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 19, 2010
 * Time: 5:49:05 PM
 * To change this template use File | Settings | File Templates.
 */
//import fr.emn.creole.core._
import fr.emn.creole.model._
import fr.emn.creole.util._

import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client
import com.sun.jersey.api.client.config.DefaultClientConfig
import com.sun.grizzly.http.SelectorThread
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory
import java.util._
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

object TestClient{
  def main(args:Array[String]){
    val threadSelector =  LocalServer.startServer()

//    val a = new Atom("PhotoCloning", new Variable("X") :: new Variable("y") :: Nil)

    val config = new DefaultClientConfig
    val client = Client.create(config)
    val service = client.resource("http://localhost:9998/PhotoCloning")

    val atom1 = VarList(WebVariable("X", WebRelation("X","http://localhost:9999")):: new WebVariable("a", null)::Nil)
    val vlst = JSONUtil.serialize(atom1)

//    val vlst = """{"vlst":[{"name":"X","relation":{"name":"X","url":""" + LocalServer.BASE_URI +
//      """}},{"name":"a","relation":"null"}]}"""

    println(vlst)
    val resp = try{
      service.entity(vlst.getBytes, MediaType.APPLICATION_JSON_TYPE).
              put(classOf[String])
    }catch{
      case e => println("Error: " + e)
    }

    println("Response: " + resp)
    threadSelector.stopEndpoint();
  }

}

object LocalServer{
  val BASE_URI:URI = getBaseURI()

  def getBaseURI() = UriBuilder.fromUri("http://localhost/").port(9999).build()

  def startServer():SelectorThread={
      val initParams = new HashMap[String, String]()

      initParams.put("com.sun.jersey.config.property.packages",
              "fr.emn.creole.resources");

      println("Starting grizzly...")
      val threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams)
      threadSelector
  }
}
