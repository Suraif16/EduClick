package DAO;

import Database.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MCQAnswersDAO {

    public void saveMCQAnswers(String answerId, String studentAnswerListElement, String questionId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
                String sql = "INSERT INTO MCQ_Answers (AnswerID,Choice,QuestionID) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,answerId);
                preparedStatement.setString(2, studentAnswerListElement);
                preparedStatement.setString(3,questionId);

                preparedStatement.executeUpdate();
                preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }

    }

}
