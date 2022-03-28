package Controller;

import Model.EducationalWork;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SelectEPostClassroomServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = ( User ) session.getAttribute( "User" );

        jsonObject.put( "userName" , user.getFirstName() );
        jsonObject.put( "userId" , user.getUserId() );
        jsonObject.put( "profilePicture" , user.getUserProfileImage() );

        String minPostId = request.getParameter( "id" );
        String classroomId = request.getParameter( "classroomId" );

        EducationalWork educationalWork = new EducationalWork();
        List< JSONObject > ePostList = educationalWork.selectEducationalPost( classroomId , minPostId );

        JSONArray jsonEPostList = new JSONArray( ePostList );

        jsonObject.put( "ePosts" , jsonEPostList );

        out.write( jsonObject.toString() );
        out.close();

    }

}
