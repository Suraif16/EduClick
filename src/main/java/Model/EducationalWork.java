package Model;

import DAO.EducationalPostDAO;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    public EducationalWork( String type , LocalDate localDate , LocalTime localTime ){

        super( localDate , localTime );
        this.type = type;

    }

    public EducationalWork insertEducationalWork( FileItem imageFile , String path , String classroomId ) throws Exception {

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();

        EducationalWork educationalWork = educationalPostDAO.insertEducationalWork( this , "EducationalWork" , classroomId );

        if ( educationalWork != null ){

            Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( educationalWork.getImagePath() , path + "Resources\\Images\\EducationalPostImages\\" , imageFile );
            saveImage.start();

        }



        return educationalWork;

    }

    public void insertMCQEducationalWork( String classroomId , List< Mcq > mcq ){

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        educationalPostDAO.insertMCQ( this , "MCQ" , classroomId , mcq );

    }

}
