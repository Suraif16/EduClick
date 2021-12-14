package Model;

import DAO.QuestionAnswerValuesDAO;
import DAO.QuestionDAO;

public class Mcq {
    private String questionId;
    private String question;
    private String correctAnswer;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;

    public Mcq( String question, String correctAnswer, String answer1, String answer2, String answer3, String answer4 ) {

        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;

    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswerI( int i ){

        if ( i == 0 ){ return this.answer1; }
        else if ( i == 1 ){ return this.answer2; }
        else if ( i == 2 ){ return this.answer3; }
        else if ( i == 3 ){ return this.answer4; }
        else { return null; }

    }

    public void insertQuestionAndAnswer( String mcqPostId){

        QuestionDAO questionDAO = new QuestionDAO();
        this.setQuestionId( questionDAO.insert( this , mcqPostId ) );

        if ( this.getQuestion() != null ){

            QuestionAnswerValuesDAO questionAnswerValuesDAO = new QuestionAnswerValuesDAO();

            questionAnswerValuesDAO.insert( this.getQuestionId() , "1" , this.getAnswer1() );
            questionAnswerValuesDAO.insert( this.getQuestionId() , "2" , this.getAnswer2() );
            questionAnswerValuesDAO.insert( this.getQuestionId() , "3" , this.getAnswer3() );
            questionAnswerValuesDAO.insert( this.getQuestionId() , "4" , this.getAnswer4() );

        }


    }


}
