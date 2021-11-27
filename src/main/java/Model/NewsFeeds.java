package Model;

import DAO.NewsFeedsDAO;
import DAO.UserDAO;
import org.json.JSONArray;

import java.util.ArrayList;

public class NewsFeeds extends Post{

    private String imagePath;
    private int likeCount;
    private int likeShare;

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
    
    
    
}
