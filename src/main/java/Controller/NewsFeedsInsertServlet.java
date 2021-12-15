package Controller;

import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import Model.NewsFeeds;
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

public class NewsFeedsInsertServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        HttpSession session = request.getSession( false );
        System.out.println("helooo");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        NewsFeeds newsFeeds = null;

        if ( isMultipart ){

            System.out.println( "reached **********" );
            newsFeeds = handleImageAndPostUploads.uploadNewsFeedsImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() );
            System.out.println( "reached and done *********");

        }

        JSONObject newsFeedsJson = new JSONObject( newsFeeds );

        jsonObject.put( "NewsFeedsPost" , newsFeedsJson );

        out.write( jsonObject.toString() );
        out.close();

    }
}
