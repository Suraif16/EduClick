package Model.HandlingImages_Multipart;

import Model.EducationalWork;
import Model.NewsFeeds;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class handleImageAndPostUploads {

    public static EducationalWork uploadEPostImages(HttpServletRequest request , String path , LocalDate localDate , LocalTime localTime , String classroomId ){

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

            return educationalWork.insertEducationalWork( imageFile , path , classroomId );


        }catch ( Exception e ){

            System.out.println( e );

        }

        return null;

    }


    public static NewsFeeds uploadNewsFeedsImages(HttpServletRequest request , String path , LocalDate localDate , LocalTime localTime ){

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

                    }

                    inputStream.close();


                }else{

                    imageFile = file;

                }

            }

            NewsFeeds newsFeeds = new NewsFeeds( message , localDate , localTime );


            return newsFeeds.insertNewsFeeds( imageFile , path );



        }catch ( Exception e ){

            System.out.println( e );

        }

        return null;

    }

}
