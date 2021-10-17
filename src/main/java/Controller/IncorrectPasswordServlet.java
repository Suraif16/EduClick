package Controller;

import Model.Login;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IncorrectPasswordServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ){

        HttpSession session = request.getSession( false );
        String email = (String) session.getAttribute( "Email" );

        Login login = new Login( email );

        login.setPasswordIncorrect( "True" );
        login.updatePasswordIncorrect();
        session.setAttribute( "optStatus" , "Login");
    }

}
