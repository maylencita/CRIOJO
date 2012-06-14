package fr.emn.criojo.network;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class BusHornetQTest {

	/**
	 * Connector 1:
	 * <ol>
	 * <li>Connector 1 connect on Bus node 1,</li>
	 * <li>Send message in lower case to connector 2,</li>
	 * <li>Wait is message return in upper case,</li>
	 * <li>Disconnect from Bus.</li>
	 * </ol>
	 * 
	 * Connector 2:
	 * <ol>
	 * <li>Connector connect on Bus Node 2,</li>
	 * <li>Wait message on dedicated queue,</li>
	 * <li>On message receive, return message in Upper Case</li>
	 * <li>Disconnect from Bus.</li>
	 * </ol>
	 */
	@Test
	public void cloudLocalSendTest() throws FileNotFoundException, IOException,
	    RequiredPropertiesException {
		// Load properties
		final String[] REQUIRED_PROPS = { "cloudLocalSendTest.busNodePort.1",
		    "cloudLocalSendTest.connectorName.1",
		    "cloudLocalSendTest.connectorLogin.1",
		    "cloudLocalSendTest.connectorPassword.1",
		    "cloudLocalSendTest.busNodePort.2",
		    "cloudLocalSendTest.connectorName.2",
		    "cloudLocalSendTest.connectorLogin.2",
		    "cloudLocalSendTest.connectorPassword.2" };
		Properties testProps = loadTestProperties(REQUIRED_PROPS);

		// First connector arguments.
		final int BUS_NODE_PORT_1 = Integer.parseInt(testProps
		    .getProperty(REQUIRED_PROPS[0]));
		final String CONNECTOR_NAME_1 = testProps.getProperty(REQUIRED_PROPS[1]);
		final String CONNECTOR_LOGIN_1 = testProps.getProperty(REQUIRED_PROPS[2]);
		final String CONNECTOR_PASSWORD_1 = testProps
		    .getProperty(REQUIRED_PROPS[3]);

		// Second connector arguments.
		final int BUS_NODE_PORT_2 = Integer.parseInt(testProps
		    .getProperty(REQUIRED_PROPS[4]));
		final String CONNECTOR_NAME_2 = testProps.getProperty(REQUIRED_PROPS[5]);
		final String CONNECTOR_LOGIN_2 = testProps.getProperty(REQUIRED_PROPS[6]);
		final String CONNECTOR_PASSWORD_2 = testProps
		    .getProperty(REQUIRED_PROPS[7]);

		// Main Test
		Connector connector1 = new Connector() {
			public void run() {
				try {
					final String SENTENCE = "live long and prosper";
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_1,
					    CONNECTOR_NAME_1, CONNECTOR_LOGIN_1, CONNECTOR_PASSWORD_1);

					ack = new AckConnector1(SENTENCE);
					conn.setReceiveHandler(ack);
					conn.send(SENTENCE, CONNECTOR_NAME_2);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
						BusConnectorLocalHornetQ connloc;
						connloc = (BusConnectorLocalHornetQ) conn;
						if (connloc.hasServer()) {
							connloc.stopServer();
						}
					}
				}
			}
		};

		Connector connector2 = new Connector() {
			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_2,
					    CONNECTOR_NAME_2, CONNECTOR_LOGIN_2, CONNECTOR_PASSWORD_2);

					ack = new AckConnector2(conn, CONNECTOR_NAME_1);
					conn.setReceiveHandler(ack);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
						BusConnectorLocalHornetQ connloc;
						connloc = (BusConnectorLocalHornetQ) conn;
						if (connloc.hasServer()) {
							connloc.stopServer();
						}
					}
				}
			}
		};

		connector2.start();
		connector1.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue("Error on Transmited message over Network", connector1.isAck());
	}

	/**
	 * Connector 1:
	 * <ol>
	 * <li>Connector 1 connect on Bus node 1,</li>
	 * <li>Broadcast SENTENCE to all connectors,</li>
	 * <li>Acknowledge message reception if message equal SENTENCE</li>
	 * <li>Disconnect from Bus.</li>
	 * </ol>
	 * 
	 * Connector 2:
	 * <ol>
	 * <li>Connector 2 connect on Bus node 2,</li>
	 * <li>Acknowledge message reception if message equal SENTENCE</li>
	 * <li>Disconnect from Bus.</li>
	 * </ol>
	 * 
	 * Connector 3:
	 * <ol>
	 * <li>Connector 3 connect on Bus node 3,</li>
	 * <li>Acknowledge message reception if message equal SENTENCE</li>
	 * <li>Disconnect from Bus.</li>
	 * </ol>
	 */
	@Test
	public void cloudLocalBroadcastTest() throws FileNotFoundException,
	    IOException, RequiredPropertiesException {
		// Load properties
		final String[] REQUIRED_PROPS = { "cloudLocalBroadcastTest.busNodePort.1",
		    "cloudLocalBroadcastTest.connectorName.1",
		    "cloudLocalBroadcastTest.connectorLogin.1",
		    "cloudLocalBroadcastTest.connectorPassword.1",
		    "cloudLocalBroadcastTest.busNodePort.2",
		    "cloudLocalBroadcastTest.connectorName.2",
		    "cloudLocalBroadcastTest.connectorLogin.2",
		    "cloudLocalBroadcastTest.connectorPassword.2",
		    "cloudLocalBroadcastTest.busNodePort.3",
		    "cloudLocalBroadcastTest.connectorName.3",
		    "cloudLocalBroadcastTest.connectorLogin.3",
		    "cloudLocalBroadcastTest.connectorPassword.3" };
		Properties testProps = loadTestProperties(REQUIRED_PROPS);

		// First connector arguments.
		final int BUS_NODE_PORT_1 = Integer.parseInt(testProps
		    .getProperty(REQUIRED_PROPS[0]));
		final String CONNECTOR_NAME_1 = testProps.getProperty(REQUIRED_PROPS[1]);
		final String CONNECTOR_LOGIN_1 = testProps.getProperty(REQUIRED_PROPS[2]);
		final String CONNECTOR_PASSWORD_1 = testProps
		    .getProperty(REQUIRED_PROPS[3]);

		// Second connector arguments.
		final int BUS_NODE_PORT_2 = Integer.parseInt(testProps
		    .getProperty(REQUIRED_PROPS[4]));
		final String CONNECTOR_NAME_2 = testProps.getProperty(REQUIRED_PROPS[5]);
		final String CONNECTOR_LOGIN_2 = testProps.getProperty(REQUIRED_PROPS[6]);
		final String CONNECTOR_PASSWORD_2 = testProps
		    .getProperty(REQUIRED_PROPS[7]);

		// Third connector arguments.
		final int BUS_NODE_PORT_3 = Integer.parseInt(testProps
		    .getProperty(REQUIRED_PROPS[8]));
		final String CONNECTOR_NAME_3 = testProps.getProperty(REQUIRED_PROPS[9]);
		final String CONNECTOR_LOGIN_3 = testProps.getProperty(REQUIRED_PROPS[10]);
		final String CONNECTOR_PASSWORD_3 = testProps
		    .getProperty(REQUIRED_PROPS[11]);

		final String SENTENCE = "live long and prosper";

		// Main Test
		Connector connector1 = new Connector() {
			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_1,
					    CONNECTOR_NAME_1, CONNECTOR_LOGIN_1, CONNECTOR_PASSWORD_1);

					ack = new AckSpecificMessage(SENTENCE, this.getClass().getName());
					conn.setReceiveHandler(ack);
					conn.broadcast(SENTENCE);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
						BusConnectorLocalHornetQ connloc;
						connloc = (BusConnectorLocalHornetQ) conn;
						if (connloc.hasServer()) {
							connloc.stopServer();
						}
					}
				}
			}
		};

		Connector connector2 = new Connector() {
			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_2,
					    CONNECTOR_NAME_2, CONNECTOR_LOGIN_2, CONNECTOR_PASSWORD_2);
					ack = new AckSpecificMessage(SENTENCE, this.getClass().getName());
					conn.setReceiveHandler(ack);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
						BusConnectorLocalHornetQ connloc;
						connloc = (BusConnectorLocalHornetQ) conn;
						if (connloc.hasServer()) {
							connloc.stopServer();
						}
					}
				}
			}
		};
		
		Connector connector3 = new Connector() {
			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_3,
					    CONNECTOR_NAME_3, CONNECTOR_LOGIN_3, CONNECTOR_PASSWORD_3);
					ack = new AckSpecificMessage(SENTENCE, this.getClass().getName());
					conn.setReceiveHandler(ack);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
						BusConnectorLocalHornetQ connloc;
						connloc = (BusConnectorLocalHornetQ) conn;
						if (connloc.hasServer()) {
							connloc.stopServer();
						}
					}
				}
			}
		};

		connector3.start();
		connector2.start();
		connector1.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue("Error on Broadcasting message over Network",
		    (connector1.isAck() && connector2.isAck() && connector3.isAck()));
	}

	// ************************************************************ Test Utils **
	/**
	 * Defined a connector for test
	 */
	abstract class Connector extends Thread {
		protected BusConnector conn = null;
		protected Ack ack = null;
		public boolean isAck() {
			return (ack != null) ? ack.isAck() : false;
		}
	}

	/**
	 * An Ack for message control.
	 */
	abstract class Ack implements ReceiveHandler {
		protected boolean ack = false;

		public boolean isAck() {
			return ack;
		}
	}

	/**
	 * Ack if receive message is equal to a specific given message.
	 */
	class AckSpecificMessage extends Ack {
		private String specificMsg;
		private String extraName;

		public AckSpecificMessage(String specificMsg, String extraName) {
			this.specificMsg = specificMsg;
			this.extraName = extraName;
		}

		@Override
		public void onReceive(String msg) {
			System.out.println("Receive on " + extraName + ": " + msg);
			if (specificMsg.equals(msg)) {
				ack = true;
			}
		}
	}

	/**
	 * Special Ack for connector 1.
	 * 
	 * Test message received. If message is same as msgToTest but in upper case,
	 * the message is acknowledged.
	 */
	class AckConnector1 extends Ack {
		private String msg;

		public AckConnector1(String msgToTest) {
			msg = msgToTest;
		}

		@Override
		public void onReceive(String message) {
			System.out.println("Receive on connector1: " + message);
			if (message.equals(msg.toUpperCase())) {
				ack = true;
			}
		}
	}

	/**
	 * Special Ack for connector 2.
	 * 
	 * Wait msg received. On first message received, return to Connector1 and ack
	 * message.
	 */
	class AckConnector2 extends Ack {
		private BusConnector conn = null;
		private String to = null;

		public AckConnector2(BusConnector conn, String to) {
			this.conn = conn;
			this.to = to;
		}

		@Override
		public void onReceive(String message) {
			System.out.println("Receive on connector2: " + message);

			try {
				conn.send(message.toUpperCase(), to);
				ack = true;
			} catch (BusConnectorException e) {
				e.printStackTrace();
			}
		}
	}

	private static Properties loadTestProperties(String[] requiredProperties)
	    throws FileNotFoundException, IOException, RequiredPropertiesException {
		Properties testProps = new Properties();
		testProps.load(new FileInputStream("config/tests.properties"));

		if (!existingProperties(testProps, requiredProperties)) {
			throw new RequiredPropertiesException(requiredProperties);
		}

		return testProps;
	}

	private static boolean existingProperties(Properties properties,
	    String[] propertiesName) {
		for (String propName : propertiesName) {
			if (!properties.containsKey(propName)) {
				return false;
			}
		}

		return true;
	}

	public static class RequiredPropertiesException extends Exception {
		private static final long serialVersionUID = 1021048164961061044L;

		public RequiredPropertiesException(String[] requiredProperties) {
			super("One of the following properties are udefined in properties file :"
			    + formatProperties(requiredProperties));
		}

		private static String formatProperties(String[] propertiesName) {
			String properties = "[ ";
			for (String propName : propertiesName) {
				properties += propName + " ";
			}
			properties += "]";

			return properties;
		}
	}
}
