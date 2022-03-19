package Controller;

import DAO.ShareDAO;
import Model.NewsFeeds;
import Model.Requests;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//insert data to share table

public class SharedNewsFeedsServletInsert extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();
        System.out.println("user id = ***** " + userId);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = firstName + " " + lastName;

        String SharedPostID = request.getParameter("id");
        System.out.println("Shared NewsfeedID =  " + SharedPostID);

        ShareDAO shareDAO = new ShareDAO();


        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalTime time = LocalTime.ofSecondOfDay(10000);

        Requests requests = new Requests();

        ArrayList<String> followersList = requests.getTeacherFollowers(userId);
      //  System.out.println(" followers  : "+followersList);

        ArrayList<String> friendList = requests.getTeacherFriends(userId);
     //   System.out.println(" friends : "+friendList);


        for(int i=0; i < followersList.size(); i++){
            shareDAO.insert(time, date, userId, followersList.get(i), SharedPostID);
        }
        for(int i=0; i < friendList.size(); i++){
            shareDAO.insert(time, date, userId, friendList.get(i), SharedPostID);
        }
        NewsFeeds newsFeeds = new NewsFeeds();

//*****************************


        JSONArray jsonArray = new JSONArray();

        try {
            jsonArray =newsFeeds.getNewsFeedsReceiver(SharedPostID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(jsonArray+"huuuu");

        jsonObject = newsFeeds.getNewsFeedsImageDetails(SharedPostID);
        System.out.println(jsonObject + "imageeeeeeeeeeee");

        jsonArray = newsFeeds.getNFDetails(SharedPostID);
        System.out.println(jsonArray + "awoooooooooo");

        for(int i =0; i<jsonArray.length(); i++){
            if(jsonArray.get(i) == userId){
                System.out.println("yeeeeeeeeeee");
            }
        }


        out.write( jsonObject.toString() );
        out.close();

    }
}