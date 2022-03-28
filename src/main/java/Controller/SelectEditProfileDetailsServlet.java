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

            User user1 = new User( user.getUserId() );
            user1 = user1.getUserDetails();

            jsonObject.put( "firstName" , user1.getFirstName() );
            jsonObject.put( "lastName" , user1.getLastName() );
            jsonObject.put( "country" , user1.getCountry() );
            jsonObject.put( "city" , user1.getCity() );
            jsonObject.put( "mobileNumber" , user1.getMobileNumber() );
            jsonObject.put( "profilePicture" , user1.getProfilePicture() );
            jsonObject.put( "userType" , user.getUserType() );
            jsonObject.put( "countryCode" , user1.getCountryCode() );

            if ( user.getUserType().equals( "Teacher" ) ){

                jsonObject.put( "workPlace" , user1.getWorkPlace() );

            }/* else for Student*/

        }else{

            jsonObject.put( "serverResponse" , "Not Allowed" );

        }

        out.write( jsonObject.toString() );
        out.close();

    }

}
