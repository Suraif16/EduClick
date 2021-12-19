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

public class StudentMCQResultLoadOnClickServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        String postId = request.getParameter("postId");

        System.out.println("User ID is : "+user.getUserId());

        System.out.println("Post ID is : "+postId);

        Answers answers = new Answers(postId, user.getUserId());

        System.out.println("Answer ID is : "+answers.getAnswerId());

        String MCQmarks = answers.selectMarksForMCQ(answers.getAnswerId());

        System.out.println("MCQ mark is : "+MCQmarks);


        jsonObject.put("MCQResult",MCQmarks);

        out.write(jsonObject.toString());

        out.close();

    }

}
