package Controller;

import Model.Answers;
import Model.EducationalWork;
import Model.HandlingImages_Multipart.handleImageAndPostUploads;
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

public class EducationalPostAnswersInsertServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        System.out.println("Amo amo oi image load eka mehen awa mennoooooooo");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        System.out.println("UserID : "+user.getUserId());

        String userId = user.getUserId();

        Answers answers = null;

        System.out.println("The Epost ID to notification check : "+request);

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );


        if ( isMultipart ){

            System.out.println( "reached" );
            answers = handleImageAndPostUploads.uploadEPostAnswersImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() , userId);
            System.out.println( "reached and done");

        }

        JSONObject answersJson = new JSONObject(answers);
        System.out.println("Mama JSON object eka : "+answersJson);
        jsonObject.put("EPostAnswer",answersJson);

        out.write(jsonObject.toString());
        out.close();
    }
}
