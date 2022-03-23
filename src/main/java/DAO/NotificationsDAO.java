package DAO;

import Database.DBConnectionPool;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class NotificationsDAO {

    public void insertNotifications(String notifierId,String notifieeId,String ePostId,String param){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Notifications (NotifierID,NotifieeID,ContentID,PostedType,Notification_Date,Notification_Time) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,notifierId);
            preparedStatement.setString(2,notifieeId);
            preparedStatement.setString(3,ePostId);
            preparedStatement.setString(4,param);
            preparedStatement.setString(5, String.valueOf(LocalDate.now()));
            preparedStatement.setString(6, String.valueOf(LocalTime.now()));

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
    }

    public void insertNotificationsFromTeacher(String teacherId, ArrayList<String> studentList, String ePostId, String param){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for(int i=0;i<studentList.size();i++){
                String sql = "INSERT INTO Notifications (NotifierID,NotifieeID,ContentID,PostedType,Notification_Date,Notification_Time) VALUES (?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString(1,teacherId);
                preparedStatement.setString(2,studentList.get(i));
                preparedStatement.setString(3,ePostId);
                preparedStatement.setString(4,param);
                preparedStatement.setString(5, String.valueOf(LocalDate.now()));
                preparedStatement.setString(6, String.valueOf(LocalTime.now()));
                preparedStatement.executeUpdate();
                preparedStatement.close();

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
    }

    public JSONArray getNoitifications(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT NotifierID,ContentID,PostedType,Notification_Date,Notification_Time FROM Notifications WHERE NotifieeID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                User user = new User();

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("NotifierID",resultSet.getString("NotifierID"));

                jsonObject.put("NotifierDetails",user.getFirstnameLastName(resultSet.getString("NotifierID")));

                jsonObject.put("ContentID",resultSet.getString("ContentID"));

                jsonObject.put("PostedType",resultSet.getString("PostedType"));

                jsonObject.put("Notification_Date",resultSet.getString("Notification_Date"));

                jsonObject.put("Notification_Time",resultSet.getString("Notification_Time"));

                jsonArray.put(jsonObject);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return jsonArray;

    }


}
