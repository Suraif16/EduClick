package Controller;

import EmailSMTP.Email;
import Model.GenerateOTP.OTPGeneration;
import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OTPGenerateServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ){

        System.out.println("optGenerate 1");
        HttpSession session = request.getSession( false );

        OTPGeneration otpGeneration = new OTPGeneration();

        String OTPCode = otpGeneration.generateOTP( 8 );

        session.setAttribute("OPT Code" , OTPCode );

        System.out.println(OTPCode);

        Email email = new Email();

        String subject = "EduClick Registration: OTP Code.";

        String message = "Welcome to EduClick.\n\nPlease enter OTP: " + OTPCode + " to complete your Registration." +
                "\n\nThank you - EduClick.";

        email.sendMail( (String) session.getAttribute( "Email") , subject, message);



    }

}
