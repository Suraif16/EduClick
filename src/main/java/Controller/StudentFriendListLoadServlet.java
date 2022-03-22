package Controller;

import Model.Requests;
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

public class StudentFriendListLoadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = request.getParameter("userId");

        System.out.println("Friends userID : "+userId);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        Requests requests = new Requests();

        ArrayList<String> friendList = requests.getStudentFriends(userId);
        System.out.println("In servlet : "+friendList);

        JSONArray studentFriendDetails = user.getStudentFriendsDetails(friendList);

        /*for(int i=0;i< studentFriendDetails.size();i++){
            System.out.println("Servlet"+studentFriendDetails.get(i).getUserId());
            System.out.println("Servlet"+studentFriendDetails.get(i).getFirstName());
            System.out.println("Servlet"+studentFriendDetails.get(i).getLastName());
        }*/
        System.out.println(studentFriendDetails);


        jsonObject.put("List",studentFriendDetails);

        out.write(jsonObject.toString());
        out.close();

    }
}
