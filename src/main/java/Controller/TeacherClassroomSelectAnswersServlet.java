package Controller;

import Model.Answers;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherClassroomSelectAnswersServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String ePostId = request.getParameter( "epostId" );
        Answers answers = new Answers();
        JSONArray jsonArray = new JSONArray( answers.getEpostAnswers( ePostId ) );

        jsonObject.put( "answerList" , jsonArray );

        out.write( jsonObject.toString() );
        out.close();


    }

}
