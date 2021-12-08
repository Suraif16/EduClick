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

        JSONArray jsonArray = new JSONArray();

        HttpSession session = request.getSession( false );

        String classroomId = (String) session.getAttribute( "CurrentClassroomId");

        System.out.println("The classroom id : "+classroomId);

        jsonObject.put("serverResponse" , "Allowed");

        Classroom classroom =  new Classroom();

        ArrayList<String> ePostIdList = classroom.checkEposts(classroomId);

        for(int i=0;i<ePostIdList.size();i++) {
            JSONArray jsonArray1 = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            Post post = new Post();
            jsonObject1 = post.getEPostDetails(ePostIdList.get(i));
            jsonObject2 = post.getEPostContent(ePostIdList.get(i));
            jsonArray1.put(jsonObject1);
            jsonArray1.put(jsonObject2);
            jsonArray.put(jsonArray1);
        }
        System.out.println(jsonArray.get(0));
        System.out.println("\n");
        System.out.println(jsonArray.get(1));




        out.write(jsonObject.toString());

        out.close();

    }
}
