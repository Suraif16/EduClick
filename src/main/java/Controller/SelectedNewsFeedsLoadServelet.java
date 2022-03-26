package Controller;

import Model.NewsFeeds;
import Model.Post;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectedNewsFeedsLoadServelet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonObject1 = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        jsonObject.put( "serverResponse" , "Allowed" );

        User user = (User) session.getAttribute("User");
        String userId = user.getUserId();
        System.out.println("user id = " + userId);

        Post post = new Post();

        JSONArray jsonArray1 = new JSONArray();

        try {
            jsonArray1 = (JSONArray) post.getLoadedNewsFeedsId(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jsonObject.put("jsonArray1",jsonArray1);


        out.write( jsonObject.toString() );
        out.close();






    }
}
