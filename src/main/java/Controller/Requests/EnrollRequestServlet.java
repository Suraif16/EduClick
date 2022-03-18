package Controller.Requests;

import DAO.EnrollDAO;
import DAO.EnrollRequestDAO;
import Model.*;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EnrollRequestServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");


        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute("User");

        System.out.println("EnrolRequest Reached!!");

        String ClassroomId = request.getParameter("id");

        String action = request.getParameter("action");
        System.out.println(action);

        Classroom classroom = new Classroom();
        String teacherId = classroom.getClassroomOwnerId(ClassroomId);
        String param = "Classroom Request";

        Notifications notifications = new Notifications();
        notifications.insertNotifications(user.getUserId(),teacherId,"0",param);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );
        if(action.equals("request")){
            Requests requests = new Requests();
            String status = requests.alreadyEnrolledCheck(ClassroomId,user.getUserId());
            System.out.println("Status : "+status);
            if(status == "Not Enrolled"){
                requests.requestEnroll(ClassroomId,user.getUserId());
                jsonObject.put("Enroll","Requested");
            }else{
                jsonObject.put("Enroll","Already Enrolled");

            }

        }else if(action.equals("delete")){
            Requests requests = new Requests();
            String status = requests.alreadyEnrolledCheck(ClassroomId,user.getUserId());
            System.out.println("Status : "+status);

            if(status=="Enrolled"){
                requests.deleteEnroll(ClassroomId,user.getUserId());
                jsonObject.put("Enroll","Deleted");
            }else{
                jsonObject.put("Enroll","No Enrollment");
            }

        }
        out.write(jsonObject.toString());
        out.close();



    }
}
