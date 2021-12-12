package Model;

import DAO.AnswerDAO;
import DAO.AnswerStudentPostRelationshipDAO;
import DAO.EDWAnswersDAO;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Answers {
    private String questionId;
    private String answer;
    private LocalDate date;
    private LocalTime time;
    private int marks;
    private String userId;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Answers() {

    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Answers(String questionId, String answer, LocalDate date, LocalTime time,String userId) {
        this.questionId = questionId;
        this.answer = answer;
        this.date = date;
        this.time = time;
        this.userId = userId;
    }
    public Answers insertAnswers(FileItem imageFile , String path){

        AnswerDAO answerDAO = new AnswerDAO();
        String answerId = answerDAO.saveAnswers(this);
        if(answerId != null){
            AnswerStudentPostRelationshipDAO answerStudentPostRelationshipDAO = new AnswerStudentPostRelationshipDAO();
            String status = answerStudentPostRelationshipDAO.saveUserAnswerPostDetails(this,answerId);
            if(status.equals("Done")){

                EDWAnswersDAO edwAnswersDao = new EDWAnswersDAO();
                this.setImagePath(edwAnswersDao.insert(this,answerId));
                Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( this.getImagePath() , path + "Resources\\Images\\AnswerImages\\" , imageFile );
                saveImage.start();
            }
        }
        return this;

    }
}
