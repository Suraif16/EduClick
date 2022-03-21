package Controller.Requests;

import Model.Requests;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AcceptFriendRequestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String fromId = request.getParameter("fromId" );
        String toId = request.getParameter( "toId" );

        Requests requests = new Requests( fromId , toId );
        requests.acceptFriendRequest();

        out.write( jsonObject.toString() );
        out.close();

    }

}
