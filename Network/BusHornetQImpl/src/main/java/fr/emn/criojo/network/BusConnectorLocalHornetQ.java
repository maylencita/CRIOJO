package fr.emn.criojo.network;

import java.io.ByteArrayInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.SimpleString;
import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.core.client.ClientConsumer;
import org.hornetq.api.core.client.ClientMessage;
import org.hornetq.api.core.client.ClientProducer;
import org.hornetq.api.core.client.ClientSession;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.hornetq.api.core.client.HornetQClient;
import org.hornetq.api.core.client.MessageHandler;
import org.hornetq.api.core.client.ServerLocator;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.deployers.impl.FileConfigurationParser;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;

/**
 * {@link BusConnectorLocalHornetQ} connect to a HornetQ server.
 * 
 * HornetQ is an open source asynchronous messaging project from JBoss. It is an
 * example of Message Oriented Middleware. HornetQ is an open source project to
 * build a multi-protocol, embeddable, very high performance, clustered,
 * asynchronous messaging system.
 * 
 * This connector comes with an implementation of HornetQ server. If no server
 * start on {@link BusConnectorLocalHornetQ}.getHost with given port, the
 * connector start a new HornetQ server. To connect to bus, the HornetQ server
 * configuration will specifying connectors using Netty implementation. Server
 * 
 * @see http://www.jboss.org/hornetq
 */
public class BusConnectorLocalHornetQ implements BusConnector {
	protected static Lock l = new ReentrantLock();
	public static final String QUEUE = "jms.queue.";
	public static final SimpleString MANAGEMENT_QUEUE = new SimpleString(QUEUE
			+ "hornetq.management");
	public static final String DEFAULT_BROADCAST_ADDRESS = "231.7.7.7";
	public static final int DEFAULT_BROADCAST_PORT = 9876;
	public static final int DEFAULT_STOMPWEBSOCKET_PORT = 61614;

	private final int port;
	private final String name;
	private final String login;
	private final String password;
	private final int stompWebSocketPort;
	private final String broadcastAddress;
	private final int broadcastPort;
	private boolean disconected;

	private ClientSession session = null;
	private ClientConsumer consumer = null;
	private ClientProducer producer = null;
	private ServerLocator locator = null;
	private ClientSessionFactory factory = null;

	/**
	 * To each port a new server is started. This structure keep for each port the
	 * server started and number of instance connected on it.
	 */
	private static Map<Integer, InstanceServer> servers = new HashMap<Integer, InstanceServer>();
	private static String hostValue = null;

