package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDeleteReportEducationalpostDAO {

    public String deleteeducationalpostRecord(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        System.out.println("delete report value in servlet" + userId);
        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql =  "DELETE FROM EducationalPost WHERE EPostID = ? ";
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
