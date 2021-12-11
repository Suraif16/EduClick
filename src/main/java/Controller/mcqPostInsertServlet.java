package Controller;

import Model.EducationalWork;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;

public class mcqPostInsertServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ){

        HttpSession session = request.getSession( false );

        EducationalWork educationalWork = new EducationalWork( "Question" , LocalDate.now() , LocalTime.now() );
        String mcqPostId = educationalWork.insertMCQ( ( String ) session.getAttribute( "CurrentClassroomId" ) );
        if ( mcqPostId != null ){

            for ( int i = 0 ; i < 10 ; i++ ) {

                JSONObject jsonData = new JSONObject( request.getParameter( "mcq" + ( i + 1 ) ) );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "Question") );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "Answer1") );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "Answer2") );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "Answer3") );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "Answer4") );
                System.out.println( "jsonMcq" + ( i + 1 ) + " - - > " + jsonData.get( "CorrectAnswer") );

            }

        }



    }

}
