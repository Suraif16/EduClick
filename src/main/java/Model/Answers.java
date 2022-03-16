package Model;

import DAO.*;
import Model.HandlingImages_Multipart.ImageJPEGConverterAndCompressor;
import org.apache.commons.fileupload.FileItem;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private String questionId;
    private String answer;
    private LocalDate date;
    private LocalTime time;
    private int marks;
    private String userId;
    private String imageStatus;

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imagePath) {
        this.imageStatus = imagePath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Answers(String questionId, String answer, LocalDate date, LocalTime time,String userId,String imageStatus) {
        this.questionId = questionId;
        this.answer = answer;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.imageStatus = imageStatus;
    }

    public Answers(String ePostId, String userId) {
        this.questionId = ePostId;
        this.userId = userId;
    }

    public Answers(LocalDate date, LocalTime time, int marks) {
        this.date = date;
        this.time = time;
        this.marks = marks;
    }

    public Answers(){}

    public Answers insertAnswers(FileItem imageFile , String path){

        AnswerDAO answerDAO = new AnswerDAO();
        String answerId = answerDAO.saveAnswers(this);
        if(answerId != null){
            AnswerStudentPostRelationshipDAO answerStudentPostRelationshipDAO = new AnswerStudentPostRelationshipDAO();
            String status = answerStudentPostRelationshipDAO.saveUserAnswerPostDetails(this,answerId);
            if(status.equals("Done")){

                EDWAnswersDAO edwAnswersDao = new EDWAnswersDAO();
                edwAnswersDao.insert(this,answerId);
                Thread saveImage = ImageJPEGConverterAndCompressor.convertCompressJPEG( answerId , path + "Resources\\Images\\AnswerImages\\" , imageFile );
                saveImage.start();
            }
        }
        return this;

    }

    public String getAnswerId(){
        System.out.println(this.getQuestionId());
        System.out.println(this.getUserId());
        AnswerStudentPostRelationshipDAO answerStudentPostRelationshipDAO = new AnswerStudentPostRelationshipDAO();
        return answerStudentPostRelationshipDAO.getAnswerId(this);
    }

    public JSONObject getAnswerDetails(String answerId){
        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.selectAnswerDetails(answerId);
    }
    public JSONObject getAnswerContent(String answerId){
         EDWAnswersDAO edwAnswersDAO = new EDWAnswersDAO();
         return edwAnswersDAO.getAnswerContent(answerId);
    }
    public String enterMCQMarks(){
        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.enterMCQMarks(this);
    }
    public void saveMCQAnswers(String answerId, String studentAnswerListElement, String questionId){
        MCQAnswersDAO mcqAnswersDAO = new MCQAnswersDAO();
        mcqAnswersDAO.saveMCQAnswers(answerId,studentAnswerListElement,questionId);

    }


    public List< JSONObject > getEpostAnswers( String epostId ){

        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.selectEpostAnswer( epostId );
    }

    public void saveMCQAnswerPostStudentRelationship(String userId,String answerId,String postId){
        AnswerStudentPostRelationshipDAO answerStudentPostRelationshipDAO = new AnswerStudentPostRelationshipDAO();
        answerStudentPostRelationshipDAO.saveMCQAnswerPostStudentRelationship(userId,answerId,postId);

    }

    public String selectMarksForMCQ(String answerId){
        AnswerDAO answerDAO = new AnswerDAO();
        return answerDAO.selectMarksForMCQ(answerId);

    }
}
