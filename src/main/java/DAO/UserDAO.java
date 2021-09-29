package DAO;

import Database.DBConnectionPool;
import Model.User;

import java.sql.*;

public class UserDAO {

    public String generatedUserId;

    public String getGeneratedUserId() {
        return generatedUserId;
    }

    public void setGeneratedUserId(String generatedUserId) {
        this.generatedUserId = generatedUserId;
    }

    public String insert(User user){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Users (FirstName,Lastname,DOB,MobileNum,UserType,Gender,Country,City,RegistrationDate,RegistrationTime) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3, String.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(4,user.getMobileNumber());
            preparedStatement.setString(5,user.getUserType());
            preparedStatement.setString(6,user.getGender());
            preparedStatement.setString(7,user.getCountry());
            preparedStatement.setString(8,user.getCity());
            preparedStatement.setString(9, String.valueOf(user.getRegistrationDate()));
            preparedStatement.setString(10, String.valueOf(user.getRegistrationTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                generatedUserId = resultSet.getString(1);

            }
            //returns userID
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return generatedUserId;
    }

    public String select(String userid) {
        /*Here the login table from the database is accessed to check if the password is correct,
         * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String userType = "";
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select UserType from Users where UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userType = resultSet.getString("UserType");
                System.out.println("hi" + userType + "id:" + userid + " nothing");
            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return userType;
    }

}
