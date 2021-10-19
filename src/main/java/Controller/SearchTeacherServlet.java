package Controller;

import Model.Classroom;
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

public class SearchTeacherServlet extends HttpServlet {

    ArrayList<User> TeacherNameList = new ArrayList<User>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse" , "Allowed");

        String teacherName = request.getParameter("searchValue");

          Teacher teacher = new Teacher();
          teacher.searchTeacher(teacherName);

        TeacherNameList = teacher.getClassDetails(TeacherNameList);

        JSONArray jsonArray = new JSONArray( TeacherNameList );

        jsonObject.put( "TeacherNameList" , jsonArray);

        out.write(jsonObject.toString());
        out.close();
    }

}
