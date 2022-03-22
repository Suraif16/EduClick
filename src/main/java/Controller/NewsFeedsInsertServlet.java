package Controller;

import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import Model.NewsFeeds;
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

import static java.time.LocalDate.now;

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

        NewsFeeds newsfeeds = new NewsFeeds();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        NewsFeeds newsFeeds = null;

        if ( isMultipart ){

            newsFeeds = handleImageAndPostUploads.uploadNewsFeedsImages( request , getServletContext().getRealPath( "" ) , LocalDate.now(), LocalTime.now() );
            //  System.out.println(newsFeeds.getImageStatus() + "huu huu huu ");

        }

        if ( newsFeeds != null ){

            Thread loadNewsFeeds = NewsFeeds.loadNewsFeeds( newsFeeds , userId );
            loadNewsFeeds.start();

        }
        String postId = newsFeeds.getPostID();
        System.out.println(postId + " = news feeds id");

        String postedTime = newsfeeds.getPostedTime(postId);

        JSONObject newsFeedsJson = new JSONObject( newsFeeds );

        jsonObject.put( "NewsFeedsPost" , newsFeedsJson );
        jsonObject.put("fullName",fullName);
        jsonObject.put("Time",postedTime);


        out.write( jsonObject.toString() );
        out.close();

    }
}