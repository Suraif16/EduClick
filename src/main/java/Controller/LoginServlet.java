package Controller;

import DAO.LoginDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("Email");
        String password = request.getParameter("Password");

        LoginDAO loginDAO = new LoginDAO();
        loginDAO.login(email);
        if(loginDAO.getPswd().equals(password)){
            out.println("true");
        }
        else {
            out.println("false");
        }


    }
}
