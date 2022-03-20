package Controller.Requests;

import Model.Requests;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class cancelFriendRequestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        String fromID = request.getParameter("fromId");
        String toID = request.getParameter( "toId" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse"  , "Allowed" );

        Requests requests = new Requests( fromID , toID );
        requests.cancelRequest();

        out.write( jsonObject.toString() );
        out.close();

    }



}