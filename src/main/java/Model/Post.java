package Model;


import DAO.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Post {

    protected String postID;
    protected String postName;
    protected LocalDate date;
    protected LocalTime time;
    protected String caption;
    protected String userId;

    public Post() {

    }

    public Post(String caption, LocalDate localDate, LocalTime localTime) {

        this.caption = caption;
        this.date = localDate;
        this.time = localTime;

    }

    public Post(LocalDate localDate, LocalTime localTime) {

        this.date = localDate;
        this.time = localTime;

    }

    public Post(String postID) {

        this.postID = postID;

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


    public ArrayList<String> getNewsFeedsID(String userId) {

        PostDAO postDAO = new PostDAO();
        ArrayList<String> NFKeyList = postDAO.getNewsFeedsID(userId);
        return NFKeyList;


    }

    public ArrayList<String> getNFTeacherID(String userId) {

        PostDAO postDAO = new PostDAO();
        ArrayList<String> NFTeacherList = postDAO.getNFTeacherKeys(userId);
        return NFTeacherList;

    }


    public JSONArray getIDNewsFeeds(String userId) {

        PostDAO postDAO = new PostDAO();
        JSONArray NewsFeedIDList = postDAO.getIDNF(userId);
        return NewsFeedIDList;
    }

    public JSONObject getEPostDetails(String classroomId) {
        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        return educationalPostDAO.getEPostDetails(classroomId);
    }

    public JSONObject getEPostContent(String postId) {
        EducationalWorkDAO educationalWorkDAO = new EducationalWorkDAO();
        return educationalWorkDAO.getEPostContent(postId);
    }

    public ArrayList<String> checkEposts(String classroomId) {
        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        return educationalPostDAO.getEpostsIds(classroomId);

    }

    public String selectClassroomId(String ePostId) {
        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        return educationalPostDAO.selectClassroomId(ePostId);

    }


    public JSONArray getMcqResults() {

        AnswerStudentPostRelationshipDAO answerStudentPostRelationshipDAO = new AnswerStudentPostRelationshipDAO();
        return answerStudentPostRelationshipDAO.getMcqResult(this.postID);

    }

    public Object getLoadedNewsFeedsId(String userId) throws SQLException {

        PostDAO postDAO = new PostDAO();
        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        ShareDAO shareDAO = new ShareDAO();
        NewsFeedsImageDAO newsFeedsImageDAO = new NewsFeedsImageDAO();
        UserDAO userDAO = new UserDAO();
        System.out.println(userId+"  aaaaaqqqqq");

        ArrayList<String> NewsFeedUserIdList = postDAO.getLoadedNewsFeedsId(userId);
        //  System.out.println(NewsFeedUserIdList  + "This is news feeds id list");
        ArrayList<String>SharedNewsFeedsIdList = shareDAO.getSharedIdList(userId);
       // System.out.println(SharedNewsFeedsIdList);



        for(int i=0; i<SharedNewsFeedsIdList.size(); i++){
            NewsFeedUserIdList.add(SharedNewsFeedsIdList.get(i));
        }

       /* Set<String> set = new HashSet<String>( NewsFeedUserIdList );*/

        JSONArray array = new JSONArray(  );
      //  System.out.println(array);

      //  System.out.println(NewsFeedUserIdList);
        for (int i = 0; i < NewsFeedUserIdList.size(); i++) {

            JSONObject newsFeedDetails = newsFeedsDAO.getNewsFeedsDetails(NewsFeedUserIdList.get(i));

            String imagePath = newsFeedsImageDAO.getImagePath((String) NewsFeedUserIdList.get(i));

            String ownerId = String.valueOf(postDAO.getOwnerId(NewsFeedUserIdList.get(i)));

            String ownerName = userDAO.getOwnerName(ownerId);

            newsFeedDetails.put("ownerName",ownerName);
            newsFeedDetails.put("path", imagePath);
            newsFeedDetails.put("postId",NewsFeedUserIdList.get(i));
            newsFeedDetails.put("ownerId",ownerId);
            //  System.out.println(array);
            array.put(newsFeedDetails);

        }

        return array;
    }

    public Object getLoadedInsertedNewsFeedsId(String userId) {

        PostDAO postDAO = new PostDAO();
        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        NewsFeedsImageDAO newsFeedsImageDAO = new NewsFeedsImageDAO();
        UserDAO userDAO = new UserDAO();

        ArrayList<String> NewsFeedUserIdList = postDAO.getLoadedNewsFeedsId(userId);
      //  System.out.println(NewsFeedUserIdList  + "This is news feeds id list");

        JSONArray array = new JSONArray();

        for (int i = 0; i < NewsFeedUserIdList.size(); i++) {

            JSONObject newsFeedDetails = newsFeedsDAO.getNewsFeedsDetails(NewsFeedUserIdList.get(i));
         //   System.out.println(newsFeedDetails);
            String imagePath = newsFeedsImageDAO.getImagePath((String) NewsFeedUserIdList.get(i));
            String ownerId = String.valueOf(postDAO.getOwnerId(NewsFeedUserIdList.get(i)));


            String ownerName = userDAO.getOwnerName(userId);

                newsFeedDetails.put("ownerName", ownerName);
                newsFeedDetails.put("path", imagePath);
                newsFeedDetails.put("postId", NewsFeedUserIdList.get(i));
                newsFeedDetails.put("ownerId",ownerId);
                //  System.out.println(array);
                array.put(newsFeedDetails);

            }
        System.out.println(array);
            return array;
        }


    public Object getProfilePageLoadedNewsFeedsId(String userId) throws SQLException {

        PostDAO postDAO = new PostDAO();
        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        ShareDAO shareDAO = new ShareDAO();
        NewsFeedsImageDAO newsFeedsImageDAO = new NewsFeedsImageDAO();
        UserDAO userDAO = new UserDAO();

        ArrayList<String> NewsFeedUserIdList = postDAO.getInsertedNewsFeedsId(userId);
        //  System.out.println(NewsFeedUserIdList  + "This is news feeds id list");
        ArrayList<String>SharedNewsFeedsIdList = shareDAO.getSharedIdList(userId);
        // System.out.println(SharedNewsFeedsIdList);



        for(int i=0; i<SharedNewsFeedsIdList.size(); i++){
            NewsFeedUserIdList.add(SharedNewsFeedsIdList.get(i));
        }

       // Set<String> set = new HashSet<String>( NewsFeedUserIdList );

        JSONArray array = new JSONArray( );
        //  System.out.println(array);

        //  System.out.println(NewsFeedUserIdList);
        for (int i = 0; i < NewsFeedUserIdList.size(); i++) {

            JSONObject newsFeedDetails = newsFeedsDAO.getNewsFeedsDetails(NewsFeedUserIdList.get(i));

            String imagePath = newsFeedsImageDAO.getImagePath((String) NewsFeedUserIdList.get(i));

            String ownerId = String.valueOf(postDAO.getOwnerId(NewsFeedUserIdList.get(i)));

            String ownerName = userDAO.getOwnerName(ownerId);

            newsFeedDetails.put("ownerName",ownerName);
            newsFeedDetails.put("path", imagePath);
            newsFeedDetails.put("postId",NewsFeedUserIdList.get(i));
            newsFeedDetails.put("ownerId",ownerId);
            array.put(newsFeedDetails);

        }


        return array;
    }


}
