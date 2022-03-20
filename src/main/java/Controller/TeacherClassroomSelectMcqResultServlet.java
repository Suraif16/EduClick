package Controller;

import Model.Post;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherClassroomSelectMcqResultServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String mcqPostId = request.getParameter("epostMcqId" );
        Post mcqPost = new Post( mcqPostId );

        JSONArray jsonArray = mcqPost.getMcqResults();
        jsonObject.put( "mcqResultList" , jsonArray );

        out.write( jsonObject.toString() );
        out.close();

    }

}
