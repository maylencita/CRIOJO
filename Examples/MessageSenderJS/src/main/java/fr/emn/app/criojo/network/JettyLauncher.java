package fr.emn.app.criojo.network;

import java.util.Properties;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import fr.emn.criojo.network.BusConnector;
import fr.emn.criojo.network.BusConnectorException;
import fr.emn.criojo.network.BusConnectorFactory;
import fr.emn.criojo.network.BusConnectorFactoryException;
import fr.emn.criojo.network.BusConnectorLocalHornetQWithManagementFactory;

public class JettyLauncher {
	public static BusConnector startHornetQWithManagement()
	    throws BusConnectorFactoryException, BusConnectorException {
		BusConnectorFactory factory = new BusConnectorLocalHornetQWithManagementFactory();
		BusConnector connector = factory.createConnector("5445:debug");

		return connector;
	}

	private static Server startJetty() throws Exception {
		Server server = new Server(8080);
		WebAppContext context = new WebAppContext();

		context.setDescriptor("/WEB-INF/web.xml");
		context.setResourceBase(System.getProperties().getProperty("user.dir")
		    + "/src/main/webapp");
		context.setContextPath("/message-sender");

		server.setHandler(context);
		server.start();

		return server;
	}

	public static void main(String[] args) {
		 BusConnector connector = null;
		 Server jetty = null;
		
		 try {
		
		 connector = startHornetQWithManagement();
		 new MessageSender(connector);
		 jetty = startJetty();
		
		 System.out.println("Open http://localhost:8080/message-sender/"
		 + " in your browser ...");
		 System.in.read();
		 } catch (BusConnectorFactoryException e) {
		 e.printStackTrace();
		 } catch (BusConnectorException e) {
		 e.printStackTrace();
		 } catch (Exception e) {
		 e.printStackTrace();
		 } finally {
		 if (connector != null) {
		 connector.disconnect();
		 }
		 if (jetty != null) {
		 try {
		 jetty.stop();
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 }
		 }
	}
}
