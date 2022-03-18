package Controller;


import Model.AdminDatacheck;
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

public class AdminDatacheckServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession( false );

        String searchValue = request.getParameter("userName");
        String searchType = request.getParameter("searchType");
        System.out.println("Search value " +  searchValue);
        System.out.println("Search value " +  searchType);
        JSONArray jsonArray = new JSONArray();

        /*if ( searchType.equals( "Teacher" ) ){
            AdminDatacheck user = new AdminDatacheck();
            //ArrayList< AdminDatacheck > userList = new ArrayList<>();
            List< JSONObject > userList =  user.searchUser( searchValue , searchType );
            jsonArray = new JSONArray( userList );
        }*/

            AdminDatacheck user = new AdminDatacheck();
            //ArrayList< AdminDatacheck > userList = new ArrayList<>();
            List< JSONObject > userList =  user.searchUser( searchValue , searchType );
            jsonArray = new JSONArray( userList );


        jsonObject.put( "searchResult" , jsonArray );
        out.write(jsonObject.toString());
        out.close();
    }

}