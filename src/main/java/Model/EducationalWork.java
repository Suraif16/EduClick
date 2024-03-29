package Model;

import DAO.EducationalPostDAO;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EducationalWork extends Post{

    private String imageStatus;
    private String type;

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EducationalWork(){



    }

    public EducationalWork(String message , String type , LocalDate localDate , LocalTime localTime , String imageStatus ){

        super( message , localDate , localTime );
        this.type = type;
        this.imageStatus = imageStatus;

    }

    public EducationalWork( String type , LocalDate localDate , LocalTime localTime ){

        super( localDate , localTime );
        this.type = type;

    }

    public EducationalWork insertEducationalWork( FileItem imageFile , String path , String classroomId ) throws Exception {

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();

        EducationalWork educationalWork = educationalPostDAO.insertEducationalWork( this , "EducationalWork" , classroomId );

        if ( educationalWork != null && educationalWork.getImageStatus().equals( "true" ) ){
            /* here the post id is given as the image name*/
            Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( educationalWork.getPostID() , path + "Resources\\Images\\EducationalPostImages\\" , imageFile );
            saveImage.start();

        }



        return educationalWork;

    }

    public void insertMCQEducationalWork( String classroomId , List< Mcq > mcq , String userId ){

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        String epostId = educationalPostDAO.insertMCQ( this , "MCQ" , classroomId , mcq );

        Classroom classroom = new Classroom();
        ArrayList<String> studentList = classroom.getStudentListInClass( classroomId );

        Notifications notifications = new Notifications();
        notifications.insertEpostNotificationsFromTeacher( userId , studentList , epostId , "Educational Post" );

    }

    public List<JSONObject> selectEducationalPost( String classroomId , String minPostId ){

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        return educationalPostDAO.select( classroomId , minPostId );

    }
    public List<JSONObject> selectEducationalPostMCQ( String classroomId , String minPostId ,String userId ){

        System.out.println("I am Running!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        EducationalPostDAO educationalPostDAO = new EducationalPostDAO();
        return educationalPostDAO.selectForMCQ( classroomId , minPostId ,userId);

    }

}
