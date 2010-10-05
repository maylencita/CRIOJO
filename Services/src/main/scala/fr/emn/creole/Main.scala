package fr.emn.creole

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 12, 2010
 * Time: 4:50:24 PM
 * To change this template use File | Settings | File Templates.
 */
import com.sun.grizzly.http.SelectorThread
//import com.sun.jersey.api.container.grizzly._
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory
import java.util._
import java.net.URI;
import java.io.IOException
import javax.ws.rs.core.UriBuilder;

//import com.sun.jersey.api.client.config.ClientConfig

object Main extends Application{
  val BASE_URI:URI = getBaseURI()

  def getPort(defaultPort:Int):Int= {
      val port = System.getenv("JERSEY_HTTP_PORT");
      if (null != port) {
          try {
              Integer.parseInt(port);
          } catch {
            case e:NumberFormatException => defaultPort
          }
      }else{
        defaultPort
      }
  }

  def getBaseURI() = UriBuilder.fromUri("http://localhost/VM2/").port(getPort(9998)).build()


  def startServer():SelectorThread={
      val initParams = new HashMap[String, String]()

      initParams.put("com.sun.jersey.config.property.packages",
              "fr.emn.creole.resources");

      println("Starting grizzly...")
      val threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams)
      threadSelector
  }

  override def main(args:Array[String]){
      val threadSelector = startServer()
      System.out.println(String.format("Jersey app started with WADL available at "
              + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
              BASE_URI, BASE_URI));
      System.in.read();
      threadSelector.stopEndpoint();
  }

}