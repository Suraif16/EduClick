package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentNewsFeedLoadedServlet extends HttpServlet {

    public String id;

    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        System.out.println("Student Entrance Servlet reached");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();
        System.out.println("UserID is : "+user.getUserId());

        id = user.getUserId();

        jsonObject.put( "serverResponse" , "Allowed" );
        jsonObject.put( "firstName" , user.getFirstName() );

        out.write(jsonObject.toString());
        out.close();
    }
}
