import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ExampleEmailSender {

    public static void main(String[] args) {
	
	//Represents the user's email username and password. Keep in mind that all of these are
	//case sensitive and will fail if any one of these is spelled incorrectly
        final String username = JOptionPane.showInputDialog("Username?");
        final String password = JOptionPane.showInputDialog("Password?");

	//Do not touch - sets up the connection between gmail smtp server and computer
        Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
	//Checks to see if the user's credentials are correct. The user must also enable third
	//party services (done via settings) on their email provider to make this work
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
	
	// Sends the message - will be edited to be more varied in the future
        try {

            Message message = new MimeMessage(session);
	    final String recipientAddress = JOptionPane.showInputDialog("To who? (Email Address)");
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientAddress)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

	//Catches any errors that may occur
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
