package Model;

import DAO.EducationalPostDAO;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDate;
import java.time.LocalTime;

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

    public EducationalWork(String message , String type , LocalDate localDate , LocalTime localTime ){

        super( message , localDate , localTime );
        this.type = type;

    }

    public void insertEducationalWork( FileItem imageFile , String path ) throws Exception {

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        this.setPostID( educationalPostDAO.insert( this , "EducationalWork" ) );

        if ( this.getPostID() != null ){

            Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( this.getPostID() , path + "Resources\\Images\\EducationalPostImages\\" , imageFile );
            saveImage.start();

        }

    }

}
