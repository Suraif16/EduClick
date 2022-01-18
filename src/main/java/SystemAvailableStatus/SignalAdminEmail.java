package SystemAvailableStatus;

import EmailSMTP.Email;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SignalAdminEmail implements ServletContextListener {

    private Thread databaseThread = null;
    private ServletContext context;
    final private String emailAddress = "educlickorg@gmail.com";
    final private String subject = "System Status";
    final private String message  = "EduClick\n\nHourly system status";


    public void contextInitialized( ServletContextEvent contextEvent ){

        databaseThread = new Thread(){

            public void run(){

                try {

                    while( true ){

                        Email email = new Email();
                        email.sendMail( emailAddress , subject , message );

                        Thread.sleep( 5000 );

                    }

                }catch ( InterruptedException ignored ){}

            }

        };

        databaseThread.start();
        context = contextEvent.getServletContext();

    }

    public void contextDestroyed( ServletContextEvent contextEvent ){

        databaseThread.interrupt();

    }

}
