package fr.emn.criojo.network;

public class BusConnectorRemoteHornetQFactory implements BusConnectorFactory {
	
	/**
	 * Connect on Bus HornetQ already start.
	 * 
	 * This factory instantiate a connector on bus node HornetQ. The bus node have
	 * to be already started and the location have to be specified in url. The url
	 * form is "host:port:name:login:password" with:
	 * <ul>
	 * <li>host: The Bus Node address (HornetQ Connector address);</li>
	 * <li>port: The Bus Node Port (HornetQ Connector Port);</li>
	 * <li>name: The new Connector Name (unique in cluster);</li>
	 * <li>login: The user login (default guest);</li>
	 * <li>password: The user password (default guest).</li>
	 * </ul>
	 */
	@Override
	public BusConnector createConnector(String url)
	    throws BusConnectorFactoryException, BusConnectorException {
		BusConnector bus = null;

		String[] params = url.split(":");
		if (params.length == 3) {
			bus = createConnector(params[0], Integer.valueOf(params[1]), params[2]);
		} else if (params.length == 5) {
			bus = createConnector(params[0], Integer.valueOf(params[1]), params[2],
			    params[3], params[4]);
		} else {
			throw new BusConnectorFactoryException(
			    "Can't create BusConnectorRemoteHornetQ with following "
			        + "url. The default format is \"host:port:name:login:password\" with :"
			        + "\thost: The Bus Node address (HornetQ Connector address);"
			        + "\tport: The Bus Node Port (HornetQ Connector Port);"
			        + "\tname: The new Connector Name (unique in cluster);"
			        + "\tlogin: The user login (default guest);"
			        + "\tpassword: The user password (default guest).");
		}

		return bus;
	}

	/**
	 * Create connector on existing HornetQ cluster.
	 * 
	 * @param host
	 *          The Bus Node address (HornetQ Connector address).
	 * @param port
	 *          The Bus Node Port (HornetQ Connector Port).
	 * @param name
	 *          The new Connector Name (unique in cluster).
	 * @param login
	 *          The user login (may be guest).
	 * @param password
	 *          The user password (may be guest).
	 * @return The {@link BusConnectorRemoteHornetQ}.
	 * 
	 * @throws BusConnectorException
	 *           Error {@link BusConnector} instantiation.
	 */
	public BusConnector createConnector(String host, int port, String name,
	    String login, String password) throws BusConnectorException {
		return new BusConnectorRemoteHornetQ(host, port, name, login, password);
	}

	/**
	 * Create connector on existing HornetQ cluster with user guest and password
	 * guest.
	 * 
	 * @param host
	 *          The Bus Node address (HornetQ Connector address).
	 * @param port
	 *          The Bus Node Port (HornetQ Connector Port).
	 * @param name
	 *          The new Connector Name (unique in cluster).
	 * @return The {@link BusConnectorRemoteHornetQ}.
	 * 
	 * @throws BusConnectorException
	 *           Error {@link BusConnector} instantiation.
	 */
	public BusConnector createConnector(String host, int port, String name)
	    throws BusConnectorException {
		final String login = "guest";
		final String password = "guest";

		return new BusConnectorRemoteHornetQ(host, port, name, login, password);
	}
}
