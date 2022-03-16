package Controller;

import DAO.LikesDAO;
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

public class LikeNewsFeedsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse", "Allowed");

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();
      //  System.out.println("user id = " + userId);

        String likedPostID = request.getParameter("id");
     //   System.out.println("Liked NewsfeedID =" + likedPostID);

        LikesDAO likesDAO = new LikesDAO();
        likesDAO.insert(userId , likedPostID);
        JSONArray LikeCount = likesDAO.count(likedPostID);

        jsonObject.put("likeCount",LikeCount);

        out.write( jsonObject.toString() );
        out.close();
    }
}