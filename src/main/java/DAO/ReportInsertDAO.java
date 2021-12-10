package DAO;

import Database.DBConnectionPool;
import Model.AdminPost;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class ReportInsertDAO {


    public String generatedSysPostUserId;

    public String insert(AdminPost adminPost){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Admin_Post_System_Updates (APTextMsg,APDate,APTime) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, adminPost.getTextMsg());
            preparedStatement.setString(2, String.valueOf(adminPost.getDate()));
            preparedStatement.setString(3, String.valueOf(adminPost.getTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                generatedSysPostUserId = resultSet.getString(1);

            }
            //returns userID
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return generatedSysPostUserId;
    }

}
