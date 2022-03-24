package Controller.Requests;

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
import java.time.LocalDate;
import java.time.LocalTime;
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

        List< Requests > requestList = new ArrayList<>();

        Classroom classroom = new Classroom( user.getUserId() );
        /* this gets the list of classrooms for the user*/
        List<Classroom> classroomList = classroom.getListOfCLassRooms();

        Requests requests = new Requests();
        /* Here for each request we check if there are enroll requests, if there are requests, then these requests are appended to the list*/
        for (Classroom classroom1 : classroomList){

            requestList.addAll( requests.selectRequests( classroom1 ) );

        }
        /* Here all the friend requests for the user is taken form the database and append to the same list*/
        requestList.addAll( requests.selectRequests( user.getUserId() ) );

        for( int i = 0 ; i < requestList.size() ; i++ ){
            /* For each request object in the list we get the user details using  fromUserId and them to the object*/
            User user1 = new User( requestList.get( i ).getFromId() );

            requestList.get( i ).setUserName( user1.getUser().getFirstName() );
            requestList.get( i ).setUserProfile( user1.getUser().getProfilePicture() );

        }

        JSONObject jsonObject1 = user.getBellIconDetails(user.getUserId());
        LocalDate localDate = LocalDate.parse( jsonObject1.getString( "ClickDate" ) );
        LocalTime localTime = LocalTime.parse( jsonObject1.getString( "ClickTime" ) );

        JSONArray jsonArray = new JSONArray( requestList );
        Boolean newRequestStatus = false;

        for ( int i = 0 ; i < requestList.size() ; i++ ) {


            LocalDate localDate1 = LocalDate.parse( requestList.get( i ).getRequestDate().toString() );
            LocalTime localTime1 = LocalTime.parse( requestList.get( i ).getRequestTime().toString() );

            if ( localDate1.isAfter( localDate ) ){

                newRequestStatus = true;
                break;

            }else{

                if ( localDate1.isEqual( localDate ) && localTime1.isAfter( localTime ) ){

                    newRequestStatus = true;
                    break;

                }else{

                    newRequestStatus = false;

                }

            }

        }

        if ( newRequestStatus ){

            jsonObject.put( "newRequestStatus" , true );

        }else {

            jsonObject.put( "newRequestStatus" , false );

        }

        jsonObject.put("requestList" , jsonArray);

        out.write( jsonObject.toString() );
        out.close();

    }

}
