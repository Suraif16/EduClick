package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MCQAnswersDAO {

    public void saveMCQAnswers(String answerId, ArrayList<String> studentAnswerList, String postId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            for (int i = 0 ; i<studentAnswerList.size() ; i++){
                String sql = "INSERT INTO MCQ_Answers (AnswerID,Choice,QuestionID) VALUES (?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,answerId);
                preparedStatement.setString(2, studentAnswerList.get(i));
                preparedStatement.setString(3,postId);

                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
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
