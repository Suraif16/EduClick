package Controller.UserLoad;

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
import java.util.ArrayList;

public class StudentEntrancePageNewsFeedLoadServlet extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        String firstName = user.getFirstName();

        String lastName = user.getLastName();

        String fullName = firstName + " " + lastName;

        Post post = new Post();

        ArrayList<String> newsFeedsIDList = post.getNewsFeedsID(userId);

        ArrayList<String> NFTeacherIDList = post.getNFTeacherID(userId);

        JSONArray TeacherNameList = user.getNFownerName(NFTeacherIDList);

        JSONArray NFIDList = post.getIDNewsFeeds(userId);

        NewsFeeds newsFeeds = new NewsFeeds();

        JSONArray NFimagesPath = newsFeeds.getImagePath(newsFeedsIDList);

        JSONArray NFDetails = newsFeeds.getNFDetails(newsFeedsIDList);

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("NewsFeedID", NFIDList);

        jsonObject.put("TeacherName", TeacherNameList);

        jsonObject.put("NewsFeedsDetails", NFDetails);

        jsonObject.put("ImagePath",NFimagesPath);

        jsonObject.put("FullName", fullName);

        jsonObject.put("serverResponse", "Allowed");

        out.write(jsonObject.toString());
        out.close();
    }



}
