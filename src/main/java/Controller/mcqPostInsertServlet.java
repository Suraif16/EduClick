package Controller;

import Model.EducationalWork;
import Model.Mcq;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class mcqPostInsertServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ){

        HttpSession session = request.getSession( false );

        List< Mcq > mcqList = new ArrayList<>();

        EducationalWork educationalWork = new EducationalWork( "Question" , LocalDate.now() , LocalTime.now() );

        for ( int i = 0 ; i < 10 ; i++ ) {

            JSONObject jsonData = new JSONObject( request.getParameter( "mcq" + ( i + 1 ) ) );

            Mcq mcq = new Mcq( ( String ) jsonData.get( "Question") , ( String ) jsonData.get( "CorrectAnswer") , ( String ) jsonData.get( "Answer1") , ( String ) jsonData.get( "Answer2") , ( String ) jsonData.get( "Answer3") , ( String ) jsonData.get( "Answer4") );

            mcqList.add( mcq );

        }

        educationalWork.insertMCQEducationalWork( ( String ) session.getAttribute( "CurrentClassroomId" ) , mcqList );

    }

}
