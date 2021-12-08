package Controller;

import Model.EducationalWork;
import Model.HandlingImages_Multipart.handleImageAndPostUploads;
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

public class EducationalPostInsertServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        HttpSession session = request.getSession( false );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        String classroomId = ( String ) session.getAttribute( "CurrentClassroomId" );
        EducationalWork educationalWork = null;

        if ( isMultipart ){

            System.out.println( "reached" );
            educationalWork = handleImageAndPostUploads.uploadEPostImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() , classroomId );
            System.out.println( "reached and done");

        }

        JSONObject educationalWorkJson = new JSONObject( educationalWork );

        jsonObject.put( "EPost" , educationalWorkJson );
        out.write( jsonObject.toString() );
        out.close();

    }

}
