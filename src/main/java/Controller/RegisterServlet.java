package Controller;



import Model.Login;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.JSONObject;

public class RegisterServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String firstname = request.getParameter("Firstname");
        String lastname = request.getParameter("Lastname");
        LocalDate dateofBirth = LocalDate.now();
        String mobileNum = request.getParameter("MobileNo");
        String country = request.getParameter("Country");
        String city = request.getParameter("City");
        LocalDate registrationDate = LocalDate.now();
        LocalTime registrationTime = LocalTime.now();
        String gender = request.getParameter("Gender");
        String userType = request.getParameter("UserType");

        String email = request.getParameter("Email");
        String password = request.getParameter("Password");
        LocalDate loginDate = LocalDate.now();
        LocalTime loginTime = LocalTime.now();

        User user = new User( firstname,lastname,dateofBirth,mobileNum,country,city,registrationTime,registrationDate,gender,userType);
        Login login = new Login( email , password , loginDate , loginTime);



        out.write(jsonObject.toString());
        out.close();





    }
}
