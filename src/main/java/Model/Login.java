package Model;

import DAO.LoginDAO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Login {

    private String email;
    private String password;
    private LocalDate loginDate;
    private LocalTime loginTime;

    /*Getters and setters begins here*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        this.loginTime = loginTime;
    }

    /*Getters and setters ends here*/

    /*Constructor*/
    public Login(String email , String password , LocalDate loginDate , LocalTime loginTime){
        this.email = email;
        this.password = password;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
    }

    /*other functions*/

    public String checkPassword(){
        /*here the loginDao is called and it it return null for userid then it is the admin otherwise
        * its a user. If the password is incorrect then it returns as password incorrect*/
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.select(this.email);
        if(loginDAO.getPswd().equals(this.password)){

            if (!loginDAO.getUserid().equals("")){
                return loginDAO.getUserid();
            }
            return "";

        }
        else {

            System.out.println("password incorrect");
            return "password incorrect";

        }


    }
    public void insertRecord(){
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.enter(this);
    }


}
