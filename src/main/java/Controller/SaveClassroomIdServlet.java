package Controller;

import Model.Classroom;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SaveClassroomIdServlet extends HttpServlet {

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("User");

        String id = request.getParameter("id");

        JSONObject jsonObject = new JSONObject();

        Classroom classroom = new Classroom();

        String currentUserId = user.getUserId();

        String userType = user.getUserType();

        if(userType.equals("Teacher")){
            String userID = classroom.getClassroomOwnerId(id);
            if(currentUserId.equals(userID)){
                session.setAttribute("CurrentClassroomId",id);
                response.sendRedirect("Teacher/Classroom.html");
            }else{
                jsonObject.put("Error","No User");
                session.invalidate();
            }

        }
        else if(userType.equals("Student")){
            String userID = user.getUserIdFromClass(id);
            if(currentUserId.equals(userID)){
                session.setAttribute("CurrentClassroomId",id);
                response.sendRedirect("Student/classroom.html");
            }
            else{
                jsonObject.put("Error","No User");
                session.invalidate();
            }
        }
        else{
            jsonObject.put("Error","No User");
            session.invalidate();

        }



    }

}
