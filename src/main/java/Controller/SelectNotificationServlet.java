package Controller;

import Model.Login;
import Model.Notifications;
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

public class SelectNotificationServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );

        Notifications notifications = new Notifications();

        JSONArray jsonArray = notifications.getNoitifications(user.getUserId());

        JSONObject jsonObject1 = user.getBellIconDetails(user.getUserId());
        LocalDate localDate = LocalDate.parse( jsonObject1.getString( "ClickDate" ) );
        LocalTime localTime = LocalTime.parse( jsonObject1.getString( "ClickTime" ) );

        boolean newNotificationStatus = false;

        for ( int i = 0 ; i < jsonArray.length() ; i++ ) {


            LocalDate localDate1 = LocalDate.parse( jsonArray.getJSONObject( i ).getString( "Notification_Date" ) );
            LocalTime localTime1 = LocalTime.parse( jsonArray.getJSONObject( i ).getString( "Notification_Time" ) );

            if ( localDate1.isAfter( localDate ) ){

                newNotificationStatus = true;
                break;

            }else{

                if ( localDate1.isEqual( localDate ) && localTime1.isAfter( localTime ) ){

                    newNotificationStatus = true;
                    break;

                }else{

                    newNotificationStatus = false;

                }

            }

        }

        if ( newNotificationStatus ){

            jsonObject.put( "newNotificationStatus" , true );

        }else {

            jsonObject.put( "newNotificationStatus" , false );

        }

        //updating the login time and date of the login table

        Login login = new Login(user.getUserId());

        login.updateDateAndTimeOnNotificationCall();


        jsonObject.put("notificationList",jsonArray);

        out.write(jsonObject.toString());
        out.close();
    }

}
