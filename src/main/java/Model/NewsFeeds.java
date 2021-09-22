package Model;

public class NewsFeeds extends Post{

    private String imagePath;
    private Int likeCount;
    private Int likeShare;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Int likeCount) {
        this.likeCount = likeCount;
    }

    public Int getLikeShare() {
        return likeShare;
    }

    public void setLikeShare(Int likeShare) {
        this.likeShare = likeShare;
    }
}
