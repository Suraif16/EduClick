
package Controller.ClassroomList;

import Model.Classroom;
import Model.Student;
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

public class StudentNewsFeedLoadedServlet extends HttpServlet {

    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<Classroom> classDetails = new ArrayList<Classroom>();

    public String id;

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println("Student Entrance Servlet reached");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        Student student = new Student(user);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");


        //System.out.println("UserID is : "+user.getUserId());



        id = user.getUserId();
        arrayList = student.checkEnroll(id);
        System.out.println(arrayList);

        Classroom classroom = new Classroom();

        classDetails = classroom.getClassDetails(arrayList);

        JSONArray jsonArray = new JSONArray( classDetails );

        jsonObject.put( "classroomList" , jsonArray);


        out.write(jsonObject.toString());
        out.close();
    }
}
