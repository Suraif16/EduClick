package DAO;

import Database.DBConnectionPool;
import Model.AdminPost;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AdminPostDAO {


    public String generatedSysPostUserId;

    public String getGeneratedSysPostUserId() {
        return generatedSysPostUserId;
    }

    public void setGeneratedSysPostUserId(String generatedSysPostUserId) {
        this.generatedSysPostUserId = generatedSysPostUserId;
    }

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

    public AdminPost select(AdminPost adminPost) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select APTextMsg, APPhoto, APTime, APDate from Admin_Post_System_Updates where SysPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adminPost.getSysPostID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String textmsg = resultSet.getString("APTextMsg");
                String photo = resultSet.getString("APPhoto");
                String time = resultSet.getString("APTime");
                String date = resultSet.getString("APDate");

                adminPost.setTextMsg(textmsg);
                adminPost.setPhoto(photo);
                adminPost.setDate(LocalDate.parse(time));
                adminPost.setTime(LocalTime.parse(date));

            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return adminPost;
    }

}
