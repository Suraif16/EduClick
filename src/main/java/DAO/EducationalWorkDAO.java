package DAO;

import Database.DBConnectionPool;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EducationalWorkDAO {
    public JSONObject getEPostContent(String postId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM EducationalWork WHERE EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                jsonObject.put("ImagePath",resultSet.getString("ImagePath"));
                jsonObject.put("Caption",resultSet.getString("Caption"));
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return jsonObject;
    }

}
