package EmailSMTP;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {

    public static void main(String[] args){

        final String username = "educlickorg@gmail.com";
        final String password = "hckujhhwzxqijdsd";

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
            message.setFrom( new InternetAddress("EduClick") );
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("farzanroxz123@gmail.com")
            );

            message.setSubject("Testing");
            message.setText("Dear me" +
                    "\n\n please wait... frosty");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("error");
        }

        System.out.println("complete");

    }
}
