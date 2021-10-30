package Controller.Search;


import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SearchTeacherServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();

        HttpSession session = request.getSession( false );

        String searchValue = (String) session.getAttribute( "searchValue" );
        String searchType = (String) session.getAttribute( "searchType" );
        System.out.println("Search value " + searchValue);
        System.out.println("Search type " + searchType);
        JSONArray jsonArray = new JSONArray();

        if ( searchType.equals( "Teacher" ) || searchType.equals( "Student" ) ){

            User user = new User();
            List< JSONObject > userList =  user.searchUser( searchValue , searchType , ( User ) session.getAttribute( "User" ) );
            jsonArray = new JSONArray( userList );

        }else if ( searchType.equals("Post") ){

            jsonArray = new JSONArray(  );

        }else if ( searchType.equals( "Answer" ) ){

            jsonArray = new JSONArray(  );

        }


        jsonObject.put( "teacherList" , jsonArray );
        jsonObject.put( "searchValue" , searchValue );
        out.write(jsonObject.toString());
        out.close();
    }

}