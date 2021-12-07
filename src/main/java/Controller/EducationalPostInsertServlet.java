package Controller;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EducationalPostInsertServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );

        if ( isMultipart ){

            System.out.println( "reached" );

        }

    }

}
