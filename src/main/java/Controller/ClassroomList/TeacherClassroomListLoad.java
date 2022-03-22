package Controller.ClassroomList;

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

public class TeacherClassroomListLoad extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession( false );
        String userId = request.getParameter("userId");
        User user = new User();
        JSONObject jsonObject = new JSONObject();

        List<Classroom> classroomList = user.TeacherClassroomList(userId);

        for(int i=0;i< classroomList.size();i++){
            System.out.println(classroomList.get(i).getClassroomID());
            System.out.println(classroomList.get(i).getClassroomName());
            System.out.println(classroomList.get(i).getYear());
            System.out.println(classroomList.get(i).getGrade());
            System.out.println(classroomList.get(i).getSubject());
        }

        User user1 = (User) session.getAttribute( "User" );

        Classroom classroom = new Classroom();
        ArrayList<String> studentEnrollList = classroom.getEnrolledClassrooms(user1.getUserId());
        ArrayList<String> requestedClassroomList = classroom.getRequestedClassroomList(user1.getUserId());

        
        JSONArray jsonArray = new JSONArray();

        for ( int i = 0 ; i < classroomList.size() ; i++ ){

            JSONObject jsonObject1 = new JSONObject();
            String x = classroomList.get( i ).getClassroomID();
            boolean isInStudentEnrollList = studentEnrollList.contains( x );
            boolean isInrequestedClassroomList = requestedClassroomList.contains( x );

            jsonObject1.put( "classroomId" ,  classroomList.get( i ).getClassroomID() );
            jsonObject1.put( "classroomName" ,  classroomList.get( i ).getClassroomName() );
            jsonObject1.put( "classroomYear" ,  classroomList.get( i ).getYear() );
            jsonObject1.put( "classroomGrade" ,  classroomList.get( i ).getGrade() );
            jsonObject1.put( "classroomSubject" ,  classroomList.get( i ).getSubject() );

            if ( isInStudentEnrollList ){

                jsonObject1.put( "enrolledStatus" , true );
                jsonObject1.put( "requestedStatus" , false );

            }
            else if(isInrequestedClassroomList){
                jsonObject1.put( "enrolledStatus" , false );
                jsonObject1.put( "requestedStatus" , true );
            }
            else{
                jsonObject1.put( "enrolledStatus" , false );
                jsonObject1.put( "requestedStatus" , false );
            }
            jsonArray.put(jsonObject1);

            System.out.println( x + " : " + isInStudentEnrollList + " : " + isInrequestedClassroomList );


        }




        System.out.println(jsonArray);
        jsonObject.put( "classroomList" , jsonArray);


        out.write(jsonObject.toString());
        out.close();


    }
}
