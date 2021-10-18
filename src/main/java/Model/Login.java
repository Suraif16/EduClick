package Model;

import DAO.AdminDAO;
import DAO.LoginDAO;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;

public class Login {

    private String email;
    private String password;
    private String saltingKey;
    private LocalDate loginDate;
    private LocalTime loginTime;
    private String emailConfirmation;
    private String passwordIncorrect;

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

    public String getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(String emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    public String getPasswordIncorrect() {
        return passwordIncorrect;
    }

    public void setPasswordIncorrect(String passwordIncorrect) {
        this.passwordIncorrect = passwordIncorrect;
    }

    /*Getters and setters ends here*/

    /*Constructor*/
    public Login(String email , String password , LocalDate loginDate , LocalTime loginTime){
        this.email = email;
        this.password = password;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
    }

    public Login(String email , String password , String saltingKey, LocalDate loginDate , LocalTime loginTime,String userID, String emailConfirmation , String passwordIncorrect){
        this.email = email;
        this.password = password;
        this.saltingKey = saltingKey;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.userID = userID;
        this.emailConfirmation = emailConfirmation;
        this.passwordIncorrect = passwordIncorrect;
    }
    public Login(String email ){
        this.email = email;
    }

    /*other functions*/

    public JSONObject checkPassword(){
        /*here the loginDao is called and it it return null for userid then it is the admin otherwise
        * its a user. If the password is incorrect then it returns as password incorrect*/

        AdminDAO adminDAO = new AdminDAO();
        JSONObject jsonObject = adminDAO.select( this.email );

        if ( ( jsonObject.get( "pswd" ).equals("") ) ){

            /* if pswd is "" then it is a user */
            LoginDAO loginDAO = new LoginDAO();
            jsonObject = loginDAO.select(this.email);

        }

        return jsonObject;

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

    public void updateEmailConfirmation(){

        LoginDAO loginDAO = new LoginDAO();
        loginDAO.updateValueStatus( this , "EmailConfirmation");

    }

    public void updatePasswordIncorrect(){

        LoginDAO loginDAO = new LoginDAO();
        loginDAO.updateValueStatus( this , "PasswordIncorrect");

    }
}
