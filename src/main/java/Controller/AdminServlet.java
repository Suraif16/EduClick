package Controller;

import DAO.UserDAO;
import Model.Admin;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminServlet extends HttpServlet {

    @Override

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        JSONObject jsonObject = new JSONObject();

        /*for users*/
        /*loginStatus = UserID*/
       /* UserDAO userDAO = new UserDAO();
        int count= userDAO.countTeacher();
        jsonObject.put("Teacher" ,count );*/
        UserDAO userDAO = new UserDAO();
        userDAO.count();
        //Admin admin = new Admin();

        jsonObject.put("counttotal" ,userDAO.getCountTeacher());

        jsonObject.put("Teacher" ,userDAO.getCountTeacher());

        jsonObject.put("TeacherReg" ,userDAO.getTodaycountTeacher());

        jsonObject.put("Student" ,userDAO.getCountStudent());

        jsonObject.put("StudentReg" ,userDAO.getTodaycountStudent());

        System.out.println(jsonObject);
        out.write(jsonObject.toString());
        out.close();
    }




}
