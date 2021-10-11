package Model;

import DAO.UserDAO;

public class Admin {
    private String adminName;
    private String email;
    private String password;
    private int todaycountTeacher;
    private int countTeacher;
    private int countStudent;
    private int todaycountStudent;


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

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

    /*constructor*/

    public Admin(String adminName , String email){
        this.adminName = adminName;
        this.email = email;
    }


}
