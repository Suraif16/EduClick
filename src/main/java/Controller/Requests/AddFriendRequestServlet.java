package Controller.Requests;

import Model.Requests;
import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddFriendRequestServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        HttpSession session = request.getSession( false );
        User user = ( User ) session.getAttribute( "User" );
        String fromID = user.getUserId();
        String toID = request.getParameter( "toID" );

        Requests requests = new Requests( fromID , toID );
        requests.addRequest();

    }



}