	/**
	 * BusConnection Using HornetQ Message Oriented Middleware.
	 * 
	 * If no connection point defined on local HornetQ server, this will start a
	 * new HornetQ server on {@link BusConnectorLocalHornetQ}.getHost with given
	 * port. If new server started, the new server is start in clustered mode with
	 * given broadcast address.
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
	public BusConnectorLocalHornetQ(int port, String name, String login,
	    String password, int stompWebSocketPort, String broadcastAddress,
	    int broadcastPort) throws BusConnectorException {
		this.port = port;
		this.name = name;
		this.login = login;
		this.password = password;
		this.stompWebSocketPort = stompWebSocketPort;
		this.broadcastAddress = broadcastAddress;
		this.broadcastPort = broadcastPort;
		this.disconected = false;

		try {
			connect();
			startQueueProducerConsumer();
		} catch (Exception e) {
			throw new BusConnectorException(e.getMessage());
		}
	}

	/**
	 * BusConnection Using HornetQ Message Oriented Middleware.
	 * 
	 * If no connection point defined on local HornetQ server, this will start a
	 * new HornetQ server on {@link BusConnectorLocalHornetQ}.getHost with given
	 * port. If new server started, the new server is start in clustered mode with
	 * broadcast address BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS and
	 * broadcast port BusConnectorLocalHrnetQ.DEFAULT_BROADCAST_PORT. Moreover an
	 * acceptor for Stomp connection over WebSocket is started on
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
	public BusConnectorLocalHornetQ(int port, String name, String login,
	    String password) throws BusConnectorException {
		this(port, name, login, password, DEFAULT_STOMPWEBSOCKET_PORT,
		    DEFAULT_BROADCAST_ADDRESS, DEFAULT_BROADCAST_PORT);
	}

	@Override
	public void send(String message, String recipient)
	    throws BusConnectorException {
		ClientMessage msg = session.createMessage(false);

		try {
			// Use NullableSimpleString cause it is default Stomp message form.
			msg.getBodyBuffer().writeNullableSimpleString(new SimpleString(message));
			msg.setDurable(true);
			producer.send(QUEUE + recipient, msg);
		} catch (HornetQException hqe) {
			throw hornetQExceptionToBusException(hqe);
		}
	}

	@Override
	public void setReceiveHandler(final ReceiveHandler receiveHandler)
	    throws BusConnectorException {
		try {
			consumer.setMessageHandler(new MessageHandler() {
				@Override
				public void onMessage(ClientMessage message) {
					try {
						message.acknowledge();
					} catch (HornetQException hqe) {
						hqe.printStackTrace();
					}
					// Use NullableSimpleString cause it is default Stomp message form.
					try {
						receiveHandler.onReceive(message.getBodyBuffer()
								.readNullableSimpleString().toString());
					} catch(NullPointerException npe) {
						receiveHandler.onReceive(message.toString());
					}
				}
			});
		} catch (HornetQException hqe) {
			throw hornetQExceptionToBusException(hqe);
		}
	}

	@Override
	public void disconnect() {
		try {
			if (consumer != null) {
				consumer.close();
			}
			if (producer != null) {
				producer.close();
			}
			if (session != null) {
				session.close();
			}
			if (factory != null) {
				factory.close();
			}
			if (locator != null) {
				locator.close();
			}
			if (servers.containsKey(port)) {
				servers.get(port).decr();
				if (servers.get(port).hasInstance()) {
					try {
						// FIXME:
						// Server only count instance from current jvm.
						// If other connector are connected on server from other jvm,
						// those connectors are not counted, so this instruction may kill
						// connectors from other jvm.
						servers.get(port).server.stop();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (HornetQException hqe) {
			hornetQExceptionToBusException(hqe).printStackTrace();
		} finally {
			disconected = true;
		}
	}

	@Override
	public boolean isDisconected() {
		return disconected;
	}

	/**
	 * Connect to HornetQ server using host, port, login and password properties.
	 * 
	 * @throws Exception
	 *           Bus is unreachable.
	 */
	private void connect() throws Exception {
		TransportConfiguration tConfiguration = null;
		Map<String, Object> connectionParams = new HashMap<String, Object>();

		connectionParams.put(TransportConstants.HOST_PROP_NAME, getHost());
		connectionParams.put(TransportConstants.PORT_PROP_NAME, port);

		tConfiguration = new TransportConfiguration(
		    NettyConnectorFactory.class.getName(), connectionParams);

		// Get the connection factory to connect on server.
		locator = HornetQClient.createServerLocatorWithoutHA(tConfiguration);

		// Connect to the server
		try {
			factory = locator.createSessionFactory();
		} catch (HornetQException hqe) {
			switch (hqe.getCode()) {
			case HornetQException.NOT_CONNECTED:
				// Start localhost HornetQ server on given port and broadcast address.
				l.lock();
				startServer();
				l.unlock();
				break;
			default:
				throw hornetQExceptionToBusException(hqe);
			}
		}

		session = factory.createSession(login, password, false, true, true, false,
		    0);
	}

