package DAO;

import Database.DBConnectionPool;
import Model.Classroom;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class BellIconDAO {

    public void insertRecord(String userId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String generatedKey = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "INSERT INTO Bell_Icon(UserID,Click_Date,Click_Time) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString( 1 , userId );
            preparedStatement.setString( 2 , String.valueOf(LocalDate.now()));
            preparedStatement.setString( 3 , String.valueOf(LocalTime.now()));

            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public void updateRecord(String userId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "UPDATE Bell_Icon SET Click_Date = ?,Click_Time = ? WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setDate(1 , Date.valueOf(LocalDate.now()));
            preparedStatement.setTime(2 , Time.valueOf(LocalTime.now()));
            preparedStatement.setString(3 , userId);
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

}
