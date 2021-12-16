package Controller.Requests;

import Model.Requests;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AcceptEnrollRequestTeacherServlet extends HttpServlet{

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        String fromId = request.getParameter( "fromId" );
        String toId = request.getParameter( "toId" );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse"  , "Allowed" );

        /* toId is the classroom id here*/

        Requests requests = new Requests( fromId , toId );
        boolean enrollStatus = requests.confirmEnrollRequest();
        jsonObject.put( "enrollStatus" , enrollStatus );

        out.write( jsonObject.toString() );
        out.close();

    }

}
