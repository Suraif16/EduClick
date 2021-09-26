package DAO;

import Database.DBConnectionPool;
import Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserDAO {

    String generatedUserId;

    public void insert(User user){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Users (FirstName,Lastname,DOB,MobileNum,UserType,Country,City,RegistrationDate,RegistrationTime) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3, String.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(4,user.getMobileNumber());
            preparedStatement.setString(5,user.getUserType());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setString(7,user.getCity());
            preparedStatement.setString(8, String.valueOf(user.getRegistrationDate()));
            preparedStatement.setString(9, String.valueOf(user.getRegistrationTime()));

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
    }
}
