package Model;

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


}
