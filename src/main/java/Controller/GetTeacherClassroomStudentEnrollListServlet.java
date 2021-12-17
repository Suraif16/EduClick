package Controller;

import Model.Classroom;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetTeacherClassroomStudentEnrollListServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String classroomId = ( String ) request.getParameter( "classroomId" );

        Classroom classroom = new Classroom();
        List< JSONObject > classroomEnrollList =  classroom.SelectClassroomStudentEnrollList( classroomId );

        JSONArray jsonArray = new JSONArray( classroomEnrollList );

        jsonObject.put( "classroomEnrollList" , jsonArray );

        out.write( jsonObject.toString() );
        out.close();

    }

}
