package DAO;

import Database.DBConnectionPool;
import Model.Mcq;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDAO {


    public ArrayList<String> selectCorrectAnswers(String postId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> correctAnswerList = new ArrayList<>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT Correct_answers FROM Question WHERE EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , postId );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                correctAnswerList.add(resultSet.getString("Correct_answers"));

            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return correctAnswerList;
    }

}
