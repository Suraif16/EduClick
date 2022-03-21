package Controller.Classroom;

import Model.Classroom;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherDeleteClassroomServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String classroomId = request.getParameter( "classroomId" );

        Classroom classroom = new Classroom();
        classroom.setClassroomID( classroomId );
        classroom.deleteClassroom();

        out.write( jsonObject.toString() );
        out.close();


    }

}
