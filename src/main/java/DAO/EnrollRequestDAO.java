package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollRequestDAO {
    public void insertRecord(String classrooomId,String userId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Enroll_Request (From_UserID,To_ClassroomID) VALUES (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,classrooomId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

    public void deleteRecord(String classrooomId,String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "DELETE FROM Enroll_Request WHERE From_UserID = ? AND To_ClassroomID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,classrooomId);

            int result=preparedStatement.executeUpdate();
            if(result==0){
                deleteRecord(classrooomId,userId);
            }
            System.out.println("Successfully deleted");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }
}
