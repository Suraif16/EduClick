package Controller;


import DAO.RegisterDAO;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
//import org.json.JSONObject;

public class RegisterServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        //JSONObject jsonObject = new JSONObject();

        String firstname = request.getParameter("Firstname");
        String lastname = request.getParameter("Lastname");
        LocalDate dateofBirth = LocalDate.now();
        String mobileNo = request.getParameter("MobileNo");
        String country = request.getParameter("Country");
        String city = request.getParameter("City");
        LocalDate registrationDate = LocalDate.now();
        LocalTime registrationTime = LocalTime.now();
        String gender = request.getParameter("Gender");

        User user = new User();

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setDateOfBirth(dateofBirth);
        user.setMobileNumber(mobileNo);
        user.setCountry(country);
        user.setCity(city);
        user.setRegistrationDate(registrationDate);
        user.setRegistrationTime(registrationTime);
        user.setGender(gender);

        String userRegistered = RegisterDAO.registerUser(user);

        if(userRegistered.equals("SUCCESS")){
            request.getRequestDispatcher("/Home.html").forward(request,response);
        }
        else{
            request.setAttribute("errMessage",userRegistered);
            request.getRequestDispatcher("/RegisterNew.html").forward(request,response);
        }

    }
}
