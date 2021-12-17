package Controller;

import Model.Question;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentMCQResultLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        int result = 0;

        ArrayList<String> studentAnswerList = new ArrayList<>();

        String classroomId = request.getParameter("classroomId");

        String postId = request.getParameter("postId");
        for(int i = 0 ; i<10 ; i++){
            String mcq = "mcq"+(i+1);
            studentAnswerList.add(request.getParameter(mcq));
        }


        System.out.println("EPostId is : "+postId);

        Question question =  new Question();
        ArrayList<String> correctAnswerList=question.selectCorrectAnswers(postId);
        for( int i =0;i<10;i++){
            if(correctAnswerList.get(i).equals(studentAnswerList.get(i))){
                result += 10;
            }
        }
        System.out.println("Your result is : "+result);



    }
}
