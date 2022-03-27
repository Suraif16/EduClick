package Controller;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SelectEditProfileDetailsServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType( "text/html" );

        HttpSession session = request.getSession( false );

        User user = ( User ) session.getAttribute( "User" );

        String userProfileId = request.getParameter( "userId" );

        JSONObject jsonObject = new JSONObject();

        if ( user.getUserId().equals( userProfileId ) ){

            jsonObject.put( "serverResponse" , "Allowed" );

            jsonObject.put( "firstName" , user.getFirstName() );
            jsonObject.put( "lastName" , user.getLastName() );
            jsonObject.put( "country" , user.getCountry() );
            jsonObject.put( "city" , user.getCity() );
            jsonObject.put( "mobileNumber" , user.getMobileNumber() );
            jsonObject.put( "profilePicture" , user.getProfilePicture() );
            jsonObject.put( "userType" , user.getUserType() );

            if ( user.getUserType().equals( "Teacher" ) ){

                jsonObject.put( "workPlace" , user.getWorkPlace() );

            }/* else for Student*/
            System.out.println( user.getFirstName() + " : " + user.getLastName() + " : " + user.getUserType() + " : " + user.getCountry() + " : " + user.getCity()  + " : " + user.getMobileNumber() + " : " + user.getProfilePicture() + " : " + user.getWorkPlace());

        }else{

            jsonObject.put( "serverResponse" , "Not Allowed" );

        }

        out.write( jsonObject.toString() );
        out.close();

    }

}
