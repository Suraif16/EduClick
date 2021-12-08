package Controller;

import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveClassroomIdServlet extends HttpServlet {

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("User");

        String id = request.getParameter("id");


        session.setAttribute("CurrentClassroomId",id);

        if(user.getUserType().equals("Student")){
            response.sendRedirect("Student/classroom.html");
        }
        else if(user.getUserType().equals("Teacher")){
            response.sendRedirect("Teacher/Classroom.html");
        }

    }

}
