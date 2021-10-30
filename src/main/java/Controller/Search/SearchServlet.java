package Controller.Search;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        HttpSession session = request.getSession();
        String searchValue = request.getParameter( "searchValue" );
        String searchType = request.getParameter( "searchType" );
        session.setAttribute( "searchValue" , searchValue );
        session.setAttribute( "searchType" , searchType );

    }
}
