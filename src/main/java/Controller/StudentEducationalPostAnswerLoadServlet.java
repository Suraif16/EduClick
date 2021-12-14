package Controller;

import Model.Answers;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentEducationalPostAnswerLoadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Student Answer selection eka load unoooo");

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String ePostId = request.getParameter("ePostId");

        System.out.println("EPostID is : "+ePostId);

        System.out.println("Student ID is : "+user.getUserId());

        Answers answers = new Answers(ePostId, user.getUserId());
        String answerId = answers.getAnswerId();
        if(!answerId.equals("")){
            jsonObject.put("Answered","Yes");
            System.out.println("Yes awooo");
        }else {
            jsonObject.put("Answered","No");
            System.out.println("No awooo");
        }

        jsonObject.put("AnswerDetails",answers.getAnswerDetails(answerId));

        jsonObject.put("AnswerContent",answers.getAnswerContent(answerId));

        System.out.println(jsonObject);


        out.write(jsonObject.toString());
        out.close();
    }
}
