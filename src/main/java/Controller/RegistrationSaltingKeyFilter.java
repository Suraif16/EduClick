package Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RegistrationSaltingKeyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig ){}

    @Override
    public void doFilter(ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain) throws ServletException, IOException {

        String password = servletRequest.getParameter("Password");
        RegistrationSaltingKeyFilterWrapper wrapper = new RegistrationSaltingKeyFilterWrapper( (HttpServletRequest) servletRequest);
        wrapper.setHashedPassword( password );
        filterChain.doFilter( wrapper , servletResponse );

    }


    @Override
    public void destroy(){}

}
