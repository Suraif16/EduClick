package DAO;

import Database.DBConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import Model.Login;
import Model.User;
import DAO.UserDAO;
import org.json.JSONObject;

public class LoginDAO {




    public JSONObject select(String email) {
        /*Here the login table from the database is accessed to check if the password is correct,
        * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "pswd" , "" );
        jsonObject.put( "userid" , "" );

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select Password,UserID,EmailConfirmation,PasswordIncorrect from Login where EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1 , email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String pswd = resultSet.getString("Password");
                String userid = resultSet.getString("UserID");
                String emailConfirmation = resultSet.getString("EmailConfirmation");
                String passwordIncorrect = resultSet.getString( "PasswordIncorrect" );
                jsonObject.put( "pswd" , pswd );
                jsonObject.put( "userid" , userid );
                jsonObject.put( "emailConfirmation" , emailConfirmation );
                jsonObject.put( "passwordIncorrect" , passwordIncorrect );

            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return jsonObject;
    }

    public static void enter(Login login){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection =dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Login(EmailID,Password,SaltingKey,LoginDate,LoginTime,UserID,EmailConfirmation,PasswordIncorrect) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,login.getEmail());
            preparedStatement.setString(2,login.getPassword());
            preparedStatement.setString( 3 , login.getSaltingKey() );
            preparedStatement.setString(4, String.valueOf(login.getLoginDate()));
            preparedStatement.setString(5, String.valueOf(login.getLoginTime()));
            preparedStatement.setString(6, login.getUserID());
            preparedStatement.setString( 7 , login.getEmailConfirmation() );
            preparedStatement.setString( 8 , login.getPasswordIncorrect() );
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }



    }

    public String validateEmail(String email){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String emailId = "";

        try {
            connection =dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT userID FROM Login WHERE EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                emailId = resultSet.getString("userID");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return emailId;
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
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }



    public String selectSaltingKey( String email ){
        /* Here we extract the salting key from the database and return it, if the email is not found then "" will be returned as a sign of
        * no result found*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String saltingKey = "";

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT SaltingKey FROM Login WHERE EmailId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , email );
            ResultSet resultSet = preparedStatement.executeQuery();

            if ( resultSet.next() ){

                saltingKey = resultSet.getString( "SaltingKey" );

            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }

        return saltingKey;

    }

    public void updateValueStatus ( Login login , String columnName ) {
        /*Here the login table from the database is accessed to check if the password is correct,
         * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String value = "";

        if ( columnName.equals( "EmailConfirmation" ) ){

            value = login.getEmailConfirmation();

        }else if ( columnName.equals( "PasswordIncorrect" ) ){

            value = login.getPasswordIncorrect();

        }

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "update Login set " + columnName + " = ? where EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1 , value );
            preparedStatement.setString(2 , login.getEmail() );
            int rowAffected = preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

}
