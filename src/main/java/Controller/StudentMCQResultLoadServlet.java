package Controller;

import Model.Answers;
import Model.Question;
import Model.User;
import org.json.JSONObject;

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

public class StudentMCQResultLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String userId = user.getUserId();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        String postId = request.getParameter("postId");

        int result = 0;

        ArrayList<String> studentAnswerList = new ArrayList<>();
        ArrayList<String> questionIdList = new ArrayList<>();

        for(int i = 0 ; i<10 ; i++){
            String mcq = "mcq"+(i+1);
            JSONObject jsonMCQ = new JSONObject( request.getParameter(mcq) );

            System.out.println(jsonMCQ.get("questionId"));

            System.out.println(jsonMCQ.get("answerChoice"));

            questionIdList.add((String) jsonMCQ.get("questionId"));

            studentAnswerList.add((String) jsonMCQ.get("answerChoice"));

        }

        Question question =  new Question();

        ArrayList<String> correctAnswerList=question.selectCorrectAnswers(postId);

        for( int i =0;i<10;i++){

            if(correctAnswerList.get(i).equals(studentAnswerList.get(i))){

                result += 10;
            }

        }

        Answers answers = new Answers(LocalDate.now(),LocalTime.now(),result);

        String answerId = answers.enterMCQMarks();


        for(int i=0 ; i < 10 ; i++){

            answers.saveMCQAnswers(answerId,studentAnswerList.get(i),questionIdList.get(i));
        }

        answers.saveMCQAnswerPostStudentRelationship(userId,answerId,postId);

        System.out.println("Your result is : "+result);



        for(int i = 0;i<10;i++){

            System.out.println("Student answers"+(i+1)+" : "+studentAnswerList.get(i));

            System.out.println("Question ID list "+(i+1)+" : "+questionIdList.get(i));
        }

        System.out.println("Everything inserted into tables smoothly");

        jsonObject.put("Result",result);

        out.write( jsonObject.toString() );
        out.close();



    }
}
