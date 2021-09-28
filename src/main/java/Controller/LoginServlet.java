package Controller;

import DAO.LoginDAO;
import Model.Admin;
import Model.Login;
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
        depending on the return value we make the decisions as below*/

        if(loginStatus.equals("password incorrect")){
            System.out.println("incorrect password");
            session.invalidate();
        }else if(loginStatus.equals("")){
            /*admin*/
            System.out.println("admin");
            Admin admin = new Admin( "Admin" , email );
            session.setAttribute("Admin" , admin);
            jsonObject.put("User" , "Admin");

        }else{
            /*for users*/
            System.out.println("user");
            jsonObject.put("User" , "User");
        }
        System.out.println("farzan");

        out.write(jsonObject.toString());
        out.close();

    }
}
