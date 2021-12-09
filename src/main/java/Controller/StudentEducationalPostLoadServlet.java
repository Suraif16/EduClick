package Controller;

import Model.Classroom;
import Model.EducationalWork;
import Model.Post;
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

public class StudentEducationalPostLoadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        JSONObject jsonObject = new JSONObject();

//        JSONArray jsonArray = new JSONArray();

        HttpSession session = request.getSession( false );

        String classroomId = (String) session.getAttribute( "CurrentClassroomId");

        System.out.println("The classroom id : "+classroomId);

        jsonObject.put("serverResponse" , "Allowed");

        Classroom classroom =  new Classroom();

        //Getting the full name of the teacher whom the classroom belongs to

        User user = new User();

        String id = classroom.getClassroomOwnerId(classroomId);
        String fullName = user.getFullName(id);
        System.out.println("Teacher FullName in servlet : "+fullName);

        jsonObject.put("TeacherFullName",fullName);

        ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
        ArrayList<String> ePostIdList = classroom.checkEposts(classroomId);

        for(int i=0;i<ePostIdList.size();i++) {
            /*JSONArray jsonArray1 = new JSONArray();*/
            JSONObject jsonObject1 = new JSONObject();
            Post post = new Post();
            jsonObject1.put("ePost",post.getEPostDetails(ePostIdList.get(i)));
            jsonObject1.put("eWork",post.getEPostContent(ePostIdList.get(i)));
            arr.add(jsonObject1);
        }

        String userId = classroom.getClassroomOwnerId(id);
        System.out.println("Teachers ID : "+userId);

        jsonObject.put("TeacherId",userId);



        JSONArray jsonArray1 = new JSONArray(arr);

        System.out.println(jsonArray1);




        jsonObject.put("EPostContent",jsonArray1);


        out.write(jsonObject.toString());

        out.close();

    }
}
