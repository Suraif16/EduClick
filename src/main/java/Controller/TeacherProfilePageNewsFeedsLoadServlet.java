package Controller;

import DAO.NewsFeedsImageDAO;
import Model.NewsFeeds;
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
import java.sql.SQLException;

public class TeacherProfilePageNewsFeedsLoadServlet extends HttpServlet  {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

       /* User user = (User) session.getAttribute("User");

        String userId = user.getUserId();*/

        User user = new User();

        String userId = request.getParameter("userId");
      //  System.out.println(userId);

        User profileUser = new User( userId );
        profileUser.getUser();

        String fullName = user.getFullName(userId);
      //  System.out.println(fullName);

        Post post = new Post();
        JSONArray jsonArray1 = new JSONArray();

        try {
            jsonArray1 = (JSONArray) post.getProfilePageLoadedNewsFeedsId(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        jsonObject.put("fullName",fullName);
        jsonObject.put("jsonArray1",jsonArray1);


        out.write( jsonObject.toString() );
        out.close();


    }
    }
