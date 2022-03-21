package Controller;

import Model.AdminPost;
import org.json.JSONArray;
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
import java.util.ArrayList;

public class AdminPost1Servlet extends HttpServlet {
    public String generatedUserID;
    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        //String userId = adminPost.getSysPostID();
        //ArrayList<String> APostIDList = adminPost.getSysPostID(userId);

        AdminPost adminPost = new AdminPost();
        JSONArray APDetails = adminPost.getAPostDetails();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("AdminPostDetails" ,APDetails);

        //jsonObject.put("AdminPostDetails" ,APostIDList);

        jsonObject.put("serverResponse" , "Allowed");

        out.write(jsonObject.toString());
        out.close();
    }

}
