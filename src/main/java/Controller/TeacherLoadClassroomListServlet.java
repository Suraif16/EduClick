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

        Classroom classroom = new Classroom( user.getUserId() );

        List<Classroom> classroomList = classroom.getListOfCLassRooms();

        JSONArray jsonArray = new JSONArray( classroomList );
        jsonObject.put( "classroomList" , jsonArray);

        out.write(jsonObject.toString());
        out.close();


    }

}