	/**
	 * Starts the Queue, Producer and Consumer.
	 * 
	 * Start the queue BusRemoteHornetQ.getName() and start a consumer on it. If
	 * queue already exists, the consumer is connected to existing queue. This
	 * method although allocate a producer and start the ClientSession.
	 * 
	 * @throws HornetQException
	 */
	private void startQueueProducerConsumer() throws HornetQException {
		// Create Queue.
		try {
			session.createQueue(getQueueName(), getQueueName(), false);
		} catch (HornetQException hqe) {
			switch (hqe.getCode()) {
			case HornetQException.QUEUE_EXISTS:
				System.err
				    .println("[BUS] Queue " + getQueueName() + " already exists.");
				break;
			default:
				throw hqe;
			}
		}

		// Create Producer and Consumer.
		producer = session.createProducer();
		consumer = session.createConsumer(getQueueName());

		session.start();
	}

	/**
	 * Start Local HornetQ Server.
	 * 
	 * @throws Exception
	 */
	private void startServer() throws Exception {
		if (!servers.containsKey(port)) {
			String confFile = "<configuration xmlns=\"urn:hornetq\""
			    + "\n               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
			    + "\n               xsi:schemaLocation=\"urn:hornetq /schema/hornetq-configuration.xsd\">"
			    + "\n"
			    + "\n   <clustered>true</clustered>"
			    + "\n   "
			    + "\n   <!--"
			    + "\n   <paging-directory>${data.dir:../data}/paging</paging-directory>"
			    + "\n   "
			    + "\n   <bindings-directory>${data.dir:../data}/bindings</bindings-directory>"
			    + "\n   "
			    + "\n   <journal-directory>${data.dir:../data}/journal</journal-directory>"
			    + "\n   "
			    + "\n   <journal-min-files>10</journal-min-files>"
			    + "\n   "
			    + "\n   <large-messages-directory>${data.dir:../data}/large-messages</large-messages-directory>"
			    + "\n   -->"
			    + "\n"
			    + "\n		<management-address>jms.queue.hornetq.management</management-address>"
			    + "\n"
			    + "\n   <connectors>      "
			    + "\n      <connector name=\"netty\">"
			    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyConnectorFactory</factory-class>"
			    + "\n         <param key=\"host\"  value=\"" + getHost() + "\"/>"
			    + "\n         <param key=\"port\"  value=\"" + port + "\"/>"
			    + "\n      </connector>"
			    + "\n      "
			    + "\n      <connector name=\"netty-throughput\">"
			    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyConnectorFactory</factory-class>"
			    + "\n         <param key=\"host\"  value=\"" + getHost() + "\"/>"
			    + "\n         <param key=\"port\"  value=\"" + (port + 10) + "\"/>"
			    + "\n         <param key=\"batch-delay\" value=\"50\"/>"
			    + "\n      </connector>"
			    + "\n   </connectors>"
			    + "\n"
			    + "\n   <acceptors>"
			    + "\n      <acceptor name=\"netty\">"
			    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
			    + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
			    + "\n         <param key=\"port\"  value=\"" + port + "\"/>"
			    + "\n      </acceptor>"
			    + "\n      "
			    + "\n      <acceptor name=\"stomp-ws-acceptor\">"
			    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
			    + "\n         <param key=\"protocol\"  value=\"stomp_ws\"/>"
			    + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
			    + "\n         <param key=\"port\"  value=\"" + stompWebSocketPort + "\"/>"
			    + "\n      </acceptor>"
			    + "\n      "
			    + "\n      <acceptor name=\"netty-throughput\">"
			    + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
			    + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
			    + "\n         <param key=\"port\"  value=\"" + (port + 10) + "\"/>"
			    + "\n         <param key=\"batch-delay\" value=\"50\"/>"
			    + "\n         <param key=\"direct-deliver\" value=\"false\"/>"
			    + "\n      </acceptor>"
			    + "\n   </acceptors>"
			    + "\n"
			    + "\n   <broadcast-groups>"
			    + "\n      <broadcast-group name=\"bg-group1\">"
			    + "\n         <group-address>" + broadcastAddress + "</group-address>"
			    + "\n         <group-port>" + broadcastPort + "</group-port>"
			    + "\n         <broadcast-period>5000</broadcast-period>"
			    + "\n         <connector-ref>netty</connector-ref>"
			    + "\n      </broadcast-group>"
			    + "\n   </broadcast-groups>"
			    + "\n"
			    + "\n   <discovery-groups>"
			    + "\n      <discovery-group name=\"dg-group1\">"
			    + "\n         <group-address>" + broadcastAddress + "</group-address>"
			    + "\n         <group-port>" + broadcastPort + "</group-port>"
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
			    + "\n			<!--security for management queue-->"
		      + "\n			<security-setting match=\"jms.queue.hornetq.management\">"
		      + "\n				<permission type=\"manage\" roles=\"guest\" />"
		      + "\n			</security-setting>"
			    + "\n   </security-settings>"
			    + "\n"
			    + "\n" + "\n</configuration>";
			Configuration configuration = new FileConfigurationParser()
			    .parseMainConfig(new ByteArrayInputStream(confFile.getBytes()));

			configuration.setPersistenceEnabled(false);
			configuration.setSecurityEnabled(false);

			configuration.getAcceptorConfigurations().add(
			    new TransportConfiguration(NettyAcceptorFactory.class.getName()));

			System.err.println("[BUS] Start local server using " + confFile);
			HornetQServer server = HornetQServers.newHornetQServer(configuration);
			server.start();
			server.deployQueue(MANAGEMENT_QUEUE, MANAGEMENT_QUEUE, null, false,
					false);

			servers.put(port, new InstanceServer(server));
		} else {
			servers.get(port).incr();
		}

		TransportConfiguration tConfiguration = null;
		Map<String, Object> connectionParams = new HashMap<String, Object>();

		connectionParams.put(TransportConstants.HOST_PROP_NAME, getHost());
		connectionParams.put(TransportConstants.PORT_PROP_NAME, port);

		tConfiguration = new TransportConfiguration(
		    NettyConnectorFactory.class.getName(), connectionParams);

		// Get the connection factory to connect on server.
		locator = HornetQClient.createServerLocatorWithoutHA(tConfiguration);

		// Connect to the server
		factory = locator.createSessionFactory();
	}

