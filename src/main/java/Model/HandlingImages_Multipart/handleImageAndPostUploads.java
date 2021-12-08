package Model.HandlingImages_Multipart;

import Model.EducationalWork;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class handleImageAndPostUploads {

    public static void uploadEPostImages(HttpServletRequest request , String path , LocalDate localDate , LocalTime localTime , String classroomId ){

        String type = "";
        String message = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );


        try{

            List<FileItem> files = servletFileUpload.parseRequest( request );

            FileItem imageFile = null;

            for (FileItem file : files ){

                if ( file.isFormField() ){

                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[ inputStream.available() ];
                    inputStream.read( bytes );

                    if ( file.getFieldName().equals( "message" ) ){

                        message = new String( bytes );

                    }else if ( file.getFieldName().equals( "type" ) ){

                        type = new String( bytes );

                    }

                    inputStream.close();


                }else{

                    imageFile = file;

                }

            }

            EducationalWork educationalWork = new EducationalWork( message , type , localDate , localTime );

            educationalWork.insertEducationalWork( imageFile , path , classroomId );


        }catch ( Exception e ){

            System.out.println( e );

        }

//        return output;

    }

}
