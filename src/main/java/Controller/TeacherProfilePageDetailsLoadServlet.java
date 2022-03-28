package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherProfilePageDetailsLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        JSONObject teacherDetails = user.getTeacherDetails(userId);

        if(request.getParameter("userId").equals(userId)){
            jsonObject.put("userStatus","currentUser");
        }else{
            jsonObject.put("userStatus","otherUser");
        }

        jsonObject.put("teacherDetails",teacherDetails);

        System.out.println("kajshgjhasdhgdkafkas ashjdgas dasjhgdas gdkajsdg ka djashg");

        out.write(jsonObject.toString());
        out.close();

    }

}
