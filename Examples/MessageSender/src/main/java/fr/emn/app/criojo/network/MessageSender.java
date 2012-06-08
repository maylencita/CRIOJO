package fr.emn.app.criojo.network;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.emn.criojo.network.BusConnector;
import fr.emn.criojo.network.BusConnectorException;
import fr.emn.criojo.network.BusConnectorFactory;
import fr.emn.criojo.network.BusConnectorFactoryException;
import fr.emn.criojo.network.BusConnectorLocalHornetQFactory;
import fr.emn.criojo.network.BusConnectorRemoteHornetQFactory;
import fr.emn.criojo.network.ReceiveHandler;

public class MessageSender {
	private BusConnector conn;
	private String name;
	private JFrame frame;
	private JTextArea messageBufferTextArea;
	private JTextField messageTextField, userTextField;

	public MessageSender(BusConnector conn) {
		this.conn = conn;
		this.name = conn.getName();

		try {
			frame = new JFrame("MessageBox " + this.name);
			frame.setLayout(new GridBagLayout());

			initComponents();
			initListeners();
			placeComponents();

			frame.pack();
			frame.setSize(300, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (BusConnectorException e) {
			e.printStackTrace();
		}
	}

	public void send(String msg, String recipientName) {
		try {
			conn.send(name + ": " + msg, recipientName);
		} catch (BusConnectorException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		// -- Message Buffer
		messageBufferTextArea = new JTextArea();
		messageBufferTextArea.setEditable(false);

		// -- User
		userTextField = new JTextField("User Name");

		// -- Message
		messageTextField = new JTextField("Message");
	}

	private void initListeners() throws BusConnectorException {
		userTextField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				userTextField.setText("");
			}
		});

		messageTextField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				messageTextField.setText("");
			}
		});

		// Press enter to send msg.
		messageTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String recipientName = (!userTextField.getText().trim().isEmpty()) ? userTextField
					    .getText().trim() : "NoName";
					String msg = messageTextField.getText().trim();
					messageTextField.setText("");
					send(msg, recipientName);
				}
			}
		});

		// Every new msg receive, msg is added to buffer
		conn.setReceiveHandler(new ReceiveHandler() {
			@Override
			public void onReceive(String msg) {
				messageBufferTextArea.setText(messageBufferTextArea.getText() + "\n"
				    + msg);
			}
		});
	}

	private void placeComponents() {
		GridBagConstraints gbc = new GridBagConstraints();

		// -------------------------------------------------------------------------
		// First Line
		// -------------------------------------------------------------------------

		// -- Message Buffer
		gbc.gridx = 0;
		gbc.gridy = 0;

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;

		gbc.weightx = 1.;
		gbc.weighty = 1.;

		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 15, 0, 10);
		frame.add(messageBufferTextArea, gbc);

		// -------------------------------------------------------------------------
		// Second Line
		// -------------------------------------------------------------------------

		// -- User
		gbc.gridx = 0;
		gbc.gridy = 1;

		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.weightx = 0.1;
		gbc.weighty = 0.;

		gbc.insets = new Insets(10, 15, 0, 0);
		frame.add(userTextField, gbc);

		// -- Message
		gbc.gridx = 1;
		gbc.gridy = 1;

		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;

		gbc.weightx = 0.9;
		gbc.weighty = 0.;

		gbc.insets = new Insets(10, 0, 0, 10);
		frame.add(messageTextField, gbc);
	}

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

		// System.out.println("\n* Defined connector of second MessageSender *");
		// startMessageSender(factory, bufferRead);
	}
}
