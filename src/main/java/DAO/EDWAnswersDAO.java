package DAO;

import Database.DBConnectionPool;
import Model.Answers;
import org.json.JSONObject;

import java.sql.*;

public class EDWAnswersDAO {
    public String insert(Answers answers,String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedImageId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO EDW_Answers(AnswerID,Content) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,answerId);
            preparedStatement.setString(2,answers.getAnswer());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                generatedImageId = resultSet.getString( 1 );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("case eka nmekada? : "+generatedImageId);
        return generatedImageId;
    }
    public JSONObject getAnswerContent(String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT Content,ImagePath FROM EDW_Answers WHERE AnswerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,answerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                jsonObject.put("Content",resultSet.getString("Content"));
                jsonObject.put("ImagePath",resultSet.getString("ImagePath"));

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