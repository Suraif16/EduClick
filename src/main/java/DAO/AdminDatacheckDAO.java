package DAO;

import Database.DBConnectionPool;
import Model.AdminDatacheck;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class AdminDatacheckDAO {


    public AdminDatacheck select(AdminDatacheck user) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String userType = "";
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select UserID , FirstName, LastName, ProfilePic, DOB, MobileNum, UserType, Gender, Country, City , RegistrationDate , RegistrationTime from Users ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String userID = resultSet.getString("UserID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String dataOfBirth = resultSet.getString("DOB");
                String mobileNumber = resultSet.getString("MobileNum");
                String profilePicture = resultSet.getString("ProfilePic");
                String country = resultSet.getString("Country");
                String city = resultSet.getString("City");
                String gender = resultSet.getString("Gender");
                userType = resultSet.getString("UserType");
                String dataOfReg = resultSet.getString("RegistrationDate");
                String timeOfReg = resultSet.getString("RegistrationTime");

                user.setUserId(userID);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDateOfBirth(LocalDate.parse(dataOfBirth));
                user.setMobileNumber(mobileNumber);
                user.setProfilePicture(profilePicture);
                user.setCountry(country);
                user.setCity(city);
                user.setGender(gender);
                user.setUserType(userType);
                user.setRegistrationDate(LocalDate.parse(dataOfReg));
                user.setRegistrationTime(LocalTime.parse(timeOfReg));

            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        return user;
    }

}


