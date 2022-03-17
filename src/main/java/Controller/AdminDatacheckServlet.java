package Controller;


import Model.AdminDatacheck;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


public class AdminDatacheckServlet extends HttpServlet {
    @Override

    public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put( "serverResponse" , "Allowed" );
        AdminDatacheck user = new AdminDatacheck();
        user = user.getData();
        jsonObject.put("getUserId" ,user.getUserId());
        System.out.println(user.getUserId());
        jsonObject.put("getFirstName" ,user.getFirstName());
        System.out.println(user.getFirstName());
        jsonObject.put("getLastName" ,user.getLastName());
        System.out.println(user.getLastName());
        jsonObject.put("getGender" ,user.getGender());
        System.out.println(user.getGender());
        jsonObject.put("getDateOfBirth" ,user.getDateOfBirth());
        System.out.println(user.getDateOfBirth());
        jsonObject.put("getCountry" ,user.getCountry());
        System.out.println(user.getCountry());
        jsonObject.put("getRegistrationDate" ,user.getRegistrationDate());
        System.out.println(user.getRegistrationDate());
        jsonObject.put("getRegistrationTime" ,user.getRegistrationTime());
        System.out.println(user.getRegistrationTime());
        jsonObject.put("getUserType" ,user.getUserType());
        System.out.println(user.getUserType());


        System.out.println(jsonObject);
        out.write(jsonObject.toString());
        out.close();
    }


}
