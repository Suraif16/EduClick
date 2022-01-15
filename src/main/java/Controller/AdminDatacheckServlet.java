package Controller;


import Model.AdminDatacheck;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminDatacheckServlet extends HttpServlet {
    public String generatedUserID;
    @Override

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );
        AdminDatacheck user = new AdminDatacheck();
        user = user.getData();
        jsonObject.put("getLastName" ,user.getLastName());




        System.out.println(jsonObject);
        out.write(jsonObject.toString());
        out.close();
    }


}