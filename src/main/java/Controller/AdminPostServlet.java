package Controller;


import Model.Admin;
import Model.AdminPost;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;


public class AdminPostServlet extends HttpServlet {
    public String generatedUserID;
    @Override



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        String textMsg = request.getParameter("textMsg");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println(textMsg);
        System.out.println(date);
        System.out.println(time);
        AdminPost adminpost = new AdminPost(generatedUserID,textMsg,date,time);
        adminpost.adminPost();
       /* Login loginemail = new Login(email);


        String emailStatus = loginemail.checkEmail();
        if(emailStatus.equals("Email exsist")){
            System.out.println("Enter another email");
            jsonObject.put("EmailStatus" , "InvalidEmail");
            session.invalidate();
        }*/
        /*else if(emailStatus.equals("Email doesn't exsist")){
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
            Login login = new Login( email , password , saltingKey , loginDate , loginTime, generatedUserID);
            login.insertRecord();
            user.setRegistrationDate(null);
            user.setRegistrationTime(null);
            session.setAttribute("User" , user );
            session.setAttribute("Email" , email);
            jsonObject.put("EmailStatus" , "ValidEmail");
        }*/
        out.write(jsonObject.toString());
        out.close();
    }
}
