package Controller;

import Model.Classroom;
import Model.EducationalWork;
import Model.Post;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentEducationalPostLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );


        Classroom classroom = new Classroom();

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        String minPostId = ( String ) request.getParameter( "id" );

        /*String classroomId = (String) session.getAttribute("CurrentClassroomId");*/
        String classroomId = request.getParameter( "classroomId" );

        System.out.println("minPostId : "+minPostId);

        EducationalWork educationalWork = new EducationalWork();
        List< JSONObject > ePostList = educationalWork.selectEducationalPostMCQ( ( String ) session.getAttribute( "CurrentClassroomId" ) , minPostId, userId );

        JSONArray jsonEPostList = new JSONArray( ePostList );

        jsonObject.put( "ePosts" , jsonEPostList );

        System.out.println(classroomId);

        jsonObject.put("TeacherFullName",user.getFullName(classroom.getClassroomOwnerId(classroomId)));

        jsonObject.put("TeacherId",classroom.getClassroomOwnerId(classroomId));



        System.out.println(jsonObject);

        System.out.println("New post load student ok!!!");

        out.write( jsonObject.toString() );
        out.close();

    }
}
