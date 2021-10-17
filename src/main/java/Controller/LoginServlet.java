package Controller;

import Model.Admin;
import Model.Login;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        LocalDate loginDate = LocalDate.now();
        LocalTime loginTime = LocalTime.now();

        Login login = new Login( email , password , loginDate , loginTime);
        JSONObject jsonObjectLoginStatus = login.checkPassword();/*here we check for the password
        depending on the return value we make the decisions as below
        if loginStatus has an empty string then it is an admin ,
        if loginStatus has an id then it is an user*/



        if ( jsonObjectLoginStatus.get( "pswd" ).equals( "" ) && jsonObjectLoginStatus.get( "userid" ).equals( "" )){

            session.invalidate();
            jsonObject.put( "User" , "User does not exist");

        }else if( jsonObjectLoginStatus.get("pswd").equals( password ) ){

            if( jsonObjectLoginStatus.get( "userid" ).equals("") ){

                Admin admin = new Admin( "Admin" , email );
                session.setAttribute( "Admin" , admin );
                jsonObject.put( "User" , "Admin");

            }else{

                jsonObject.put( "User" , "User");
                User user = new User( (String) jsonObjectLoginStatus.get("userid") );
                user = user.getUser();
                session.setAttribute("User" , user);
                jsonObject.put("Usertype" ,user.getUserType() );
                session.setAttribute("Email" , email);
                if( jsonObjectLoginStatus.get("emailConfirmation").equals("False") ||  jsonObjectLoginStatus.get("passwordIncorrect").equals("True") ) {

                    jsonObject.put("otpSend", "True");
                    if (jsonObjectLoginStatus.get("emailConfirmation").equals("False")){

                        session.setAttribute( "optStatus" , "Registration");

                    }else {

                        session.setAttribute( "optStatus" , "Login");

                    }

                }

            }

        }else{
            User user = new User( (String) jsonObjectLoginStatus.get("userid") );
            user = user.getUser();
            session.setAttribute("User" , user);
            session.setAttribute("Email" , email);
//            session.invalidate();
            jsonObject.put( "User" , "incorrect password" );

        }

        out.write(jsonObject.toString());
        out.close();

    }
}
