package fr.emn.criojo.network;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class BusConnectorLocalHornetQTest {

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
	public void localSendTest() {
		boolean conn1IsAck = false;
		
    try {
			// Load properties
			final String[] REQUIRED_PROPS = { "localSendTest.busNodePort.1",
			    "localSendTest.connectorName.1",
			    "localSendTest.connectorLogin.1",
			    "localSendTest.connectorPassword.1",
			    "localSendTest.busNodePort.2",
			    "localSendTest.connectorName.2",
			    "localSendTest.connectorLogin.2",
			    "localSendTest.connectorPassword.2" };
			
			Properties testProps = loadTestProperties(REQUIRED_PROPS);
	
			// First connector arguments.
			final int BUS_NODE_PORT_1 = Integer.parseInt(testProps.getProperty(REQUIRED_PROPS[0]));
			final String CONNECTOR_NAME_1 = testProps.getProperty(REQUIRED_PROPS[1]);
			final String CONNECTOR_LOGIN_1 = testProps.getProperty(REQUIRED_PROPS[2]);
			final String CONNECTOR_PASSWORD_1 = testProps.getProperty(REQUIRED_PROPS[3]);
	
			// Second connector arguments.
			final int BUS_NODE_PORT_2 = Integer.parseInt(testProps.getProperty(REQUIRED_PROPS[4]));
			final String CONNECTOR_NAME_2 = testProps.getProperty(REQUIRED_PROPS[5]);
			final String CONNECTOR_LOGIN_2 = testProps.getProperty(REQUIRED_PROPS[6]);
			final String CONNECTOR_PASSWORD_2 = testProps.getProperty(REQUIRED_PROPS[7]);
	
			final String SENTENCE = "live long and prosper";
	
			// -- Test --
			Connector connector1 = new Connector() {
				public void run() {
					try {
						conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_1,
						    CONNECTOR_NAME_1, CONNECTOR_LOGIN_1, CONNECTOR_PASSWORD_1);
	
						ack = new Ack() {
							@Override
							public void onReceive(String message) {
								System.out.println("Receive on connector1: " + message);
								if (SENTENCE.toUpperCase().equals(message)) {
									ack = true;
								}
							}
						};
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
	
						ack = new Ack() {
							@Override
							public void onReceive(String message) {
								System.out.println("Receive on connector2: " + message);
								try {
									conn.send(message.toUpperCase(), CONNECTOR_NAME_1);
									ack = true;
								} catch (BusConnectorException e) {
									e.printStackTrace();
								}
							}
						};
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
	
			conn1IsAck = connector1.isAck();
    } catch (FileNotFoundException e1) {
    	fail(e1.getMessage());
    } catch (IOException e1) {
    	fail(e1.getMessage());
    } catch (RequiredPropertiesException e1) {
    	fail(e1.getMessage());
		}
    
    assertTrue("Error on Transmited message over Network", conn1IsAck);
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
	public void localBroadcastTest() {
		try {
			
			// Load properties
			final String[] REQUIRED_PROPS = { "localBroadcastTest.busNodePort.1",
			    "localBroadcastTest.connectorName.1",
			    "localBroadcastTest.connectorLogin.1",
			    "localBroadcastTest.connectorPassword.1",
			    "localBroadcastTest.busNodePort.2",
			    "localBroadcastTest.connectorName.2",
			    "localBroadcastTest.connectorLogin.2",
			    "localBroadcastTest.connectorPassword.2",
			    "localBroadcastTest.busNodePort.3",
			    "localBroadcastTest.connectorName.3",
			    "localBroadcastTest.connectorLogin.3",
			    "localBroadcastTest.connectorPassword.3" };
			
			Properties testProps = loadTestProperties(REQUIRED_PROPS);
	
			// First connector arguments.
			final int BUS_NODE_PORT_1 = Integer.parseInt(testProps.getProperty(REQUIRED_PROPS[0]));
			final String CONNECTOR_NAME_1 = testProps.getProperty(REQUIRED_PROPS[1]);
			final String CONNECTOR_LOGIN_1 = testProps.getProperty(REQUIRED_PROPS[2]);
			final String CONNECTOR_PASSWORD_1 = testProps.getProperty(REQUIRED_PROPS[3]);
	
			// Second connector arguments.
			final int BUS_NODE_PORT_2 = Integer.parseInt(testProps.getProperty(REQUIRED_PROPS[4]));
			final String CONNECTOR_NAME_2 = testProps.getProperty(REQUIRED_PROPS[5]);
			final String CONNECTOR_LOGIN_2 = testProps.getProperty(REQUIRED_PROPS[6]);
			final String CONNECTOR_PASSWORD_2 = testProps.getProperty(REQUIRED_PROPS[7]);
	
			// Third connector arguments.
			final int BUS_NODE_PORT_3 = Integer.parseInt(testProps.getProperty(REQUIRED_PROPS[8]));
			final String CONNECTOR_NAME_3 = testProps.getProperty(REQUIRED_PROPS[9]);
			final String CONNECTOR_LOGIN_3 = testProps.getProperty(REQUIRED_PROPS[10]);
			final String CONNECTOR_PASSWORD_3 = testProps.getProperty(REQUIRED_PROPS[11]);
	
			final String SENTENCE = "live long and prosper";
	
			// -- Test --
			Connector connector1 = new Connector() {
				public void run() {
					try {
						conn = new BusConnectorLocalHornetQ(BUS_NODE_PORT_1,
						    CONNECTOR_NAME_1, CONNECTOR_LOGIN_1, CONNECTOR_PASSWORD_1);
	
						ack = new Ack() {
							@Override
							public void onReceive(String msg) {
								System.out.println("Receive on connector1: " + msg);
								if (SENTENCE.equals(msg)) {
									ack = true;
								}
							}
						};
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
						ack = new Ack() {
							@Override
							public void onReceive(String msg) {
								System.out.println("Receive on connector2: " + msg);
								if (SENTENCE.equals(msg)) {
									ack = true;
								}
							}
						};
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
						ack = new Ack() {
							@Override
							public void onReceive(String msg) {
								System.out.println("Receive on connector3: " + msg);
								if (SENTENCE.equals(msg)) {
									ack = true;
								}
							}
						};
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
    } catch (FileNotFoundException e1) {
    	fail(e1.getMessage());
    } catch (IOException e1) {
    	fail(e1.getMessage());
    } catch (RequiredPropertiesException e1) {
    	fail(e1.getMessage());
		}
	}

	// ************************************************************* Test Utils **
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
