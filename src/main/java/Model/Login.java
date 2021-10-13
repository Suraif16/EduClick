package Model;

import DAO.LoginDAO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Login {

    private String email;
    private String password;
    private String saltingKey;
    private LocalDate loginDate;
    private LocalTime loginTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

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

    public String getSaltingKey() {
        return saltingKey;
    }

    public void setSaltingKey(String saltingKey) {
        this.saltingKey = saltingKey;
    }

    /*Getters and setters ends here*/

    /*Constructor*/
    public Login(String email , String password , LocalDate loginDate , LocalTime loginTime){
        this.email = email;
        this.password = password;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
    }

    public Login(String email , String password , String saltingKey, LocalDate loginDate , LocalTime loginTime,String userID){
        this.email = email;
        this.password = password;
        this.saltingKey = saltingKey;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.userID = userID;
    }
    public Login(String email ){
        this.email = email;
    }

    /*other functions*/

    public String checkPassword(){
        /*here the loginDao is called and it it return null for userid then it is the admin otherwise
        * its a user. If the password is incorrect then it returns as password incorrect*/
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.select(this.email);
        if(loginDAO.getPswd()==null && loginDAO.getUserid()==null){
            return "User does not exist";

        }else if(loginDAO.getPswd().equals(this.password)){
            if ( !loginDAO.getUserid().equals("") ){
                loginDAO.update(this.email,this.loginDate,this.loginTime);
                return loginDAO.getUserid();
            }
            return "";
        }
        else {
            System.out.println("password incorrect");
            return "password incorrect";
        }
    }

    public String checkEmail(){
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.validateEmail(this.email);
        if(loginDAO.getEmailDAO()==null){
            return "Email doesn't exsist";
        }
        return "Email exsist";
    }
    public void insertRecord(){
        LoginDAO loginDAO = new LoginDAO();
        loginDAO.enter(this);
    }
}
