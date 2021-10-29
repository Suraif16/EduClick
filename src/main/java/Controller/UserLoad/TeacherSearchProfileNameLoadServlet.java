package Controller.UserLoad;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherSearchProfileNameLoadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("TeacherProName load successfully loaded!!!!");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );
        HttpSession session = request.getSession( false );
        String userId = (String) session.getAttribute( "profileUserId");
        User user = new User();
        String fullName=user.getFullName(userId);
        System.out.println("In Servlet = "+fullName);
        jsonObject.put("FullName",fullName);
        out.write(jsonObject.toString());
        out.close();
    }

}
