package Controller.Classroom;

import Model.Classroom;
import Model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherCreateClassroomServlet extends HttpServlet {

    @Override
    public void doPost( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "serverResponse" , "Allowed" );

        HttpSession session = request.getSession( false );

        User user = (User) session.getAttribute( "User" );

        String classroomName = request.getParameter( "classroomName" );
        String subject = request.getParameter(( "subject" ));
        String grade = request.getParameter(( "grade" ));
        String yearOfExamination = request.getParameter( "yearOfExamination" );

        System.out.println( classroomName + subject + grade + yearOfExamination + user.getUserId());

        Classroom classroom = new Classroom( classroomName , subject , grade , yearOfExamination , user.getUserId() );

        classroom.createClassroom();

        if ( classroom.getClassroomID() == null){
            /* this means that the classroom wasn't created */
            jsonObject.put( "classroomId" , "Classroom not created" );

        }else{
            /* this means that the classroom was created and the classroomStatus has the
             * id of the created classroom*/
            jsonObject.put( "classroomId" , classroom.getClassroomID() );

        }

        out.write(jsonObject.toString());
        out.close();

    }

}
