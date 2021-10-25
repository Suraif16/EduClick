package Controller.ClassroomList;

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

public class TeacherClassroomListLoad extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession( false );
        String userId = (String) session.getAttribute( "profileUserId");
        User user = new User();
        JSONObject jsonObject = new JSONObject();

        List<Classroom> classroomList = user.TeacherClassroomList(userId);

        for(int i=0;i< classroomList.size();i++){
            System.out.println(classroomList.get(i).getClassroomID());
            System.out.println(classroomList.get(i).getClassroomName());
            System.out.println(classroomList.get(i).getYear());
            System.out.println(classroomList.get(i).getGrade());
            System.out.println(classroomList.get(i).getSubject());
        }
        JSONArray jsonArray = new JSONArray( classroomList );
        jsonObject.put( "classroomList" , jsonArray);

        out.write(jsonObject.toString());
        out.close();


    }
}
