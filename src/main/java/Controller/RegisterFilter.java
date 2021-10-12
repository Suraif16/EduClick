package Controller;

import org.json.JSONObject;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String firstName = servletRequest.getParameter("firstName");
        String lastName = servletRequest.getParameter("lastName");
        String email = servletRequest.getParameter("email");
        String dateOfBirth = servletRequest.getParameter("dateOfBirth");
        String userTypeSelect = servletRequest.getParameter("userTypeSelect");
        String country= servletRequest.getParameter("country");
        String city= servletRequest.getParameter("city");
        String newNumber= servletRequest.getParameter("newNumber");
        String genderSelect= servletRequest.getParameter("genderSelect");


        checkAge(dateOfBirth);

        /*System.out.println("Filter Details : "+firstName);
        System.out.println("Filter Details : "+lastName);
        System.out.println("Filter Details : "+email);
        System.out.println("Filter Details : "+dateOfBirth);
        System.out.println("Filter Details : "+userTypeSelect);
        System.out.println("Filter Details : "+country);
        System.out.println("Filter Details : "+city);
        System.out.println("Filter Details : "+newNumber);
        System.out.println("Filter Details : "+genderSelect);*/

        JSONObject jsonObject = new JSONObject();

        if((firstName!=null && firstName.length()<20 && isValidName(firstName)) && (lastName!=null && lastName.length()<20 && isValidName(lastName)) && (email!=null && isValid(email)==true) && (dateOfBirth!=null) && (userTypeSelect!=null) && (country!=null) &&(city!=null) && (newNumber!=null && isValidMobileNo(newNumber)==true  && newNumber.length()<=15) && (genderSelect!=null)){
            System.out.println("Everything is good kid!!! :)");
            //filterChain.doFilter(servletRequest,servletResponse);
            jsonObject.put("Filter","Success");
        }
        else{
            jsonObject.put("Filter","Unsuccess");
        }


    }

    public boolean isValid(String email){
        String PATTERN = "[_a-zA-Z1-9]+(\\.[A-Za-z0-9]*)*@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9]*)*";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidMobileNo(String newNumber){
        String PATTERN = "[0-9]+";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(newNumber);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }

    }

    public boolean isValidName(String name){
        String PATTERN = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }

    }

    public void checkAge(String dateOfBirth){
        Date date = new Date();
        int birthYear = Integer.parseInt(dateOfBirth.substring(0,4));
        int thisYear = date.getYear();



    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
