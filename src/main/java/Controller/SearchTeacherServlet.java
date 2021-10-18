package Controller;

import Model.Teacher;
import Model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchTeacherServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse" , "Allowed");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("User");

        String teacherName = request.getParameter("searchValue");

          Teacher teacher = new Teacher(user);
          teacher.searchTeacher(teacherName);

        System.out.println("jjj");
        jsonObject.put("check" , "done");
        out.write(jsonObject.toString());
        out.close();
    }

}
