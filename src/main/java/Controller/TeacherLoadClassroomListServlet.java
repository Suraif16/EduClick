package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherLoadClassroomListServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        JSONObject userJsonObject = new JSONObject(user);

        jsonObject.put("UserObject" , userJsonObject);
        System.out.println(userJsonObject);
        System.out.println(jsonObject);

        out.write(jsonObject.toString());
        out.close();


    }

}
