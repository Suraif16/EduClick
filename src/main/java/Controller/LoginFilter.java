package Controller;

import DAO.LoginDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{}

    @Override
    public void doFilter( ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws ServletException, IOException {
        String password = servletRequest.getParameter("Password");
        LoginFilterWrapper wrapper = new LoginFilterWrapper((HttpServletRequest) servletRequest);

        LoginDAO loginDAO = new LoginDAO();
        String saltingKey = loginDAO.selectSaltingKey( servletRequest.getParameter("Email") );
        /* Here if the salting key is not equal to "" then there is an email id in the database where there exist an salting key
        * we concatenate this key with password and hash it, if there is a users else we just hash the password as the login could be from an admin*/
        if( !( saltingKey.equals("") ) ){

            wrapper.setHashPassword( password + saltingKey );

        }else {

            wrapper.setHashPassword(password);

        }


        filterChain.doFilter( wrapper , servletResponse );
    }

    @Override
    public void destroy(){}

}
