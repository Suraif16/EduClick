package Controller;

import DAO.AdminPostDAO;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminPostLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("User");

        String userId = request.getParameter("userId");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("serverResponse" , "Allowed");

        AdminPostDAO adminPostDAO = new AdminPostDAO();
        JSONArray post = adminPostDAO.getAPostDetails();


        jsonObject.put("jsonArray1",post);

        out.write(jsonObject.toString());

        out.close();

    }
}