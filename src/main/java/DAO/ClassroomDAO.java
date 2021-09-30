package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassroomDAO {

    public void insert(){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Classroom(CR_Name , Year , Grade , Subject , UserID) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );


        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

}
