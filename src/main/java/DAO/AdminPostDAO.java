package DAO;

import Database.DBConnectionPool;
import Model.AdminPost;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPostDAO {


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

    public JSONArray getAPostDetails(ArrayList<String> APostIDList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < APostIDList.size(); i++) {
                String sql = "select  SysPostID,APTextMsg, APTime, APDate from Admin_Post_System_Updates where SysPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, APostIDList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String ApostId = resultSet.getString("SysPostID");
                    String textmsg = resultSet.getString("APTextMsg");
                    String date = resultSet.getString("APDate");
                    String time = resultSet.getString("APTime");

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("APId",ApostId);
                    jsonObject.put("Caption",textmsg);
                    jsonObject.put("Date",date);
                    jsonObject.put("Time",time);

                    jsonArray.put(jsonObject);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }

    public ArrayList<String> getSysPostIDkeys(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> APostIDList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select  * from Admin_Post_System_Updates WHERE SysPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                APostIDList.add(resultSet.getString("SysPostID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return APostIDList;
    }
}
