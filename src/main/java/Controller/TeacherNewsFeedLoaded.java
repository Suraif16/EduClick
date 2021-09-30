package Controller;

import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherNewsFeedLoaded extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        /*PrintWriter out = response.getWriter();
        response.setContentType("text/html");*/
        System.out.println("servlet reached");


    }

}
