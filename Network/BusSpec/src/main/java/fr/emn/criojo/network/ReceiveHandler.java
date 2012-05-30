package fr.emn.criojo.network;

/**
 * A ReceiveHandler is used to receive message asynchronously. To receive
 * messages asynchronously, a ReceiveHandler is set on a Bus. Every time the bus
 * will receive a message, it will call the handler's onReceive() method.
 */
public interface ReceiveHandler {

  /**
   * Notifies the {@link ReceiveHandler} that a message has been received.
   * 
   * @param message
   *          A message.
   */
  public void onReceive(String message);
}

