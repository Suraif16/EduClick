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

public class StudentEnableOrDisableCheckServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        String classroomId = (String)session.getAttribute("CurrentClassroomId");

        System.out.println("Enable check : "+userId);
        System.out.println("Enable check : "+classroomId);

        Classroom classroom = new Classroom();
        classroom.setClassroomID(classroomId);
        classroom.setUserId(userId);
        String status = classroom.checkEnableOrDisable();
        jsonObject.put("Status",status);

        out.write(jsonObject.toString());
        out.close();

    }
}
