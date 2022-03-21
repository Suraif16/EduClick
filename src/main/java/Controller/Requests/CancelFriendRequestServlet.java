package Controller.Requests;

import Model.Requests;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class CancelFriendRequestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        HttpSession session = request.getSession( false );
        User user = ( User ) session.getAttribute( "User" );

        String fromID = user.getUserId();
        String toID = request.getParameter( "toId" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse"  , "Allowed" );

        Requests requests = new Requests( fromID , toID );
        requests.cancelRequest();

        out.write( jsonObject.toString() );
        out.close();

    }

}
