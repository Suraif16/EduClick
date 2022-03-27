package Controller;

import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import Model.User;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveEditProfileDetailsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        HttpSession session = request.getSession( false );
        User user = ( User ) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );

        if ( isMultipart ){

            handleImageAndPostUploads.uploadProfileImage( request , getServletContext().getRealPath( "" ) , user.getUserId() );

        }

    }

}
