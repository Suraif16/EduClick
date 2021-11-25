package Controller.UserProfile;

import Model.Requests;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TeacherEntrancePageNewsFeedLoadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("hhhh");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        System.out.println(userId+" **************");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        Requests requests = new Requests();

        ArrayList<String> friendList = requests.getTeacherFriends(userId);
        System.out.println("In servlet : "+friendList);



        out.close();
    }


}
