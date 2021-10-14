package Controller;

import Model.Classroom;
import Model.Requests;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DisplayRequestServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serverResponse" , "Allowed");

        HttpSession session = request.getSession( false );

        User user = ( User ) session.getAttribute( "User" );
        System.out.println("hi");
        List< Requests > requestList = new ArrayList<>();

        Classroom classroom = new Classroom( user.getUserId() );

        List<Classroom> classroomList = classroom.getListOfCLassRooms();

        Requests requests = new Requests();

        for (Classroom classroom1 : classroomList){

            requestList.addAll( requests.selectRequests( classroom1 ) );

        }

        requestList.addAll( requests.selectRequests( user.getUserId() ) );

        for( int i = 0 ; i < requestList.size() ; i++ ){

            User user1 = new User( requestList.get( i ).getFromId() );
            System.out.println( "before" );
            requestList.get( i ).setUserName( user1.getUser().getFirstName() );
            requestList.get( i ).setUserProfile( user1.getUser().getProfilePicture() );

        }
        JSONArray jsonArray = new JSONArray( requestList );
        System.out.println("after json array");
        jsonObject.put("requestList" , jsonArray);

        out.write( jsonObject.toString() );
        out.close();

    }

}
