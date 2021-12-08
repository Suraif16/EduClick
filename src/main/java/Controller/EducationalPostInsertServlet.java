package Controller;

import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;

public class EducationalPostInsertServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        String classroomId = "1";

        if ( isMultipart ){

            System.out.println( "reached" );
            handleImageAndPostUploads.uploadEPostImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() , classroomId );
            System.out.println( "reached and done");

        }

    }

}
