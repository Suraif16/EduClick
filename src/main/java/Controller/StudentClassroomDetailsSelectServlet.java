package Controller;

import Model.Classroom;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentClassroomDetailsSelectServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        /*PrintWriter out = response.getWriter();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        System.out.println("I am runnijnsuhgfisyuHDJAGDIG!!!!!!");

        System.out.println("User Id of me :hdbjhgd : "+ user.getUserId());

        String classroomId =  request.getParameter("classroomId");

        *//*Classroom classroom = new Classroom();

        classroom.getStudentClassroomDetails(user.getUserId(), classroomId);*//*

        Classroom classroom = new Classroom();

        JSONObject classroomDetails = classroom.getStudentClassroomDetails(classroomId);

        System.out.println("ClassroomDetails : "+classroomDetails);

        jsonObject.put("classroomDetails",classroomDetails);*/

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        Classroom classroom = new Classroom();
        classroom.setClassroomID( request.getParameter( "classroomId" ) );

        System.out.println(classroom.getClassroomDetails());

        jsonObject.put( "classroomDetails" , classroom.getClassroomDetails() );

        out.write( jsonObject.toString() );
        out.close();



        out.write(jsonObject.toString());
        out.close();
    }
}
