package Controller;

import DAO.AdminPostDAO;
import Model.AdminPost;
import Model.HandlingImages_Multipart.handleImageAndPostUploads;
import Model.AdminWork;
import Model.User;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdminPostServlet extends HttpServlet {
    public String generatedUserID;
    @Override

    /*public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );
        String textMsg = request.getParameter("textMsg");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println(textMsg);
        System.out.println(date);
        System.out.println(time);
        AdminPost adminpost = new AdminPost(generatedUserID,textMsg,date,time);
        adminpost.adminPost();

        out.write(jsonObject.toString());
        out.close();
    }*/
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );
        HttpSession session = request.getSession( false );
        //User user = (User) session.getAttribute("User");



        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        boolean isMultipart = ServletFileUpload.isMultipartContent( request );
        AdminWork adminWork = null;
        if ( isMultipart ){
            adminWork = handleImageAndPostUploads.uploadEPostImages( request , getServletContext().getRealPath( "" ) , LocalDate.now() , LocalTime.now() );
        }

        JSONObject adminWorkJson = new JSONObject( adminWork );
        jsonObject.put( "EPost" , adminWorkJson );
        out.write( jsonObject.toString() );
        out.close();

    }


}
