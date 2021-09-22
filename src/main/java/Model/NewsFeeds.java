package Model;

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
}
