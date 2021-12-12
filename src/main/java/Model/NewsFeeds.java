package Model;

import DAO.*;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NewsFeeds extends Post{

    private String imagePath;
    private int likeCount;
    private int likeShare;

    public NewsFeeds(String message , LocalDate localDate , LocalTime localTime) {
        super( message , localDate , localTime );
    }

    public NewsFeeds() {

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




    public JSONArray getNFDetails(ArrayList<String> newsFeedsIDList){
        NewsFeedsDAO newsFeedsDAO = new NewsFeedsDAO();
        JSONArray NFDetailList = newsFeedsDAO.getNFDetails(newsFeedsIDList);
        return NFDetailList;
    }

    public JSONArray getImagePath(ArrayList<String> newsFeedsIDList){
        NewsFeedsImageDAO newsFeedImageDAO = new NewsFeedsImageDAO();
        JSONArray NFImageList = newsFeedImageDAO.getImagePath(newsFeedsIDList);
        return NFImageList;
    }

    /*********************/

    public NewsFeeds insertNewsFeeds(FileItem imageFile , String path ) throws Exception {

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


}
