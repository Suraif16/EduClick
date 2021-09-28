package DAO;

import Database.DBConnectionPool;

import java.sql.*;

import Model.Login;
import DAO.UserDAO;

public class LoginDAO {

    private String pswd;
    private String userid;
    private String emaildao;


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

    public static void enter(Login login){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection =dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Login(EmailID,Password,LoginDate,LoginTime,UserID) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,login.getEmail());
            preparedStatement.setString(2,login.getPassword());
            preparedStatement.setString(3, String.valueOf(login.getLoginDate()));
            preparedStatement.setString(4, String.valueOf(login.getLoginTime()));
            preparedStatement.setString(5, login.getUserID());
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }


    }

    public void validateEmail(String email){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection =dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT userID FROM Login WHERE EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                emaildao = resultSet.getString("userID");
                System.out.println("Email DAO printed" + emaildao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

    public String getEmailDAO() {
        return emaildao;
    }

}
