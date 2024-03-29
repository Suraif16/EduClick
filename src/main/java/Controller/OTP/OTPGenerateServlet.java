package Controller.OTP;

import EmailSMTP.Email;
import Model.GenerateRandomNumbers.OTPGeneration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OTPGenerateServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ){
        /* Here we generate an OTP code and add it to session, also we get the email using the user object in the session
        * and then we send this generated OTP to the users via email */
        HttpSession session = request.getSession( false );

        OTPGeneration otpGeneration = new OTPGeneration();

        String OTPCode = otpGeneration.generateOTP( 8 );

        System.out.println("OTP : " + OTPCode);

        session.setAttribute("OPT Code" , OTPCode );

        Email email = new Email();

        String subject = "EduClick Registration: OTP Code.";

        String message = "Welcome to EduClick.\n\nPlease enter OTP: " + OTPCode + " to complete your Registration." +
                "\n\nThank you - EduClick.";

        email.sendMail( (String) session.getAttribute( "Email") , subject, message);

    }

}
