package Controller.Authentication;

import Model.User;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class TeacherAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws ServletException , IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession( false );
        /* if there are no sessions then if wont create any, if it is true then it will create a new session*/

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        JSONObject jsonObject = new JSONObject();

        if (session == null){
            /* there is no session */
            System.out.println("session is null");
//            response.sendRedirect("../Login.html");
            jsonObject.put("serverResponse" , "null Session");
            out.write(jsonObject.toString());

        }else {

            if ( session.getAttribute("Admin") == null ){

                User user = (User) session.getAttribute("User" );

                if ( user.getUserType().equals("Teacher") ){

                    filterChain.doFilter( request , response );

                }else{

                    System.out.println("not a Teacher");
                    jsonObject.put( "serverResponse" , "Not Allowed" );
                    out.write(jsonObject.toString());


                }

            }else {
                jsonObject.put( "serverResponse" , "Not Allowed" );
                System.out.println("Admin");
                out.write(jsonObject.toString());

            }

        }
        System.out.println("before close");
        out.close();
        System.out.println("after close");

    }

    @Override
    public void destroy(){}

}
