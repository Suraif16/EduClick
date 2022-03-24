package Model;

import DAO.AdminPostDAO;
import DAO.*;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDate;
import java.time.LocalTime;

public class AdminWork extends Post{

    private String sysPostID;
    private String textMsg;
    private String photo;
    private LocalDate date;
    private LocalTime time;


    public String getSysPostID() {
        return sysPostID;
    }
    public void setSysPostID(String sysPostID) {
        this.sysPostID = sysPostID;
    }

    public String getTextMsg() {
        return textMsg;
    }
    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }


    public AdminWork(){ }

    public AdminWork(String textMsg , LocalDate date , LocalTime time ,String photo){
        this.textMsg = textMsg;
        this.date = date;
        this.time = time;
        this.photo= photo;
    }

    public AdminWork insertAdminPostWork(FileItem imageFile , String path ) throws Exception {

        AdminPostDAO adminPostDAO = new AdminPostDAO();
        this.setSysPostID( adminPostDAO.insert2( this) );
        if ( this.getSysPostID() != null ){
            Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( this.getSysPostID() , path + "Resources\\Images\\AdminPostImages\\" , imageFile );
            saveImage.start();
        }
        return this;
    }
    public String adminDelete(String postID){
        AdminPostDAO adminPostDAO = new AdminPostDAO();
        return adminPostDAO.deleteRecord(postID);
    }

}
