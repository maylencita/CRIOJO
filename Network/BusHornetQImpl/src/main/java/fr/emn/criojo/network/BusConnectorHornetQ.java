package fr.emn.criojo.network;

public interface BusConnectorHornetQ extends BusConnector {
	/**
	 * Queue suffix address for personal message.
	 */
	public static final String PERSONAL = "personal";
	
	/**
	 * Queue address for broadcast message.
	 */
	public static final String BROADCAST = "broadcast";
	
	/**
	 * Acknowledgment message from success subscription.
	 * 
	 * Message form send after success subscription. The message would have the
	 * form <code>##---## Ack of queueName - uniqueid ##---##</code>. With queue
	 * name is the queueName (something like broadcast.foo or personal.bar) and
	 * uniqueid a unique identifier.
	 * 
	 * All messages with this type are not transmitted to Upper layer, they are
	 * just interpreted by connector. For performance requirements, all message
	 * started with "##---## Ack of " are considered as acknowledge message. For a
	 * better test it's possible to use regexp with
	 * <code>##---## Ack of .+? - .+? ##---##</code>.
	 */
	public static final String ACK_MESSAGE = "##---## Ack of ";
}
