package Controller.UserLoad;

import Model.Post;
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


    public class TeacherEntrancePageNewsFeedsLoadServlet extends HttpServlet {

        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            HttpSession session = request.getSession( false );

            User user = (User) session.getAttribute("User");

            String userId = user.getUserId();

            System.out.println(userId+" **************");

            String firstName = user.getFirstName();
            System.out.println(firstName);

            String lastName = user.getLastName();
            System.out.println(lastName);

            String fullName = firstName + " " + lastName;
            System.out.println(fullName);

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("FullName",fullName);

            jsonObject.put("serverResponse" , "Allowed");

            Post post = new Post();
            ArrayList<String> newsFeedsIDList = post.getNewsFeedsID(userId);
            System.out.println("In servlet newsfeeds ID : "+newsFeedsIDList);
            ArrayList<String> NFTeacherIDList = post.getNFTeacherID(userId);
            /*JSONArray TeacherDetails = user.getTeacherFriendsDetails(newsFeedsTeacherIDList);
            System.out.println(TeacherDetails);

            jsonObject.put("List",TeacherDetails);*/

            out.write(jsonObject.toString());
            out.close();
        }


    }






