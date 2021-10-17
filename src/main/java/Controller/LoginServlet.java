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
        String loginStatus = login.checkPassword();/*here we check for the password
        depending on the return value we make the decisions as below
        if loginStatus has an empty string then it is an admin ,
        if loginStatus has an id then it is an user*/

        if(loginStatus.equals("password incorrect")){
            jsonObject.put("User" , "incorrect password");
            session.invalidate();
        }else if(loginStatus.equals("User does not exist")){
            jsonObject.put("User" , "User does not exist");
            session.invalidate();
        }
        else if(loginStatus.equals("")){
            /*admin*/
            Admin admin = new Admin( "Admin" , email );
            session.setAttribute("Admin" , admin);
            jsonObject.put("User" , "Admin");

        }else{
            /*for users*/
            /*loginStatus = UserID*/
            jsonObject.put("User" , "User");
            User user = new User ( loginStatus );
            user = user.getUser();
            session.setAttribute("User" , user);
            jsonObject.put("Usertype" ,user.getUserType() );
            session.setAttribute("Email" , email);

        }

        out.write(jsonObject.toString());
        out.close();

    }
}