	/**
	 * Returns the host value.
	 * 
	 * @return The host value;
	 */
	public static String getHost() {
		if (hostValue == null) {
			hostValue = "127.0.0.1";

			try {
				hostValue = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		return hostValue;
	}

	/**
	 * Transforms a HornetQException to BusException.
	 * 
	 * @param hqe
	 *          The HorneQException
	 * @return The BusException.
	 */
	private BusConnectorException hornetQExceptionToBusException(
	    HornetQException hqe) {
		BusConnectorException be = null;

		switch (hqe.getCode()) {
		case HornetQException.NOT_CONNECTED:
			be = new BusConnectorException(BusConnectorException.NOT_CONNECTED,
			    hqe.getMessage());
			break;
		case HornetQException.OBJECT_CLOSED:
			be = new BusConnectorException(BusConnectorException.CLOSED, name);
			break;
		default:
			be = new BusConnectorException(BusConnectorException.INTERNAL_ERROR,
			    hqe.getMessage());
		}

		return be;
	}

	/**
	 * Returns the queue name were using to store received msg of bus connector.
	 * 
	 * @return The HornetQ queue name.
	 */
	public String getQueueName() {
		return QUEUE + name;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Structure to count number of instance on a specific server.
	 */
	private class InstanceServer {
		private int instance = 0;
		private HornetQServer server = null;

		public InstanceServer(HornetQServer server) {
			this.server = server;
		}

		public void incr() {
			++instance;
		}

		public void decr() {
			--instance;
		}

		public boolean hasInstance() {
			return instance > 0;
		}
	}
}
