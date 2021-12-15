package Controller;

import Model.EducationalWork;
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

        String minPostId = ( String ) request.getParameter( "id" );

        EducationalWork educationalWork = new EducationalWork();
        List< JSONObject > ePostList = educationalWork.selectEducationalPost( ( String ) session.getAttribute( "CurrentClassroomId" ) , minPostId );

        JSONArray jsonEPostList = new JSONArray( ePostList );

        jsonObject.put( "ePosts" , jsonEPostList );

        out.write( jsonObject.toString() );
        out.close();

    }

}
