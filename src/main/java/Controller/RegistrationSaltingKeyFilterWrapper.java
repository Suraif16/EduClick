package Controller;

import Model.GenerateRandomNumbers.SaltingKeyGeneration;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Random;

public class RegistrationSaltingKeyFilterWrapper extends HttpServletRequestWrapper {
    private String hashedPassword;
    private String saltingKey;

    public RegistrationSaltingKeyFilterWrapper(HttpServletRequest request){ super(request);}

    private String generateSaltingKey(){

        Random random = new Random();
        int length = random.nextInt(10) + 10;
        SaltingKeyGeneration saltingKeyGeneration = new SaltingKeyGeneration();
        saltingKey = saltingKeyGeneration.generateSaltingKey( length );
        return saltingKey;
    }

    public void setHashedPassword( String password ){

        String saltedPassword = password + generateSaltingKey();
        hashedPassword = DigestUtils.sha256Hex( saltedPassword );
    }


    @Override
    public String getParameter( String key ){

        if (key.equals("Password")){
            return hashedPassword;
        }else if (key.equals("saltingKey")){
            return saltingKey;
        }else{

            return super.getParameter(key);
        }
    }
}
