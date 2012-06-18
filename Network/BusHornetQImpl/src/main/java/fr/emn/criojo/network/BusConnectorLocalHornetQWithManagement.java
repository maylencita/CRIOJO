package fr.emn.criojo.network;

import org.hornetq.api.core.SimpleString;

/**
 * {@link BusConnectorLocalHornetQWithManagement} connect to a HornetQ server.
 * 
 * Same as {@link BusConnectorLocalHornetQ} but start a Management Queue to
 * manage HornetQ node.
 */
public class BusConnectorLocalHornetQWithManagement extends BusConnectorLocalHornetQ {
	public static final SimpleString MANAGEMENT_QUEUE = new SimpleString(
	    "jms.queue.hornetq.management");

	/**
	 * BusConnection Using HornetQ Message Oriented Middleware.
	 * 
	 * If no connection point defined on local HornetQ server, this will start a
	 * new HornetQ server on {@link BusConnectorLocalHornetQWithManagement}.getHost with
	 * given port. If new server started, the new server is start in clustered
	 * mode with given broadcast address.
	 * 
	 * @param port
	 *          The host port (HornetQ Connector port, with Netty).
	 * @param name
	 *          The name of current connection (should be unique in cluster).
	 * @param login
	 *          The session login.
	 * @param password
	 *          The session password.
	 * @param stompWebSocketPort
	 *          The Stomp over WebSocket acceptor port.
	 * @param broadcastAddress
	 *          The broadcast address (HornetQ server).
	 * @param broadcastPort
	 *          The broadcast port to diffuse and listen other HornetQ server.
	 * @throws BusConnectorException
	 *           Troubles append during connection to Bus or Bus start.
	 */
	public BusConnectorLocalHornetQWithManagement(int port, String name, String login,
	    String password, int stompWebSocketPort, String broadcastAddress,
	    int broadcastPort) throws BusConnectorException {
		super(port, name, login, password, stompWebSocketPort, broadcastAddress,
		    broadcastPort);
	}

	/**
	 * BusConnection Using HornetQ Message Oriented Middleware.
	 * 
	 * If no connection point defined on local HornetQ server, this will start a
	 * new HornetQ server on {@link BusConnectorLocalHornetQWithManagement}.getHost with
	 * given port. If new server started, the new server is start in clustered
	 * mode with broadcast address
	 * BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS and broadcast port
	 * BusConnectorLocalHrnetQ.DEFAULT_BROADCAST_PORT. Moreover an acceptor for
	 * Stomp connection over WebSocket is started on
	 * BusConnectorLocalHornetQ.DEFAULT_STOMPWEBSOCKET_PORT.
	 * 
	 * @param port
	 *          The host port (HornetQ Connector port, with Netty).
	 * @param name
	 *          The name of current connection (should be unique in cluster).
	 * @param login
	 *          The session login.
	 * @param password
	 *          The session password.
	 * @throws BusConnectorException
	 *           Troubles append during connection to Bus or Bus start.
	 */
	public BusConnectorLocalHornetQWithManagement(int port, String name, String login,
	    String password) throws BusConnectorException {
		super(port, name, login, password);
	}
	
	@Override
	protected void startServer() throws Exception {
		super.startServer();
		server.deployQueue(MANAGEMENT_QUEUE, MANAGEMENT_QUEUE, null, false, false);
	}
	
	@Override
	protected String configurationXML() {
		return "<configuration xmlns=\"urn:hornetq\""
		    + "\n               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
		    + "\n               xsi:schemaLocation=\"urn:hornetq /schema/hornetq-configuration.xsd\">"
		    + "\n"
		    + "\n   <clustered>true</clustered>"
		    + "\n   "
		    + "\n   <paging-directory>${build.directory}/data/paging</paging-directory>"
		    + "\n   "
		    + "\n   <bindings-directory>${build.directory}/data/bindings</bindings-directory>"
		    + "\n   "
		    + "\n   <journal-directory>${build.directory}/data/journal</journal-directory>"
		    + "\n   "
		    + "\n   <large-messages-directory>${build.directory}/data/large-messages</large-messages-directory>"
		    + "\n"
		    + "\n		<management-address>" + MANAGEMENT_QUEUE + "</management-address>"
		    + "\n"
		    + "\n   <connectors>      "
		    + "\n      <connector name=\"netty\">"
		    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyConnectorFactory</factory-class>"
		    + "\n         <param key=\"host\"  value=\"" + getHost() + "\"/>"
		    + "\n         <param key=\"port\"  value=\"" + getPort() + "\"/>"
		    + "\n      </connector>"
		    + "\n   </connectors>"
		    + "\n"
		    + "\n   <acceptors>"
		    + "\n      <acceptor name=\"netty\">"
		    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
		    + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
		    + "\n         <param key=\"port\"  value=\"" + getPort() + "\"/>"
		    + "\n      </acceptor>"
		    + "\n      "
		    + "\n      <acceptor name=\"stomp-ws-acceptor\">"
		    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
		    + "\n         <param key=\"protocol\"  value=\"stomp_ws\"/>"
		    + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
		    + "\n         <param key=\"port\"  value=\"" + getStompWebSocketPort() + "\"/>"
		    + "\n      </acceptor>"
		    + "\n   </acceptors>"
		    + "\n"
		    + "\n   <broadcast-groups>"
		    + "\n      <broadcast-group name=\"bg-group1\">"
		    + "\n         <group-address>" + getBroadcastAddress() + "</group-address>"
		    + "\n         <group-port>" + getBroadcastPort() + "</group-port>"
		    + "\n         <broadcast-period>5000</broadcast-period>"
		    + "\n         <connector-ref>netty</connector-ref>"
		    + "\n      </broadcast-group>"
		    + "\n   </broadcast-groups>"
		    + "\n"
		    + "\n   <discovery-groups>"
		    + "\n      <discovery-group name=\"dg-group1\">"
		    + "\n         <group-address>" + getBroadcastAddress() + "</group-address>"
		    + "\n         <group-port>" + getBroadcastPort() + "</group-port>"
		    + "\n         <refresh-timeout>10000</refresh-timeout>"
		    + "\n      </discovery-group>"
		    + "\n   </discovery-groups>"
		    + "\n   "
		    + "\n   <cluster-connections>"
		    + "\n      <cluster-connection name=\"my-cluster\">"
		    + "\n         <address>jms</address>	 "
		    + "\n         <connector-ref>netty</connector-ref>"
		    + "\n	      <discovery-group-ref discovery-group-name=\"dg-group1\"/>"
		    + "\n      </cluster-connection>"
		    + "\n   </cluster-connections>"
		    + "\n   "
		    + "\n   <security-settings>"
		    + "\n      <security-setting match=\"#\">"
		    + "\n         <permission type=\"createNonDurableQueue\" roles=\"guest\"/>"
		    + "\n         <permission type=\"deleteNonDurableQueue\" roles=\"guest\"/>"
		    + "\n         <permission type=\"consume\" roles=\"guest\"/>"
		    + "\n         <permission type=\"send\" roles=\"guest\"/>"
		    + "\n      </security-setting>"
		    + "\n   "
		    + "\n	    <!--security for management queue-->"
		    + "\n	    <security-setting match=\"" + MANAGEMENT_QUEUE + "\">"
		    + "\n	      <permission type=\"manage\" roles=\"guest\" />"
		    + "\n	    </security-setting>"
		    + "\n   </security-settings>"
		    + "\n</configuration>";
	}
}
