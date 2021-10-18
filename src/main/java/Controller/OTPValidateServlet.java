package Controller;

import Model.Login;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class OTPValidateServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {
        /* Here we get the otp value given by the user and the otp value in the session, we compare them, and if they are equal we allow
        * user to continue, else the user has to retry */
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();

        String OPTUserValue = request.getParameter( "OPTUserValue" );

        HttpSession session = request.getSession( false );

        String OTPValue = (String) session.getAttribute( "OPT Code" );

        User user = (User) session.getAttribute( "User" );

        String email = ( String ) session.getAttribute( "Email" );

        Login login = new Login( email );
        if(OPTUserValue.equals(OTPValue)){

            jsonObject.put( "OTPStatus" , "valid" );

            if( user.getUserType().equals("Teacher") ){

                jsonObject.put("UserType" , "Teacher");

            }else if (user.getUserType().equals("Student")){

                jsonObject.put("UserType" , "Student");

            }

            String otpStatus = (String) session.getAttribute("optStatus");

            if ( otpStatus.equals("Registration") ){
                /* if it was a registration verification then emailconfirmation is updated to true in the database*/
                login.setEmailConfirmation("True");
                login.updateEmailConfirmation();

            }else if( otpStatus.equals("Login") ){
                /* if it was a login password incorrect verification then passwordincorrect is updated to true in the database*/
                login.setPasswordIncorrect( "False" );
                login.updatePasswordIncorrect();

            }


        }else{

            jsonObject.put( "OTPStatus" , "invalid" );

        }

        out.write(jsonObject.toString());
        out.close();

    }

}
