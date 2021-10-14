package Controller;

import Model.Student;
import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollRequestServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        System.out.println("EnrolRequest Reached!!");

        String ClassroomId = request.getParameter("id");

        String action = request.getParameter("action");
        System.out.println(action);
        if(action.equals("request")){
            Student student = new Student(user);
            student.requestEnroll(ClassroomId,user.getUserId());
        }else if(action.equals("delete")){
            Student student = new Student(user);
            student.deleteEnroll(ClassroomId,user.getUserId());
        }



    }
}
