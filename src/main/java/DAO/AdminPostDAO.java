package DAO;

import Database.DBConnectionPool;
import Model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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


    public String insert2(AdminWork adminWork){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Admin_Post_System_Updates (APTextMsg,APDate,APTime,APPhoto) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(adminWork.getTextMsg()));
            preparedStatement.setString(2, String.valueOf(adminWork.getDate()));
            preparedStatement.setString(3, String.valueOf(adminWork.getTime()));
            preparedStatement.setString(4, String.valueOf(adminWork.getPhoto()));


            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                return resultSet.getString(1);

            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return null;
    }

    public JSONArray getAPostDetails() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        //ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "select  SysPostID,APTextMsg,APPhoto,APTime, APDate from Admin_Post_System_Updates";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String ApostId = resultSet.getString("SysPostID");
                String textmsg = resultSet.getString("APTextMsg");
                String date = resultSet.getString("APDate");
                String time = resultSet.getString("APTime");
                String status = resultSet.getString("APPhoto");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("aPId",ApostId);
                jsonObject.put("caption",textmsg);
                jsonObject.put("date",date);
                jsonObject.put("time",time);
                jsonObject.put("status",status);

                jsonArray.put(jsonObject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
        return jsonArray;
    }

}
