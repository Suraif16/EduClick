package DAO;

import Database.DBConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import Model.Login;
import DAO.UserDAO;

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
        System.out.println("first");
        if (pswd == null){
            System.out.println("userlogin");
            try {
                connection = dbConnectionPool.dataSource.getConnection();
                String sql = "select Password,UserID from Login where EmailID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString(1 , email);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    pswd = resultSet.getString("Password");
                    userid = resultSet.getString("UserID");;
                    System.out.println(" A " + pswd +"id:" + userid + " B ");
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
        System.out.println("last");
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

    public String getPswd(){
        return pswd;
    }

    public String getUserid() {
        return userid;
    }
    public void update (String email , LocalDate loginDate , LocalTime loginTime) {
        /*Here the login table from the database is accessed to check if the password is correct,
         * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "update Login set LoginDate = ?,LoginTime = ? where EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setDate(1 , Date.valueOf(loginDate));
            preparedStatement.setTime(2 , Time.valueOf(loginTime));
            preparedStatement.setString(3 , email);
            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected);
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }
}
