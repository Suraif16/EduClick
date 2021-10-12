package Controller;

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

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();

        String OPTUserValue = request.getParameter( "OPTUserValue" );

        HttpSession session = request.getSession( false );

        String OTPValue = (String) session.getAttribute( "OPT Code" );

        User user = (User) session.getAttribute( "User" );

        if(OPTUserValue.equals(OTPValue)){

            jsonObject.put( "OTPStatus" , "valid" );

            if( user.getUserType().equals("Teacher") ){

                jsonObject.put("UserType" , "Teacher");

            }else if (user.getUserType().equals("Student")){

                jsonObject.put("UserType" , "Student");

            }


        }else{

            jsonObject.put( "OTPStatus" , "invalid" );

        }

        out.write(jsonObject.toString());
        out.close();

    }

}
