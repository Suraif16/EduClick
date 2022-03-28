package Controller.UserLoad;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherLoadServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );
        jsonObject.put( "firstName" , user.getFirstName() );
        jsonObject.put( "userId" , user.getUserId() );
        jsonObject.put( "profilePicture" , user.getUserProfileImage() );
        out.write(jsonObject.toString());
        out.close();


    }

}
