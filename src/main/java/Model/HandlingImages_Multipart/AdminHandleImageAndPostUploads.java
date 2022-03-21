package Model.HandlingImages_Multipart;

import Model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AdminHandleImageAndPostUploads {

    public static AdminWork uploadEPostImages(HttpServletRequest request, String path, LocalDate localDate, LocalTime localTime ) {
        String message = "";
        ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());

        try {
            List<FileItem> files = servletFileUpload.parseRequest(request);
            FileItem imageFile = null;
            for (FileItem file : files) {
                if (file.isFormField()) {
                    InputStream inputStream = file.getInputStream();
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    if ( file.getFieldName().equals("message") ) {
                        message = new String(bytes);
                    }
                    inputStream.close();
                } else {
                    imageFile = file;
                }
            }

            AdminWork adminWork = new AdminWork( message ,  localDate , localTime );
            return adminWork.insertAdminPostWork( imageFile , path );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}