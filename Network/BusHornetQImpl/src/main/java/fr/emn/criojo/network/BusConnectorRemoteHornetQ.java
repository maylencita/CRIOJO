package fr.emn.criojo.network;

import java.util.HashMap;
import java.util.Map;

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
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;

/**
 * {@link BusConnectorRemoteHornetQ} connect to an existing server. The server
 * would be a HornetQ server.
 * 
 * HornetQ is an open source asynchronous messaging project from JBoss. It is an
 * example of Message Oriented Middleware. HornetQ is an open source project to
 * build a multi-protocol, embeddable, very high performance, clustered,
 * asynchronous messaging system.
 * 
 * To connect to bus, the HornetQ server configuration will specifying
 * connectors using Netty implementation.
 * 
 * @see http://www.jboss.org/hornetq
 */
public class BusConnectorRemoteHornetQ implements BusConnector {
	public static final String QUEUE = "jms.queue.";

	private final String host;
	private final int port;
	private final String name;
	private final String login;
	private final String password;
	private boolean disconected;

	private ClientSession session = null;
	private ClientConsumer consumer = null;
	private ClientProducer producer = null;
	private ServerLocator locator = null;
	private ClientSessionFactory factory = null;

	/**
	 * BusConnection Using HornetQ Message Oriented Middleware.
	 * 
	 * @param host
	 *          The host to connect (HornetQ Connector address, with Netty).
	 * @param port
	 *          The host port (HornetQ Connector port, with Netty).
	 * @param name
	 *          The name of current connection (should be unique in cluster).
	 * @param login
	 *          The session login.
	 * @param password
	 *          The session password.
	 * @throws BusConnectorException
	 *           Troubles append during connection to Bus.
	 */
	public BusConnectorRemoteHornetQ(String host, int port, String name,
	    String login, String password) throws BusConnectorException {
		this.host = host;
		this.port = port;
		this.name = name;
		this.login = login;
		this.password = password;
		this.disconected = false;

		try {
			connect();
			startQueueProducerConsumer();
		} catch (Exception e) {
			throw new BusConnectorException(e.getMessage());
		}
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
					} catch (NullPointerException npe) {
						receiveHandler.onReceive(message.toString());
					}
				}
			});
		} catch (HornetQException hqe) {
			throw hornetQExceptionToBusException(hqe);
		}
	}
	
	@Override
	public void broadcast(String message) throws BusConnectorException {
		// TODO: Implements Broadcast.
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

		connectionParams.put(TransportConstants.HOST_PROP_NAME, host);
		connectionParams.put(TransportConstants.PORT_PROP_NAME, port);

		tConfiguration = new TransportConfiguration(
		    NettyConnectorFactory.class.getName(), connectionParams);

		// Get the connection factory to connect on server.
		locator = HornetQClient.createServerLocatorWithoutHA(tConfiguration);

		// Connect to the server
		try {
			factory = locator.createSessionFactory();
		} catch (HornetQException hqe) {
			throw hornetQExceptionToBusException(hqe);
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
			// 2.2.18 Version
			switch (hqe.getCode()) {
			case HornetQException.QUEUE_EXISTS:
				System.err.println("[BUS] Queue "
						+ getQueueName() + " already exists.");
				break;
			default:
				throw hqe;
			}

			// 2.3.0 Version
//			switch (hqe.getType()) {
//			case QUEUE_EXISTS:
//				System.err
//				    .println("[BUS] Queue " + getQueueName() + " already exists.");
//				break;
//			default:
//				throw hqe;
//			}
		}

		// Create Producer and Consumer.
		producer = session.createProducer();
		consumer = session.createConsumer(getQueueName());

		session.start();
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

		// 2.2.18 Version
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

		// 2.3.0 Version
//		switch (hqe.getType()) {
//		case NOT_CONNECTED:
//			be = new BusConnectorException(BusConnectorException.NOT_CONNECTED,
//			    hqe.getMessage());
//			break;
//		case OBJECT_CLOSED:
//			be = new BusConnectorException(BusConnectorException.CLOSED, name);
//			break;
//		default:
//			be = new BusConnectorException(BusConnectorException.INTERNAL_ERROR,
//			    hqe.getMessage());
//		}
		
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

	/**
	 * Returns the HornetQ address used to establish connection.
	 * 
	 * @return HornetQ port used to establish connection.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Returns the HornetQ server port used to establish connection.
	 * 
	 * @return HornetQ server port used to establish connection.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Returns The connector name
	 * 
	 * @return Connector name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the HornetQ server login used to establish connection.
	 * 
	 * @return HornetQ server login used to establish connection.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Returns the HornetQ server password used to establish connection.
	 * 
	 * @return HornetQ server password used to establish connection.
	 */
	public String getPassword() {
		return password;
	}
}
