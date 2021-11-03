package Controller.UserProfile;

import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherFriendsListLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        System.out.println(userId);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        JSONArray teacherFriendsList = user.getTeacherFriendsList(userId);
        System.out.println(teacherFriendsList+"*****************");

        jsonObject.put("List",teacherFriendsList);

        out.write(jsonObject.toString());
        out.close();

    }

}
