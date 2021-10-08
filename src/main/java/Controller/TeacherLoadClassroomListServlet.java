package Controller;

import Model.Classroom;
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

public class TeacherLoadClassroomListServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        System.out.println(user.getUserId() + " bigbang");

        Classroom classroom = new Classroom( user.getUserId() );

        List<Classroom> classroomList = classroom.getListOfCLassRooms();

        if( classroomList == null){

            jsonObject.put( "classroomList" , "No classrooms");
            System.out.println("no classrooms");

        }else {

            JSONArray jsonArray = new JSONArray( classroomList );
            jsonObject.put( "classroomList" , jsonArray);
            System.out.println("have classrooms");

        }




        /*JSONObject userJsonObject = new JSONObject(user);

        jsonObject.put("UserObject" , userJsonObject);
        System.out.println(userJsonObject);
        System.out.println(jsonObject);

        List<User> list = new ArrayList<>();
        list.add(user);

        JSONArray jsonArray = new JSONArray(list);
        jsonObject.put("ArrayValues" , jsonArray);*/

        out.write(jsonObject.toString());
        out.close();


    }

}
