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
import fr.emn.creole.virtualmachine._

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

//    val client = Client.create(new DefaultClientConfig)
//    val service = client.resource("http://localhost:9998/VM2/relation/X")
//
//    val resp = try{
//      service.get(classOf[String])
//    }catch{
//      case e => println("Error: " + e)
//      "Error"
//    }
//    println("Test response: " + resp)
//
//    processScript()

    System.in.read();
    threadSelector.stopEndpoint();
  }

  def processScript(){
    try{
      val script = "(public:Photo; private:Session) !T => new(x) PhotoCloning(Photo,x),Session(x)"
      val server = "http://localhost:8080/VM/relation"
      val relName = "PhotoCloning"

      VirtualMachineService.reset

      if (server != "" && relName != ""){
        val remoteRelation = new RemoteRelation(relName, UriBuilder.fromUri(server).build())
        VirtualMachineService.machine.addRelation(remoteRelation)
      }

      VirtualMachineService.runScript(script)
      Logger.log(this.getClass, "runScript","solution: " + VirtualMachineService.getSolution)

    }catch{
      case e =>
        e.printStackTrace
        "Could not process script: " + e
    }
  }
}

object LocalServer{
  val BASE_URI:URI = getBaseURI()

  def getBaseURI() = UriBuilder.fromUri("http://localhost/VM2/").port(9998).build()

  def startServer():SelectorThread={
      val initParams = new HashMap[String, String]()

      initParams.put("com.sun.jersey.config.property.packages",
              "fr.emn.creole.resources");

      println("Starting grizzly...")
      val threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams)
      println(String.format("Jersey app started with WADL available at "
              + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
              BASE_URI, BASE_URI));

      threadSelector
  }
}
