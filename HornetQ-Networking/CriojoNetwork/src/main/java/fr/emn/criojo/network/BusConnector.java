package fr.emn.criojo.network;

/**
 * Bus Connector specification for Criojo Networking.
 * 
 * A Bus connector is used to connect on bus. Bus is media to interconnect
 * many Criojo Agent over network. The Bus is in charge of routing message
 * with secure and safe perspectives.
 */
public interface BusConnector {

  /**
   * Send message over the bus to a specific recipient.
   * 
   * @param message
   *          The message to send.
   * @param recipientName
   *          The recipient of message.
   * @throws BusConnectorException Troubles during sending message.
   */
  public void send(String message, String recipientName) throws BusConnectorException;

  /**
   * Sets the ReceiveHandler for this consumer to consume messages
   * asynchronously.
   * 
   * @param receiveHandler
   *          A ReceiveHandler.
   * @throws BusConnectorException Troubles during setting handler.
   */
  public void setReceiveHandler(ReceiveHandler receiveHandler)
      throws BusConnectorException;
  
  /**
   * Disconnect the connector from Bus.
   */
  public void disconnect();
  
  /**
   * Tests if connector is disconnected from bus.
   * 
   * @return <code>True</code> if connector is disconnected, <code>False</code>
   *         otherwise.
   */
  public boolean isDisconected();
  
  /**
   * Returns the connector name would be unique on Bus.
   * 
   * @return	The connector name.
   */
  public String getName();
}
