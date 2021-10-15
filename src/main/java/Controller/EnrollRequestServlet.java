package Controller;

import DAO.EnrollDAO;
import DAO.EnrollRequestDAO;
import Model.Requests;
import Model.Student;
import Model.User;
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

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );
        if(action.equals("request")){
            Requests requests = new Requests();
            String status = requests.alreadyEnrolledCheck(ClassroomId,user.getUserId());
            System.out.println("notEnrolled : "+status);
            if(status == "Not Enrolled"){
                requests.requestEnroll(ClassroomId,user.getUserId());
                jsonObject.put("Enroll","Requested");
            }else{
                System.out.println("Enroll wela inne cer");

            }

        }else if(action.equals("delete")){
            Requests requests = new Requests();
            requests.deleteEnroll(ClassroomId,user.getUserId());
            jsonObject.put("Enroll","Deleted");
        }
        System.out.println(jsonObject);
        out.write(jsonObject.toString());
        out.close();



    }
}
