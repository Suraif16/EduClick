package Controller.UserLoad;

import Model.Requests;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherProfileNameLoadServlet extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );
        User user = new User();
        String fID = request.getParameter("userId");
        System.out.println(fID);



        String fullName = user.getFullName(fID);
        System.out.println(fullName);

        jsonObject.put("FullName",fullName);
        out.write(jsonObject.toString());
        out.close();

    }

}
