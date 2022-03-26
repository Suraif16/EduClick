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
import java.util.ArrayList;

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
            String status = requests.deleteEnroll(ClassroomId,user.getUserId());
            System.out.println("Status : "+status);

            if(status=="Enrollment Deleted"){

                jsonObject.put("Enroll","Deleted");
            }

        }

        else if(action.equals("apply")){
            Requests requests = new Requests();
            String status = requests.deleteRequest(ClassroomId,user.getUserId());
            System.out.println("Status : "+status);

            if(status=="Request Deleted"){

                jsonObject.put("Enroll","Request Deleted");
            }

        }


        ArrayList<String> studentEnrollList = classroom.getStudentListInClass(ClassroomId);

        for(int i=0;i<studentEnrollList.size();i++){
            System.out.println("Check ckeck ckech  1 ::" + studentEnrollList.get(i));
            if(studentEnrollList.get(i).equals(user.getUserId())){
                System.out.println("The user Id1 : "+studentEnrollList.get(i));
                jsonObject.put("Enroll_Status","Enrolled");
            }
        }

        Requests requests = new Requests();

        System.out.println("The user ID is : "+user.getUserId());

        ArrayList<String> studentEnrollListCheck = requests.requestCheck(ClassroomId);

        for(int i=0;i<studentEnrollListCheck.size();i++){
            System.out.println("Check ckeck ckech 2 :: " + studentEnrollListCheck.get(i));
            if(studentEnrollListCheck.get(i).equals(user.getUserId())){
                System.out.println("The user Id2 is : "+studentEnrollListCheck.get(i));
                jsonObject.put("Enroll_Status","Enroll Requested");
            }
        }
        System.out.println("JSON : "+jsonObject);

        out.write(jsonObject.toString());
        out.close();



    }
}
