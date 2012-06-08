package fr.emn.app.criojo.network;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.emn.criojo.network.BusConnector;
import fr.emn.criojo.network.BusConnectorException;
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
}
