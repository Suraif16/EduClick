package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class BellIconUpdateServlet extends HttpServlet {
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = ( User ) session.getAttribute( "User" );

        String userId = user.getUserId();

        user.updateBellIcon(userId);

        out.write( jsonObject.toString() );
        out.close();
    }
}
