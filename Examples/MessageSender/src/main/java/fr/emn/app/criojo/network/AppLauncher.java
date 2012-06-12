package fr.emn.app.criojo.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.emn.criojo.network.BusConnector;
import fr.emn.criojo.network.BusConnectorException;
import fr.emn.criojo.network.BusConnectorFactory;
import fr.emn.criojo.network.BusConnectorFactoryException;
import fr.emn.criojo.network.BusConnectorLocalHornetQFactory;
import fr.emn.criojo.network.BusConnectorRemoteHornetQFactory;

public class AppLauncher {
	/**
	 * Let User choose type of bus connection.
	 */
	private static BusConnectorFactory chooseFactory(BufferedReader bufferRead) {
		BusConnectorFactory factory = null;
		boolean stop = false;

		while (!stop) {
			int choice = -1;

			try {
				System.out.print("0 - quit; 1 - LocalHornetQ; 2 - RemoteHornetQ: ");
				choice = Integer.parseInt(bufferRead.readLine().trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switch (choice) {
			case 0:
				stop = true;
				break;
			case 1:
				factory = new BusConnectorLocalHornetQFactory();
				stop = true;
				break;
			case 2:
				factory = new BusConnectorRemoteHornetQFactory();
				stop = true;
				break;
			default:
				System.out.println("Wrong choice, try again ;)");
				break;
			}
		}

		return factory;
	}

	/**
	 * Let user defined hot to connect on bus.
	 */
	private static BusConnector connect(BusConnectorFactory factory,
	    BufferedReader bufferRead) {
		BusConnector connector = null;
		boolean stop = false;

		while (!stop) {
			System.out.print("Url to connect (empty to quit): ");
			String choice = "";
			try {
				choice = bufferRead.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!choice.isEmpty()) {
				try {
					connector = factory.createConnector(choice);
					stop = true;
				} catch (BusConnectorFactoryException e) {
					System.out.println("Wrong url, try again ;)\n" + e.getMessage());
				} catch (BusConnectorException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					stop = true;
				}
			} else {
				stop = true;
			}
		}

		return connector;
	}

	/**
	 * Start new MessageSender, if user give correct informations.
	 */
	private static void startMessageSender(BusConnectorFactory factory,
	    BufferedReader bufferRead) {
		BusConnector connector;
		if (factory != null) {
			connector = connect(factory, bufferRead);

			if (connector != null) {
				new MessageSender(connector);
			}
		}
	}

	public static void main(String[] args) {
		BusConnectorFactory factory = null;

		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
		    System.in));

		factory = chooseFactory(bufferRead);

		System.out.println("\n* Defined connector of first MessageSender *");
		startMessageSender(factory, bufferRead);

		System.out.println("\n* Defined connector of second MessageSender *");
		startMessageSender(factory, bufferRead);
	}
}
