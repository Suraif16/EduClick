package Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        HttpSession session = request.getSession( false );
        String searchValue = request.getParameter( "searchValue" );
        session.setAttribute( "searchValue" , searchValue );

    }

}
