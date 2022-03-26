package Controller;


import Model.AdminDatacheck;
import Model.AdminWork;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AdminDataDeleteServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession( false );

        String id = request.getParameter("id");
        System.out.println("delete value " + id);
        AdminWork delete = new AdminWork();
        delete.adminDelete(id);
        out.write(jsonObject.toString());
        out.close();
    }

}