package Controller;

import Model.AdminPost;
import Model.Report;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportServlet extends HttpServlet {
    public String generatedUserID;
    @Override

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        String id = request.getParameter("id");
        String type = request.getParameter("type");
        System.out.println(id);
        System.out.println(type);

        Report report = new Report(id,type);
        report.report();
        out.write(jsonObject.toString());
        out.close();
    }

}
