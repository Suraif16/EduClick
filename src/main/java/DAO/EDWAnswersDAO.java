package DAO;

import Database.DBConnectionPool;
import Model.Answers;
import org.json.JSONObject;

import java.sql.*;

public class EDWAnswersDAO {
    public void insert(Answers answers,String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedImageId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO EDW_Answers(AnswerID,Content,ImageStatus) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,answerId);
            preparedStatement.setString(2,answers.getAnswer());
            preparedStatement.setString(3,answers.getImageStatus());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
    }
    public JSONObject getAnswerContent(String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT Content,ImageStatus FROM EDW_Answers WHERE AnswerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,answerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                jsonObject.put("Content",resultSet.getString("Content"));
                jsonObject.put("ImageStatus",resultSet.getString("ImageStatus"));

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
