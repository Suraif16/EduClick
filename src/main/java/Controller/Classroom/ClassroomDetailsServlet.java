package Controller.Classroom;

import Model.Classroom;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassroomDetailsServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        Classroom classroom = new Classroom();
        classroom.setClassroomID( request.getParameter( "classroomId" ) );

        jsonObject.put( "classroomDetails" , classroom.getClassroomDetails() );

        out.write( jsonObject.toString() );
        out.close();

    }

}
