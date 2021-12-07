package Model.HandlingImages_Multipart;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class handleImageAndPostUploads {

    public static void uploadEPostImages( HttpServletRequest request , String path ){

        String type = "";
        String message = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );
        int count = 1;
        StringBuilder output = new StringBuilder();

        try{

            List<FileItem> files = servletFileUpload.parseRequest( request );

            List<FileItem> imageFiles = new ArrayList<>();

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

                    /*System.out.println( file );
                    String fileName = file.getName();
                    *//* this should be removed when the system is uploaded into a server*//*
                    file.write( new File( path + "Image\\" + fileName ) );

                    ImageJPEGConverterAndCompressor.convertToJPEG( fileName , path , count + "");

                    output.append( "<img src='Image/" + count + "Compressed" + ".jpeg" +"'><br/>" );

                    count++;*/

                    imageFiles.add( file );


                }

            }

            System.out.println( message );
            System.out.println( message.equals("") );
            System.out.println( type );
            System.out.println( imageFiles );

        }catch ( Exception e ){

            System.out.println( e );

        }

//        return output;

    }

}
