package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

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

}
