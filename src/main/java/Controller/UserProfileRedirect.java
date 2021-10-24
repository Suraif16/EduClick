package Controller;

import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserProfileRedirect extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        HttpSession session = request.getSession( false );
        String userId = request.getParameter("userId");
        User user = new User( userId );
        session.setAttribute( "profileUserId" , userId );
        user.getUser();
        if ( user.getUserType().equals("Teacher") ){

            response.sendRedirect("/EduClick_war_exploded/Teacher/TeacherProfile.html");

        }else if ( user.getUserType().equals("Student") ){

            response.sendRedirect("/EduClick_war_exploded/Student/StudentProfile.html");

        }



    }

}
