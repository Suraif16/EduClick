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


public class StudentFollowerListLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        System.out.println(userId);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        Requests requests = new Requests();

        ArrayList<String> followersList = requests.getStudentFollowers(userId);

        System.out.println("In servlet : "+followersList);

        JSONArray studentFollowersDetails = user.getStudentFollowersDetails(followersList);


        /*for(int i=0;i<studentFollowerList.size();i++){
            System.out.println(studentFollowerList.get(i).getUserId());
            System.out.println(studentFollowerList.get(i).getFirstName());
            System.out.println(studentFollowerList.get(i).getLastName());
        }*/
//        JSONArray jsonArray = new JSONArray(studentFollowerList);
        System.out.println(studentFollowersDetails);


        jsonObject.put("List",studentFollowersDetails);



        out.write(jsonObject.toString());
        out.close();
    }
}
