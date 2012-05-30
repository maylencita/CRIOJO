package fr.emn.criojo.network;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class BusHornetQTest {
	@Test
	public void cloudLocalTest() throws FileNotFoundException, IOException,
	    RequiredPropertiesException {
		// Load properties
		final String[] REQUIRED_PROPS = { "cloudLocalTest.busNodePort.1",
		    "cloudLocalTest.connectorName.1", "cloudLocalTest.connectorLogin.1",
		    "cloudLocalTest.connectorPassword.1", "cloudLocalTest.busNodePort.2",
		    "cloudLocalTest.connectorName.2", "cloudLocalTest.connectorLogin.2",
		    "cloudLocalTest.connectorPassword.2" };
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

		/**
		 * <ol>
		 * <li>Connector 1 connect on Bus node 1,</li>
		 * <li>Send message in lower case to connector 2,</li>
		 * <li>Wait is message return in upper case,</li>
		 * <li>Disconnect from Bus.</li>
		 * </ol>
		 */
		class Connector1 extends Thread {
			final String SENTENCE = "live long and prosper";
			private BusConnector conn = null;
			private Ack ack = null;

			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_1,
					    CONNECTOR_NAME_1, CONNECTOR_LOGIN_1, CONNECTOR_PASSWORD_1);
					
					ack = new AckConnector1(SENTENCE);
					conn.setReceiveHandler(ack);
					conn.send(SENTENCE, CONNECTOR_NAME_2);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}

					conn.disconnect();
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
					}
				}
			}

			public boolean isAck() {
				return (ack != null) ? ack.isAck() : false;
			}
		}

		/**
		 * <ol>
		 * <li>Connector connect on Bus Node 2,</li>
		 * <li>Wait message on dedicated queue,</li>
		 * <li>On message receive, return message in Upper Case</li>
		 * <li>Disconnect from Bus.</li>
		 * </ol>
		 */
		class Connector2 extends Thread {
			private BusConnector conn = null;
			private Ack ack = null;

			public void run() {
				try {
					conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_2,
					    CONNECTOR_NAME_2, CONNECTOR_LOGIN_2, CONNECTOR_PASSWORD_2);

					ack = new AckConnector2(conn, CONNECTOR_NAME_1);
					conn.setReceiveHandler(ack);

					while (!ack.isAck()) {
						Thread.sleep(1000);
					}

					conn.disconnect();
				} catch (BusConnectorException e) {
					fail(e.getMessage());
				} catch (InterruptedException e) {
					fail(e.getMessage());
				} finally {
					if (conn != null) {
						conn.disconnect();
					}
				}
			}
		}

		// Main Test
		Connector1 connector1 = new Connector1();
		Connector2 connector2 = new Connector2();

		connector2.start();
		connector1.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue("Error on Transmited message over Network", connector1.isAck());
	}

	// ************************************************************ Test Utils **

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
			System.out.println("Reception on connector1: " + message);
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
			System.out.println("Reception on connector2: " + message);
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
