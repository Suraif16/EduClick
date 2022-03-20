package Model;

import DAO.NotificationsDAO;

import java.util.ArrayList;

public class Notifications {
    public String getNotifierID() {
        return notifierID;
    }

    public void setNotifierID(String notifierID) {
        this.notifierID = notifierID;
    }

    public String getNotifieeID() {
        return notifieeID;
    }

    public void setNotifieeID(String notifieeID) {
        this.notifieeID = notifieeID;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getPostedType() {
        return postedType;
    }

    public void setPostedType(String postedType) {
        this.postedType = postedType;
    }

    private String notifierID;
    private String notifieeID;
    private String contentID;
    private String postedType;

    public Notifications(String notifierID, String notifieeID, String contentID, String postedType) {
        this.notifierID = notifierID;
        this.notifieeID = notifieeID;
        this.contentID = contentID;
        this.postedType = postedType;
    }

    public Notifications() {

    }

    public void insertNotifications(String notifierId,String notifieeId,String ePostId,String param){
        NotificationsDAO notificationsDAO = new NotificationsDAO();
        notificationsDAO.insertNotifications(notifierId,notifieeId,ePostId,param);
    }

    public void insertEpostNotificationsFromTeacher(String teacherId, ArrayList<String> studentList,String ePostId,String param){
        NotificationsDAO notificationsDAO = new NotificationsDAO();
        notificationsDAO.insertNotificationsFromTeacher(teacherId,studentList,ePostId,param);
    }


}
