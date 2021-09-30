package Controller.Authentication;

import Model.User;
import com.sun.media.sound.SoftTuning;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TeacherAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws ServletException , IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession( false );
        /* if there are no sessions then if wont create any, if it is true then it will create a new session*/

        if (session == null){
            /* there is no session */
            System.out.println("session is null");
            response.sendRedirect("/Login.html");
        }else {

            if ( session.getAttribute("Admin") == null ){

                User user = (User) session.getAttribute("User");

                if ( user.getUserType().equals("Teacher") ){

                    filterChain.doFilter( request , response );

                }else{
                    System.out.println("not a Teacher");
                }

            }else {
                System.out.println("Admin");
            }

        }

    }

    @Override
    public void destroy(){}

}
