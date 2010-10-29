package fr.emn.criojo

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Oct 22, 2010
 * Time: 5:34:29 PM
 * To change this template use File | Settings | File Templates.
 */
import org.mortbay.jetty._ , nio.SelectChannelConnector, webapp.WebAppContext

object Embeded extends Application{
  val server = new Server();
  val connector:Connector = new SelectChannelConnector();
  connector.setPort(8080);
  connector.setHost("localhost");
  server.addConnector(connector);

  val wac:WebAppContext = new WebAppContext();
  wac.setContextPath("/");
  wac.setWar("/users/mayleen/THESE/CRIOJO/CRIOJO_Server/target/CRIOJO_Server");    // this is path to .war OR TO expanded, existing webapp; WILL FIND web.xml and parse it
  server.setHandler(wac);
  server.setStopAtShutdown(true);

  server.start();
  
}