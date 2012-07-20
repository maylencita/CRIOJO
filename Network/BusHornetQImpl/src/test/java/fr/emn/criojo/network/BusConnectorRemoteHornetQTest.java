package fr.emn.criojo.network;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.config.Configuration;
import org.hornetq.core.deployers.impl.FileConfigurationParser;
import org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory;
import org.hornetq.core.server.HornetQServer;
import org.hornetq.core.server.HornetQServers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BusConnectorRemoteHornetQTest {
  final static String HOST = "localhost";
  final static int PORT = 5445;
  final static int STOMP_PORT = 61614;
  final static String BROADCAST_ADDRESS = "231.7.7.7";
  final static int BROADCAST_PORT = 9876;

  static HornetQServer server = null;

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
  public void testRemoteSend() {

    // First connector arguments.
    final int BUS_NODE_PORT_1 = PORT;
    final String CONNECTOR_NAME_1 = "Foo";
    final String CONNECTOR_LOGIN_1 = "guest";
    final String CONNECTOR_PASSWORD_1 = "guest";

    // Second connector arguments.
    final int BUS_NODE_PORT_2 = PORT;
    final String CONNECTOR_NAME_2 = "Bar";
    final String CONNECTOR_LOGIN_2 = "guest";
    final String CONNECTOR_PASSWORD_2 = "guest";

    final String SENTENCE = "live long and prosper";

    // -- Test --
    Connector connector1 = new Connector() {
      public void run() {
        try {
          conn = new BusConnectorRemoteHornetQ(HOST, BUS_NODE_PORT_1,
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
          }
        }
      }
    };

    Connector connector2 = new Connector() {
      public void run() {
        try {
          conn = new BusConnectorRemoteHornetQ(HOST, BUS_NODE_PORT_2,
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
  public void testRemoteBroadcast() {
    // First connector arguments.
    final int BUS_NODE_PORT_1 = PORT;
    final String CONNECTOR_NAME_1 = "Foo";
    final String CONNECTOR_LOGIN_1 = "guest";
    final String CONNECTOR_PASSWORD_1 = "guest";

    // Second connector arguments.
    final int BUS_NODE_PORT_2 = PORT;
    final String CONNECTOR_NAME_2 = "Bar";
    final String CONNECTOR_LOGIN_2 = "guest";
    final String CONNECTOR_PASSWORD_2 = "guest";

    // Second connector arguments.
    final int BUS_NODE_PORT_3 = PORT;
    final String CONNECTOR_NAME_3 = "Baz";
    final String CONNECTOR_LOGIN_3 = "guest";
    final String CONNECTOR_PASSWORD_3 = "guest";

    final String SENTENCE = "live long and prosper";

    // -- Test --
    Connector connector1 = new Connector() {
      public void run() {
        try {
          conn = new BusConnectorRemoteHornetQ(HOST, BUS_NODE_PORT_1,
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
          }
        }
      }
    };

    Connector connector2 = new Connector() {
      public void run() {
        try {
          conn = new BusConnectorRemoteHornetQ(HOST, BUS_NODE_PORT_2,
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
          }
        }
      }
    };

    Connector connector3 = new Connector() {
      public void run() {
        try {
          conn = new BusConnectorRemoteHornetQ(HOST, BUS_NODE_PORT_3,
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

  //******************************************************* Test Before/After **
  @BeforeClass public static void init() {
    System.out.println("Start HornetQ server ...");
    try {
      Configuration configuration;
      configuration = new FileConfigurationParser()
          .parseMainConfig(new ByteArrayInputStream(configurationXML()
              .getBytes()));

      configuration.setPersistenceEnabled(false);
      configuration.setSecurityEnabled(false);

      configuration.getAcceptorConfigurations().add(
          new TransportConfiguration(NettyAcceptorFactory.class.getName()));
      server = HornetQServers.newHornetQServer(configuration);
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  @AfterClass public static void releaseMemory() {
    System.out.println("Stop HornetQ server ...");
    if (server != null) {
      try {
        server.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
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

  private static String configurationXML() {
    return "<configuration xmlns=\"urn:hornetq\""
        + "\n               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
        + "\n               xsi:schemaLocation=\"urn:hornetq /schema/hornetq-configuration.xsd\">"
        + "\n"
        + "\n   <clustered>true</clustered>"
        + "\n   "
        + "\n   <paging-directory>data/paging</paging-directory>"
        + "\n   "
        + "\n   <bindings-directory>data/bindings</bindings-directory>"
        + "\n   "
        + "\n   <journal-directory>data/journal</journal-directory>"
        + "\n   "
        + "\n   <large-messages-directory>data/large-messages</large-messages-directory>"
        + "\n"
        + "\n   <connectors>"
        + "\n      <connector name=\"netty\">"
        + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyConnectorFactory</factory-class>"
        + "\n         <param key=\"host\"  value=\"" + HOST + "\"/>"
        + "\n         <param key=\"port\"  value=\"" + PORT + "\"/>"
        + "\n      </connector>"
        + "\n   </connectors>"
        + "\n"
        + "\n   <acceptors>"
        + "\n      <acceptor name=\"netty\">"
        + "\n         <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
        + "\n         <param key=\"host\"  value=\"0.0.0.0\"/>"
        + "\n         <param key=\"port\"  value=\"" + PORT + "\"/>"
        + "\n      </acceptor>"
        + "\n      "
        + "\n      <acceptor name=\"stomp-acceptor\">"
        + "\n        <factory-class>org.hornetq.core.remoting.impl.netty.NettyAcceptorFactory</factory-class>"
        + "\n        <param key=\"protocol\" value=\"stomp_ws\" />"
        + "\n        <param key=\"port\" value=\"" + STOMP_PORT + "\" />"
        + "\n      </acceptor>"
        + "\n   </acceptors>"
        + "\n"
        + "\n   <broadcast-groups>"
        + "\n      <broadcast-group name=\"bg-group1\">"
        + "\n         <group-address>" + BROADCAST_ADDRESS + "</group-address>"
        + "\n         <group-port>" + BROADCAST_PORT + "</group-port>"
        + "\n         <broadcast-period>5000</broadcast-period>"
        + "\n         <connector-ref>netty</connector-ref>"
        + "\n      </broadcast-group>"
        + "\n   </broadcast-groups>"
        + "\n"
        + "\n   <discovery-groups>"
        + "\n      <discovery-group name=\"dg-group1\">"
        + "\n         <group-address>" + BROADCAST_ADDRESS + "</group-address>"
        + "\n         <group-port>" + BROADCAST_PORT + "</group-port>"
        + "\n         <refresh-timeout>10000</refresh-timeout>"
        + "\n      </discovery-group>"
        + "\n   </discovery-groups>"
        + "\n   "
        + "\n   <cluster-connections>"
        + "\n      <cluster-connection name=\"my-cluster\">"
        + "\n         <address>jms</address>   "
        + "\n         <connector-ref>netty</connector-ref>"
        + "\n       <discovery-group-ref discovery-group-name=\"dg-group1\"/>"
        + "\n      </cluster-connection>"
        + "\n   </cluster-connections>"
        + "\n   "
        + "\n   <security-settings>"
        + "\n      <security-setting match=\"#\">"
        + "\n         <permission type=\"createDurableQueue\" roles=\"guest\"/>"
        + "\n         <permission type=\"deleteDurableQueue\" roles=\"guest\"/>"
        + "\n         <permission type=\"createNonDurableQueue\" roles=\"guest\"/>"
        + "\n         <permission type=\"deleteNonDurableQueue\" roles=\"guest\"/>"
        + "\n         <permission type=\"consume\" roles=\"guest\"/>"
        + "\n         <permission type=\"send\" roles=\"guest\"/>"
        + "\n      </security-setting>"
        + "\n   </security-settings>"
        + "\n"
        + "\n</configuration>";
  }
}

