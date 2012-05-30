package fr.emn.criojo.network;

/**
 * Abstract factory to instantiate concrete {@link BusConnector}.
 */
public interface BusConnectorFactory {

	/**
	 * Create a connector on the bus
	 * 
	 * @param url
	 *          Hot to access on the bus.
	 * @return a {@link BusConnector}.
	 * @throws BusConnectorFactoryException
	 *           An error occur during {@link BusConnector} instantiation.
	 * @throws BusConnectorException
	 *           An error occur during {@link BusConnector} instantiation.
	 */
	public BusConnector createConnector(String url)
	    throws BusConnectorFactoryException, BusConnectorException;
}

