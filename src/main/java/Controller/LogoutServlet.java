package Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) throws IOException {

        HttpSession session = httpServletRequest.getSession();
        session.invalidate();

        httpServletResponse.sendRedirect("Login.html");

    }

}
