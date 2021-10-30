package Controller.Requests;

import Model.Student;
import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class followTeacher extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute( "User" );

        String T_UserId = request.getParameter(  "teacherId" );

        Student student = new Student( user );
        student.followTeacher( T_UserId );

    }

}
