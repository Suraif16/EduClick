package Controller;

import Model.Admin;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ChartServlet extends HttpServlet {
    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        Admin chart = new Admin();
        JSONArray chartDetails = chart.getChartDetails();
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("AdminPostDetails" ,chartDetails);
        jsonObject.put("serverResponse" , "Allowed");
        out.write(jsonObject.toString());
        out.close();
    }
}
