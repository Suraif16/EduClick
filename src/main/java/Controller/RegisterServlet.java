package Controller;



import Model.Login;
import Model.Student;
import Model.Teacher;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import org.json.JSONObject;

public class RegisterServlet extends HttpServlet {
    public String generatedUserID;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        LocalDate dateofBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String mobileNum = request.getParameter("newNumber");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        LocalDate registrationDate = LocalDate.now();
        LocalTime registrationTime = LocalTime.now();
        String gender = request.getParameter("genderSelect");
        String userType = request.getParameter("userTypeSelect");

        String email = request.getParameter("email");
        String password = request.getParameter("Password");
        LocalDate loginDate = LocalDate.now();
        LocalTime loginTime = LocalTime.now();

        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(dateofBirth);
        System.out.println(mobileNum);
        System.out.println(country);
        System.out.println(city);
        System.out.println(registrationDate);
        System.out.println(registrationTime);
        System.out.println(gender);
        System.out.println(userType);
        System.out.println(email);
        System.out.println(password);
        System.out.println(loginDate);
        System.out.println(loginTime);


        Login loginemail = new Login(email);


       String emailStatus = loginemail.checkEmail();
       if(emailStatus.equals("Email exsist")){
           System.out.println("Enter another email");
           jsonObject.put("EmailStatus" , "InvalidEmail");
           session.invalidate();
       }
       else if(emailStatus.equals("Email doesn't exsist")){
           User user = new User( firstname,lastname,dateofBirth,mobileNum,country,city,registrationTime,registrationDate,gender,userType);
           user.userRegistered();

           if(userType.equals("Student")){
               System.out.println("I am a Student!!");
               Student student =  new Student(user);
               student.enterStudent();
           }
           else if(userType.equals("Teacher")){
               System.out.println("I am a Teacher!!");
               Teacher teacher =  new Teacher(user);
               teacher.enterTeacher();
           }

           generatedUserID = user.getUserId();
           Login login = new Login( email , password , loginDate , loginTime, generatedUserID);
           login.insertRecord();
           jsonObject.put("EmailStatus" , "ValidEmail");
           jsonObject.put("Reg","Done");

       }






        out.write(jsonObject.toString());
        out.close();





    }
}
