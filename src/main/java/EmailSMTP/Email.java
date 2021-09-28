package EmailSMTP;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public static void main(String[] args){

        final String username = "farzanroxz123@gmail.com";
        final String password = "farzanbigbang1@3";

        Properties properties = new Properties();
        properties.put("mail.smtp.host" , "smtp.gmail.com");
        properties.put("mail.smtp.port" , "587");
        properties.put("mail.smtp.auth" , "true");
        properties.put("mail.smtp.starttls.enable" , "true"); //TLS

        Session session = Session.getInstance( properties ,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication( username , password );
                    }
                }
        );

        try{
            Message message = new MimeMessage( session );
            message.setFrom( new InternetAddress("farzanroxz123@gamil.com"));
            message.setRecipient(
                    Message.RecipientType.TO,
                    InternetAddress.parse("m.farzan.rizwan@gmail.com , farzanroxz123@gmail.com")
            );

            message.getSubject("Testing Gmail TLS");
            message.setText("Dear me" +
                    "\n\n please wait...");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
