package DAO;

import Database.DBConnectionPool;
import Model.Answers;

import java.sql.*;

public class AnswerDAO {
    public String saveAnswers(Answers answers){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedAnswerId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Answer (Date,Time) VALUES (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(answers.getDate()));
            preparedStatement.setString(2, String.valueOf(answers.getTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedAnswerId = resultSet.getString(1);

            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("Generated AnswerID is : "+generatedAnswerId);

        return generatedAnswerId;
    }
}
