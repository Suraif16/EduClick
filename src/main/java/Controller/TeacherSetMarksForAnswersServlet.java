package Controller;

import Model.Answers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherSetMarksForAnswersServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        String answerId = request.getParameter( "answerId" );
        String marks = request.getParameter( "marks" );

        Answers answers = new Answers();
        answers.setMarksForAnswers( answerId , marks );
    }

}
