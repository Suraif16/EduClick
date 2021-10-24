package Controller;

import Model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserProfileRedirectServlet extends HttpServlet {

    @Override
    public void doGet( HttpServletRequest request , HttpServletResponse response ) throws IOException {

        HttpSession session = request.getSession( false );
        String userId = request.getParameter("userId");
        User profileUser = new User( userId );
        session.setAttribute( "profileUserId" , userId );
        profileUser.getUser();

        if( session.getAttribute("User") == null ){

            if ( profileUser.getUserType().equals("Teacher") ){

                response.sendRedirect("/EduClick_war_exploded/Teacher/Teacherpro.html");

            }else if ( profileUser.getUserType().equals("Student") ){

                response.sendRedirect("/EduClick_war_exploded/Student/StudentProfile.html");

            }

        }else{

            User user = ( User ) session.getAttribute( "User" );

            if ( user.getUserType().equals( "Teacher" ) ){

                if ( profileUser.getUserType().equals("Teacher") ){

                    response.sendRedirect("/EduClick_war_exploded/Teacher/TeacherProfile.html");

                }else if ( profileUser.getUserType().equals("Student") ){

                    response.sendRedirect("/EduClick_war_exploded/Student/StudentProfile.html");

                }

            }else if ( user.getUserType().equals( "Student" ) ){

                if ( profileUser.getUserType().equals("Teacher") ){

                    response.sendRedirect("/EduClick_war_exploded/Teacher/Teacherpro.html");

                }else if ( profileUser.getUserType().equals("Student") ){

                    response.sendRedirect("/EduClick_war_exploded/Student/StudentProfile.html");

                }

            }

        }





    }

}
