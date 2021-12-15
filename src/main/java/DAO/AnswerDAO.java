package DAO;

import Database.DBConnectionPool;
import Model.Answers;
import org.json.JSONObject;

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

    public JSONObject selectAnswerDetails(String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM Answer WHERE AnswerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,answerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                jsonObject.put("AnswerID",resultSet.getString("AnswerID"));
                jsonObject.put("Date",resultSet.getString("Date"));
                jsonObject.put("Time",resultSet.getString("Time"));
                jsonObject.put("Marks",resultSet.getString("Marks"));
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
        return jsonObject;
    }
}
