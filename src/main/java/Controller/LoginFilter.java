package Controller;

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
        wrapper.setHashPassword(password);
        filterChain.doFilter( wrapper , servletResponse );
    }

    @Override
    public void destroy(){}

}
