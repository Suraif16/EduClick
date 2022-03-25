package Controller;


import Model.Report;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminDeleteReportPostServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession( false );

        String id = request.getParameter("id");
        System.out.println("delete report value " + id);
        Report delete = new Report();
        delete.adminDelete(id);
        out.write(jsonObject.toString());
        out.close();
    }

}