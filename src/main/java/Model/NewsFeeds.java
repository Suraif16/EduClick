
package Model;

import DAO.*;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewsFeeds extends Post{

    private String imagePath;
    private int likeCount;
    private int likeShare;
    private String imageStatus;
    private LocalDate localDate;
    private LocalTime localTime;

    public NewsFeeds() {
        //  super(caption, localDate, localTime);
        this.caption = caption;
        this.localDate = localDate;
        this.localTime = localTime;
        this.imageStatus = imageStatus;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public NewsFeeds(String message, LocalDate localDate, LocalTime localTime, String imageStatus) {
        super( message , localDate , localTime );
        this.imageStatus = imageStatus;
    }
    public NewsFeeds(String message, LocalDate localDate, LocalTime localTime) {

    }




    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeShare() {
        return likeShare;
    }

    public void setLikeShare(int likeShare) {
        this.likeShare = likeShare;
    }




    public JSONArray getNFDetails(Object SharedPostID){
        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        JSONArray NFDetailList = newsFeedsDAO.getNFDetails((String) SharedPostID);
        return NFDetailList;
    }

    public String getImagePath(String newsFeedsIDList){
        NewsFeedsImageDAO newsFeedImageDAO = new NewsFeedsImageDAO();
        String NFImageList = newsFeedImageDAO.getImagePath(newsFeedsIDList);
        return NFImageList;
    }


    public NewsFeeds insertNewsFeeds(FileItem imageFile, String path) throws Exception {

        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        this.setPostID( newsFeedsDAO.insert( this) );

        if ( this.getPostID() != null ){

            NewsFeedsImageDAO newsFeedsImageDAO = new NewsFeedsImageDAO();
            this.setImagePath( newsFeedsImageDAO.insert(this) );

            Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( this.getImagePath() , path + "Resources\\Images\\NewsFeedImages\\" , imageFile );
            saveImage.start();


        }

        return this;

    }

    public static void getTeacherFriendsFollowers(NewsFeeds newsFeeds, String userId){

        AddFriendsDAO addFriendDAO = new AddFriendsDAO();
        ArrayList<String> friendsIDList = addFriendDAO.getTeacherFriendKeys(userId);

        for(int i=0; i<friendsIDList.size(); i++){
            PostDAO postDAO = new PostDAO();
            postDAO.insert(newsFeeds,friendsIDList.get(i), userId );
        }


        FollowsDAO followsDAO = new FollowsDAO();
        ArrayList<String> followersIDList = followsDAO.getTeacherFollowersKeys(userId);

        for(int i=0; i<followersIDList.size(); i++){
            PostDAO postDAO = new PostDAO();
            postDAO.insert(newsFeeds,followersIDList.get(i), userId );
        }

    }

    public static Thread loadNewsFeeds(NewsFeeds newsFeeds, String userId ){

        Runnable runnable = () -> {

            getTeacherFriendsFollowers(  newsFeeds, userId);

        };

        return new Thread( runnable );

    }

    public String getPostedTime(String postId){

        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        String NFpostedTime = newsFeedsDAO.getPostedTime(postId);
        return NFpostedTime;

    }

    public JSONArray getNewsFeedsReceiver(String SharedPostID) throws SQLException {
        ShareDAO shareDAO = new ShareDAO();
        JSONArray NFReceiversList = shareDAO.getNewsFeedsReceiver(SharedPostID);
        return NFReceiversList;
    }

    public JSONObject getNewsFeedsImageDetails(String SharedPostID) {
        NewsFeedsImageDAO newsFeedImageDAO = new NewsFeedsImageDAO();
        JSONObject NFImagePath = newsFeedImageDAO.getNewsFeedsImageDetails(SharedPostID);
        return NFImagePath;
    }



}