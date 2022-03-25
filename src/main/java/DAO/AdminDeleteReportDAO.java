package DAO;

import Database.DBConnectionPool;
import Model.Report;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class AdminDeleteReportDAO {

    public String deleteRecord(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        System.out.println("delete report value in servlet" + userId);
        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql =  "DELETE FROM NewsFeeds WHERE NFPostID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return "Adminpost Deleted reported post";
    }
}
