package Controller;

import Model.Classroom;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassroomEnableDisableStudents extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        String userId = request.getParameter( "userId" );
        String classroomId = request.getParameter( "classroomId" );
        String status = request.getParameter( "status" );

        Classroom classroom = new Classroom();
        classroom.updateEnableDisableStatus( userId , classroomId , status );

    }

}
