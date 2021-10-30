package Controller;


import Model.AdminPost;
import Model.Admin;
import Model.*;
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


public class AdminServlet extends HttpServlet {
    public String generatedUserID;
    @Override

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );
        Admin admin = new Admin( );
        admin = admin.getCount();
        jsonObject.put("counttotal" ,admin.getCountTeacher()+admin.getCountStudent());
        jsonObject.put("Teacher" ,admin.getCountTeacher());
        jsonObject.put("TeacherReg" ,admin.getTodaycountTeacher());
        jsonObject.put("Student" ,admin.getCountStudent());
        jsonObject.put("StudentReg" ,admin.getTodaycountStudent());



        System.out.println(jsonObject);
        out.write(jsonObject.toString());
        out.close();
    }


}
