package DAO;

import Database.DBConnectionPool;

import java.sql.*;

public class LoginDAO {

    private String pswd;
    private String userid;

    public void select(String email) {
        /*Here the login table from the database is accessed to check if the password is correct,
        * if the admin logs in then the userid is set to "", otherwise to a user id*/
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
                userid = "";
                System.out.println("hi" + pswd +"id:" + userid + " nothing");
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

    public String getUserid() {
        return userid;
    }

}
