package Controller;

import Model.Report;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportSelectServlet extends HttpServlet {
    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        Report adminPost = new Report();
        JSONArray reportDetails = adminPost.ReportDisplayDAO();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ReportPostDetails" ,reportDetails);

        jsonObject.put("serverResponse" , "Allowed");
        out.write(jsonObject.toString());
        out.close();
    }
}
