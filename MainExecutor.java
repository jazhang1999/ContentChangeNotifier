import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;
import java.util.*;
//Imported tools to use
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.lang.NumberFormatException;
import javax.swing.*;

public class MainExecutor extends JPanel
{
	String username = "";
	String password = "";
	String recipient = "";
	String searchTerm = "";
	String message = "";

	private JTextArea display;

	public static void main(String[] args)
	{ 	
		// GUI code to get user input
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	// Code below for the GUI -----------------------------------------------------------------
	// Shows the GUI as a popup box
	private static void createAndShowGUI() {
		int width = 500, height = 300;
		JFrame frame = new JFrame("Text File Reader");
		frame.setSize(new Dimension(width, height));
		frame.setContentPane(new MainExecutor(width, height)); /* Adds the panel to the frame */

		/* Centralizing the frame */
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int upperLeftCornerX = (screenSize.width - frame.getWidth()) / 2;
		int upperLeftCornerY = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(upperLeftCornerX, upperLeftCornerY);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		/* Shows the GUI */
		frame.setVisible(true);
	}

	private JLabel usernameLabel = new JLabel("Username:  ");
	private JTextField usernameTextField = new JTextField(30);
	private JLabel passwordLabel = new JLabel("Password: ");
	private JTextField passwordTextField = new JTextField(30);
	private JLabel recipientLabel = new JLabel("Recipient: ");
	private JTextField recipientTextField = new JTextField(30);
	private JLabel searchTermLabel = new JLabel("URL to search: ");
	private JTextField searchTermTextField = new JTextField(30);

	public MainExecutor(int frameWidth, int frameHeight) {
		// Adds in all the lables and textfields mentioned above
		add(usernameLabel);
		add(usernameTextField);
		add(passwordLabel);
		add(passwordTextField);
		add(recipientLabel);
		add(recipientTextField);
		add(searchTermLabel);
		add(searchTermTextField);

		/* Defines and adds button to trigger simple interest calculations */
		JButton submitItemsButton = new JButton("Submit Items");
		add(submitItemsButton);

		// Anonymous inner class
		submitItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				username = usernameTextField.getText();
				password = passwordTextField.getText();
				recipient = recipientTextField.getText();
				searchTerm = searchTermTextField.getText();
				System.out.println("Items have been parsed");

				try
				{
					message = PageParser.getMessage(searchTerm);
					display.setText(message);

					// Save to a JSON file before sending
					if(WriteRecord.record(message, searchTerm))
					{
						EmailSender.sendMessage(username, password, recipient, message);
						display.append("\n Email notification has been sent to: " + recipient);
					}
					else
					{
						display.append("\n Email notification has not been sent, not change in update dates");
					}
				}
				//catch (IOException g)
				catch (Exception g)
				{
					g.printStackTrace();
					display.setText(g.toString());
				}

			}
		});

		display = new JTextArea();
		display.setFont(new Font("serif", Font.BOLD, 14));
		add(display);
	}
}
