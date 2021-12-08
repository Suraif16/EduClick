package Model;


import DAO.PostDAO;
import org.json.JSONArray;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Post {

    private String postID;
    private String postName;
    private LocalDate date;
    private LocalTime time;
    private String caption;
    private String userId;

    public Post() {

    }

    public Post( String caption , LocalDate localDate , LocalTime localTime ){

        this.caption = caption;
        this.date = localDate;
        this.time = localTime;

    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }


    public ArrayList<String> getNewsFeedsID(String userId){

        PostDAO postDAO = new PostDAO();
        ArrayList<String> NFKeyList = postDAO.getNewsFeedsKeys(userId);
        return NFKeyList;


    }

    public ArrayList<String> getNFTeacherID(String userId){

        PostDAO postDAO = new PostDAO();
        ArrayList<String> NFTeacherList = postDAO.getNFTeacherKeys(userId);
        return NFTeacherList;

    }


    public JSONArray getIDNewsFeeds(String userId) {

        PostDAO postDAO = new PostDAO();
        JSONArray NewsFeedIDList = postDAO.getIDNF(userId);
        return NewsFeedIDList;
    }
}
