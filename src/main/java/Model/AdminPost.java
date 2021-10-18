package Model;

import DAO.AdminPostDAO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminPost {

    private String sysPostID;
    private String textMsg;
    private String photo;
    private LocalDate date;
    private LocalTime time;

    public String getSysPostID() {
        return sysPostID;
    }

    public void setSysPostID(String sysPostID) {
        this.sysPostID = sysPostID;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public AdminPost(String sysPostID, String textMsg, LocalDate date, LocalTime time) {
        this.sysPostID = sysPostID;
        this.textMsg = textMsg;
        this.date = date;
        this.time = time;
    }
    public void adminPost(){
        AdminPostDAO adminPostDAO = new AdminPostDAO();
        adminPostDAO.insert(this);
        //UserDAO userDAO = new UserDAO();
        //userDAO.insert(this);
        //userId=userDAO.getGeneratedUserId();
    }
    public AdminPost(AdminPost adminPost){
        this.sysPostID = adminPost.getSysPostID();
        this.textMsg = adminPost.getTextMsg();
        this.date = adminPost.getDate();
        this.time = adminPost.getTime();
    }
    public AdminPost getAdminPost(){
        AdminPostDAO adminPostDAO = new AdminPostDAO();
        return adminPostDAO.select(this);
    }
}
