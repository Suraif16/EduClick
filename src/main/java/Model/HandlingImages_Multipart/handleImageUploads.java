package Model.HandlingImages_Multipart;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class handleImageUploads {

    public static StringBuilder uploadImages(HttpServletRequest request , String path ){

        ServletFileUpload servletFileUpload = new ServletFileUpload( new DiskFileItemFactory() );
        int count = 1;
        StringBuilder output = new StringBuilder();

        try{

            List<FileItem> files = servletFileUpload.parseRequest( request );

            for (FileItem file : files ){

                if ( file.isFormField() ){

                    String fieldName = file.getFieldName();
                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[ inputStream.available() ];
                    inputStream.read( bytes );
                    String x = new String( bytes );
                    System.out.println( file );
                    System.out.println("data!!!");
                    System.out.println( fieldName );
                    System.out.println( "String!!!" );
                    System.out.println( x );
                    inputStream.close();


                }else{

                    System.out.println( file );
                    String fileName = file.getName();
                    /* this should be removed when the system is uploaded into a server*/
                    file.write( new File( path + "Image\\" + fileName ) );

                    ImageJPEGConverterAndCompressor.convertToJPEG( fileName , path , count + "");

                    output.append( "<img src='Image/" + count + "Compressed" + ".jpeg" +"'><br/>" );

                    count++;

                }

            }

        }catch ( Exception e ){

            System.out.println( e );

        }

        return output;

    }

}
