package fr.emn.criojo.network;

public class BusConnectorLocalHornetQWithManagementFactory implements
    BusConnectorFactory {

	/**
	 * Connect on Local Bus HornetQ.
	 * 
	 * This factory instantiate a connector on bus node HornetQ. The bus node have
	 * to be on localhost. If no HornetQ node run on localhost, the connector will
	 * start a new bus node. The new bus node is start in cluster mode with
	 * configuration information given in the url. The bus node have. The url form
	 * is "port:name:login:password:stompWSPort:b-castAddress:b-castPort" with:
	 * <ul>
	 * <li>port: The Bus Node Port (HornetQ Connector Port);</li>
	 * <li>name: The new Connector Name (unique in cluster);</li>
	 * <li>login: The user login (default guest);</li>
	 * <li>password: The user password (default guest);</li>
	 * <li>stompWSPort: The Stomp over WebSocket acceptor Port (HornetQ stomp_ws,
	 * default is BusConnectorLocalHornetQ.DEFAULT_STOMPWEBSOCKET_PORT);</li>
	 * <li>b-castAddress: The broadcast address (HornetQ broadcast, discovery,
	 * default is BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS);</li>
	 * <li>b-castPort: The broadcast port (HornetQ broadcast, discovery, default
	 * is BusConnectorLocalHornetQ.DEFAULT_BROADCAST_PORT).</li>
	 * </ul>
	 */
	@Override
	public BusConnectorLocalHornetQWithManagement createConnector(String url)
	    throws BusConnectorFactoryException, BusConnectorException {
		BusConnectorLocalHornetQWithManagement bus = null;

		String[] params = url.split(":");
		if (params.length == 2) {
			bus = createConnector(Integer.valueOf(params[0]), params[1]);
		} else if (params.length == 4) {
			bus = createConnector(Integer.valueOf(params[0]), params[1], params[2],
			    params[3]);
		} else if (params.length == 7) {
			bus = createConnector(Integer.valueOf(params[0]), params[1], params[2],
			    params[3], Integer.valueOf(params[4]), params[5],
			    Integer.valueOf(params[6]));
		} else {
			throw new BusConnectorFactoryException(
			    "Can't create BusConnectorLocalHornetQ with following "
			        + "url. The default format is \"host:port:name:login:password\" with :"
			        + "\n\tport: The Bus Node Port (HornetQ Connector Port);"
			        + "\n\tname: The new Connector Name (unique in cluster);"
			        + "\n\tlogin: The user login (default guest);"
			        + "\n\tpassword: The user password (default guest);"
			        + "\n\tstompWSPort: The Stomp over WebSocket acceptor Port "
			        + "(HornetQ stomp_ws, default is "
			        + "BusConnectorLocalHornetQ.DEFAULT_STOMPWEBSOCKET_PORT);"
			        + "\n\tb-castAddress: The broadcast address (HornetQ broadcast, "
			        + "discovery, default is "
			        + "BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS);"
			        + "\n\tb-castPort: The broadcast port (HornetQ broadcast, "
			        + "discovery, default is "
			        + "BusConnectorLocalHornetQ.DEFAULT_BROADCAST_PORT).");
		}

		return bus;
	}

	/**
	 * Create connector on local node HornetQ .
	 * 
	 * If no local bode HornetQ already started, this implementation will start a
	 * new HornetQ server. The new server is started in cluster mode with
	 * discovery and broadcast group set with given parameters.
	 * 
	 * @param port
	 *          The Bus Node Port (HornetQ Connector Port).
	 * @param name
	 *          The new Connector Name (unique in cluster).
	 * @param login
	 *          The user login (may be guest).
	 * @param password
	 *          The user password (may be guest).
	 * @param stompWSPort
	 *          The Stomp over WebSocket acceptor port (if any).
	 * @param broadcastAddress
	 *          The BroadcastAddress of new local server (if any).
	 * @param broadcastPort
	 *          The BroadcastPort of new local server (if any).
	 * @return The {@link BusConnectorLocalHornetQWithManagement}.
	 * 
	 * @throws BusConnectorException
	 *           Error {@link BusConnector} instantiation.
	 */
	public BusConnectorLocalHornetQWithManagement createConnector(int port,
	    String name, String login, String password, int stompWSPort,
	    String broadcastAddress, int broadcastPort) throws BusConnectorException {
		return new BusConnectorLocalHornetQWithManagement(port, name, login,
		    password, stompWSPort, broadcastAddress, broadcastPort);
	}

	/**
	 * Create connector on local node HornetQ.
	 * 
	 * If no local bode HornetQ already started, this implementation will start a
	 * new HornetQ server. The new server is started in cluster mode with
	 * discovery and broadcast group on
	 * BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS and
	 * BusConnectorLocalHornetQ.DEFAULT_BROADCAST_PORT.
	 * 
	 * @param port
	 *          The Bus Node Port (HornetQ Connector Port).
	 * @param name
	 *          The new Connector Name (unique in cluster).
	 * @param login
	 *          The user login (may be guest).
	 * @param password
	 *          The user password (may be guest).
	 * 
	 * @return The {@link BusConnectorLocalHornetQWithManagement}.
	 * 
	 * @throws BusConnectorException
	 *           Error {@link BusConnector} instantiation.
	 */
	public BusConnectorLocalHornetQWithManagement createConnector(int port,
	    String name, String login, String password) throws BusConnectorException {
		return new BusConnectorLocalHornetQWithManagement(port, name, login,
		    password);
	}

	/**
	 * Create connector on local node HornetQ.
	 * 
	 * If no local bode HornetQ already started, this implementation will start a
	 * new HornetQ server. The new server is started in cluster mode with
	 * discovery and broadcast group on
	 * BusConnectorLocalHornetQ.DEFAULT_BROADCAST_ADDRESS and
	 * BusConnectorLocalHornetQ.DEFAULT_BROADCAST_PORT. Moreover, this factory use
	 * "guest" as default login and password.
	 * 
	 * @param port
	 *          The Bus Node Port (HornetQ Connector Port).
	 * @param name
	 *          The new Connector Name (unique in cluster).
	 * @return The {@link BusConnectorLocalHornetQWithManagement}.
	 * 
	 * @throws BusConnectorException
	 *           Error {@link BusConnector} instantiation.
	 */
	public BusConnectorLocalHornetQWithManagement createConnector(int port,
	    String name) throws BusConnectorException {
		final String login = "guest";
		final String password = "guest";

		return new BusConnectorLocalHornetQWithManagement(port, name, login,
		    password);
	}
}
