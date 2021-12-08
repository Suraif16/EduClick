package Model;

public class EducationalWork extends Post{

    private String imagePath;
    private String type;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EducationalWork(){

        super();

    }

}
