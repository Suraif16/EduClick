package Model;

import DAO.AdminPostDAO;
import DAO.UserDAO;
import DAO.ChartDAO;
import org.json.JSONArray;

public class Admin {
    private String adminName;
    private String email;
    private String password;

    private int countTeacher;
    private int todaycountTeacher;
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

    public int getTodaycountTeacher() {
        return todaycountTeacher;
    }

    public void setTodaycountTeacher(int todaycountTeacher) {
        this.todaycountTeacher = todaycountTeacher;
    }

    public int getCountTeacher() {
        return countTeacher;
    }

    public void setCountTeacher(int countTeacher) {
        this.countTeacher = countTeacher;
    }

    public int getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }

    public int getTodaycountStudent() {
        return todaycountStudent;
    }

    public void setTodaycountStudent(int todaycountStudent) {
        this.todaycountStudent = todaycountStudent;
    }
    /*constructor*/

    public Admin(String admin, String email){
        this.adminName = adminName;
        this.email = this.email;
    }
    public Admin() { }

    public Admin getCount(){
        UserDAO admindao = new UserDAO();
        return admindao.count(this);
    }
    public JSONArray getChartDetails(){
        ChartDAO chartDAO =  new ChartDAO();
        JSONArray ChartDetails = chartDAO.getChartDetails();
        return ChartDetails;
    }
}
