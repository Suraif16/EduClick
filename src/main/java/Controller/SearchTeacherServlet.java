package Controller;

<<<<<<< HEAD
=======
import Model.Requests;
>>>>>>> main
import Model.Teacher;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchTeacherServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();
<<<<<<< HEAD

        System.out.println("Servlet reached");

=======
>>>>>>> main
        jsonObject.put("serverResponse" , "Allowed");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");

        List<String> teacherList = new ArrayList<>();

<<<<<<< HEAD
        String teacherName = request.getParameter("value");

        System.out.println(teacherName + " ACCESS SERVLET");

        User teacher = new User();
        teacher.searchTeacher(teacherName);
=======
        String teacherName = request.getParameter("searchValue");

       // Teacher teacherName = new Teacher(user);
        //teacher.searchTeacher();

        System.out.println(teacherName);
>>>>>>> main

        JSONArray jsonArray = new JSONArray( teacherList );

        jsonObject.put("teacherList" , jsonArray);

        out.write(jsonObject.toString());
        out.close();
    }

}