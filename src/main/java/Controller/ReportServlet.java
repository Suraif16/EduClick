package Controller;

import Model.AdminPost;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReportServlet extends HttpServlet {
    public String generatedUserID;
    @Override



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );
        String textMsg = request.getParameter("textMsg");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.println(textMsg);
        System.out.println(date);
        System.out.println(time);
        AdminPost adminpost = new AdminPost(generatedUserID,textMsg,date,time);
        adminpost.adminPost();

        out.write(jsonObject.toString());
        out.close();
    }

}
