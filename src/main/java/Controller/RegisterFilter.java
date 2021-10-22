package Controller;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.YearMonth;

public class RegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String firstName = servletRequest.getParameter("firstName");
        String lastName = servletRequest.getParameter("lastName");
        String email = servletRequest.getParameter("email");
        String dateOfBirth = servletRequest.getParameter("dateOfBirth");
        String userTypeSelect = servletRequest.getParameter("userTypeSelect");
        String country= servletRequest.getParameter("country");
        String city= servletRequest.getParameter("city");
        String newNumber= servletRequest.getParameter("newNumber");
        String genderSelect= servletRequest.getParameter("genderSelect");


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

        if((firstName!=null && firstName.length()<20 && isValidName(firstName)) && (lastName!=null && lastName.length()<20 && isValidName(lastName)) && (email!=null && isValid(email)==true && email.length()<320) && (dateOfBirth!=null && checkAge(dateOfBirth)) && (userTypeSelect!=null) && (country!=null) &&(city!=null) && (newNumber!=null && isValidMobileNo(newNumber)==true  && newNumber.length()<=15) && (genderSelect!=null)){
            System.out.println("Everything is good kid!!! :)");
            filterChain.doFilter(servletRequest,servletResponse);
            jsonObject.put("Filter","Success");
        }
        else{
            String str = "";
            if(firstName==null || firstName.length()>=20){
                String s="First name is null or character length is greater than 20.";
                str = str + s;
            }
            if(lastName==null || lastName.length()>=20){
                String s="Last name is null or character length is greater than 20.";
                str = str + s;
            }
            if(!isValidName(firstName)){
                String s = "First name cannot have numbers.";
                str = str + s;
            }
            if(!isValidName(lastName)){
                String s = "Last name cannot have numbers.";
                str = str + s;
            }
            if(!isValid(email) || email.length()>320){
                String s = "Email is not valid or exceeded the length.";
                str = str + s;
            }
            if(dateOfBirth==null && !checkAge(dateOfBirth)){
                String s = "DOB is null or age limit is not enough to register.";
                str = str +s;
            }
            if(userTypeSelect==null){
                String s = "UserType is not selected.";
                str = str + s;
            }
            if(country==null){
                String s = "Country is not selected.";
                str = str + s;
            }
            if(city==null){
                String s = "City is not entered.";
                str = str + s;
            }
            if(newNumber==null || isValidMobileNo(newNumber)!=true  || newNumber.length()>15){
                String s = "Number field is empty or number is not valid or length exceeded.";
                str = str + s;
            }
            if(genderSelect==null){
                String s = "Gender is not selected.";
                str = str + s;
            }
            System.out.println(str);
            jsonObject.put("Filter",str);
        }

        out.write(jsonObject.toString());
        out.close();
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

    public boolean checkAge(String dateOfBirth){
        int birthYear = Integer.parseInt(dateOfBirth.substring(0,4));
        int thisYear = YearMonth.now().getYear();
        int age = thisYear - birthYear;
        if(age>13){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
