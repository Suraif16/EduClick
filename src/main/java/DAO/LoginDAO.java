package DAO;

import Database.DBConnectionPool;

import java.sql.*;

public class LoginDAO {

    private String pswd;

    public void login(String email) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select Password from admin where EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1 , email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                pswd = resultSet.getString("Password");
            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public String getPswd(){
        return pswd;
    }


}
