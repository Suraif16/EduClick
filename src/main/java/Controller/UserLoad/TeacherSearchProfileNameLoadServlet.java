package Controller.UserLoad;

import Model.Post;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class TeacherSearchProfileNameLoadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("TeacherProName load successfully loaded!!!!");

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        String userId = request.getParameter("userId");

        System.out.println("The ijhksdfuikdsfhidsf is : "+userId);

        User user = new User();

        String fullName=user.getFirstName(userId);

        System.out.println("In Servlet = "+fullName);

        jsonObject.put("FullName",fullName);

        Post post = new Post();
        JSONArray jsonArray1 = new JSONArray();

        try {
            jsonArray1 = (JSONArray) post.getProfilePageLoadedNewsFeedsId(userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        jsonObject.put("fullName",fullName);
        jsonObject.put("jsonArray1",jsonArray1);

        out.write(jsonObject.toString());

        out.close();
    }

}
