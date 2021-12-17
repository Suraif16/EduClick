package Model;

import DAO.QuestionDAO;

import java.util.ArrayList;

public class Question {
    private String questionId;
    private String question;
    private String answer;
    private String correctAnswer;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> selectCorrectAnswers(String postId){
        QuestionDAO questionDAO = new QuestionDAO();
        return  questionDAO.selectCorrectAnswers(postId);
    }
}
