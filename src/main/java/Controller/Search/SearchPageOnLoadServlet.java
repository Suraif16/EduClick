package Controller.Search;

import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchPageOnLoadServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );
        JSONObject jsonObject = new JSONObject();

        if ( session.getAttribute("User") == null ){

            jsonObject.put( "UserName" , "Guest");
            jsonObject.put( "UserType" , "Guest" );
            jsonObject.put( "Url" , "/EduClick_war_exploded/Home.html");

        }else{

            User user = ( User ) session.getAttribute("User");
            jsonObject.put( "UserName" , user.getFirstName() );

            if ( user.getUserType().equals("Teacher") ){
                jsonObject.put( "Url" , "/EduClick_war_exploded/Teacher/Teacher.html");
                jsonObject.put( "UserType" , "Teacher" );

            }else if (user.getUserType().equals("Student")){

                jsonObject.put( "Url" , "/EduClick_war_exploded/Student/student.html");
                jsonObject.put( "UserType" , "Student" );

            }

        }

        out.write( jsonObject.toString() );
        out.close();


    }

}
