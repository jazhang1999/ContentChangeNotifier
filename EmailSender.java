import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.IOException;

public class EmailSender{

    public static void sendMessage(String username, String password, String recipient, String messageToSend) 
    {	
	//Do not touch - sets up the connection between gmail smtp server and computer
        Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
	//Checks to see if the user's credentials are correct. The user must also enable third
	//party services (done via settings) on their email provider to make this work
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
	
	// Sends the message - will be edited to be more varied in the future
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipient)
            );
            message.setSubject("Testing");
	    
            message.setText(messageToSend);

            Transport.send(message);

            System.out.println("Message sent");

	//Catches any errors that may occur
        } catch (MessagingException e) {
            e.printStackTrace();
        } 
    }

}

