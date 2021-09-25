package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginFilterWrapper extends HttpServletRequestWrapper {
    private String hashedPassword;

    public LoginFilterWrapper(HttpServletRequest request) { super( request ); }

    public void setHashPassword( String password ){
        hashedPassword = DigestUtils.sha256Hex( password );

    }

    @Override
    public String getParameter( String key ){

        if( key.equals("Password")){
            return hashedPassword;
        }
        else {
            return super.getParameter(key);
        }

    }

}
