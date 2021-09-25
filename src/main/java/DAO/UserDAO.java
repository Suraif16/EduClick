package DAO;

import Database.DBConnectionPool;
import Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserDAO {
    private String userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String profilePicture;
    private String country;
    private String city;
    private LocalTime registrationTime;
    private LocalDate registrationDate;
    private String gender;

    public void insert(User user){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Users (FirstName,Lastname,DOB,MobileNum,UserType,Country,City,registrationDate,registrationTime) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3, String.valueOf(Date.valueOf(user.getDateOfBirth())));
            preparedStatement.setString(4,user.getMobileNumber());
            preparedStatement.setString(5,user.getUserType());
            preparedStatement.setString(6,user.getCountry());
            preparedStatement.setString(7,user.getCity());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (resultSet.next()){
                generatedKey = resultSet.getInt(1);
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
