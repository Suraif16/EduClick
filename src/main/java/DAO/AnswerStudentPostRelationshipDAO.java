package DAO;

import Database.DBConnectionPool;
import Model.Answers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerStudentPostRelationshipDAO {
    public String saveUserAnswerPostDetails(Answers answer,String answerId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Answer_Student_Post_Relationship (S_UserID,AnswerID,EPostID) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,answer.getUserId());
            preparedStatement.setString(2,answerId);
            preparedStatement.setString(3,answer.getQuestionId());

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
        return "Done";

    }
    public String getAnswerId(Answers answers){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String answerId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT AnswerID FROM Answer_Student_Post_Relationship WHERE S_UserID = ? AND EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,answers.getUserId());
            preparedStatement.setString(2,answers.getQuestionId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                answerId = resultSet.getString("AnswerID");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("AnswerID in AnsPostStuDAO : "+answerId);
        return answerId;
    }
}
