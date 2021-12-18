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

        int result = 0;

        ArrayList<String> studentAnswerList = new ArrayList<>();

        String classroomId = request.getParameter("classroomId");

        String postId = request.getParameter("postId");
        for(int i = 0 ; i<10 ; i++){
            String mcq = "mcq"+(i+1);
            JSONObject jsonMCQ = new JSONObject( request.getParameter(mcq) );
            System.out.println(jsonMCQ.get("questionId"));
            System.out.println(jsonMCQ.get("answerChoice"));

//            studentAnswerList.add(request.getParameter(mcq));
        }
        for(int i = 0 ; i<10 ; i++){
            System.out.println(studentAnswerList.get(i));
        }

//        JSONObject jsonmcq = new JSONObject( request.getParameter(mcq) );

        System.out.println("EPostId is : "+postId);

        Question question =  new Question();
        ArrayList<String> correctAnswerList=question.selectCorrectAnswers(postId);
        for( int i =0;i<10;i++){
            if(correctAnswerList.get(i).equals(studentAnswerList.get(i))){
                result += 10;
            }
        }
        System.out.println("Your result is : "+result);

        /*Answers answers = new Answers(LocalDate.now(),LocalTime.now(),result);
        String answerId = answers.enterMCQMarks();
        System.out.println("AnswerID is : "+answerId);
        answers.saveMCQAnswers(answerId,studentAnswerList,postId);
        answers.saveMCQAnswerPostStudentRelationship(userId,answerId,postId);*/


        System.out.println("Everything inserted into tables smoothly");

        jsonObject.put("Result",result);

        out.write( jsonObject.toString() );
        out.close();



    }
}
