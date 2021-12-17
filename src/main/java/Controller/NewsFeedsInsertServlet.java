package Controller;

import DAO.PostDAO;
import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import Model.NewsFeeds;
import Model.Post;
import Model.Requests;
import Model.User;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewsFeedsInsertServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String fullName = firstName + " " + lastName;

        Requests requests = new Requests();

        ArrayList<String> friendsIDList = requests.getTeacherFriends(userId);
        System.out.println("In servlet : omg : "+friendsIDList);

        ArrayList<String> followersIDList = requests.getTeacherFollowers(userId);
        System.out.println("In servlet for followers omg : "+followersIDList);


        for(int i=0; i<followersIDList.size(); i++){
            System.out.println(followersIDList.get(i)+" mmm");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        NewsFeeds newsFeeds = null;

        if ( isMultipart ){

            newsFeeds = handleImageAndPostUploads.uploadNewsFeedsImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() );

        }

        for(int i=0; i<friendsIDList.size(); i++){
            System.out.println(friendsIDList.get(i)+" kkkk");
            PostDAO postDAO = new PostDAO();
            postDAO.insert(newsFeeds,friendsIDList.get(i), userId );
        }

        for(int i=0; i<followersIDList.size(); i++){
            System.out.println(friendsIDList.get(i)+" kkkk");
            PostDAO postDAO = new PostDAO();
            postDAO.insert(newsFeeds,followersIDList.get(i), userId );
        }

        JSONObject newsFeedsJson = new JSONObject( newsFeeds );

        jsonObject.put( "NewsFeedsPost" , newsFeedsJson );
        jsonObject.put("fullName",fullName);

        out.write( jsonObject.toString() );
        out.close();

    }
}
