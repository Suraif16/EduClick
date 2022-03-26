package Controller;

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
        System.out.println(id+"servlet");
        System.out.println(type+"servlet");
        Report report1 = new Report();
        report1 = report1.selectdao();

        if (id.equals(report1.getUserID()) || id.equals(report1.getAnswerID()) || id.equals(report1.getNF_postID()) || id.equals(report1.getEpostID())){
            report1.updatedao(report1.getReportID(),report1.getCount()+1);
        }else {
            Report report = new Report();
            report.reportinsert(id,type);
        }

        out.write(jsonObject.toString());
        out.close();
    }

}
