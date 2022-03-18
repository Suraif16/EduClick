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

   public ArrayList<AdminDatacheck> searchUser(String searchValue, String searchType) {
       DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
       Connection connection = null;
       ArrayList<AdminDatacheck> teacherArrayList = new ArrayList<>();
       try {
           connection = dbConnectionPool.dataSource.getConnection();
           String sql = "SELECT UserID , FirstName, LastName, ProfilePic, DOB, MobileNum, UserType, Gender, Country, City , RegistrationDate , RegistrationTime FROM Users WHERE (FirstName LIKE ? OR LastName LIKE ?) AND ( UserType = ?)";

           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, "%" + searchValue + "%");
           preparedStatement.setString(2, "%" + searchValue + "%");
           preparedStatement.setString(3, searchType);

           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {

               String userId = resultSet.getString("UserID");
               String firstName = resultSet.getString("FirstName");
               String lastName = resultSet.getString("LastName");
               String dataOfBirth = resultSet.getString("DOB");
               String mobileNumber = resultSet.getString("MobileNum");
               String profilePicture = resultSet.getString("ProfilePic");
               String country = resultSet.getString("Country");
               String city = resultSet.getString("City");
               String gender = resultSet.getString("Gender");
               String dataOfReg = resultSet.getString("RegistrationDate");
               String timeOfReg = resultSet.getString("RegistrationTime");
               System.out.println(userId);
               AdminDatacheck user = new AdminDatacheck( userId,firstName, lastName,LocalDate.parse(dataOfBirth),mobileNumber,profilePicture,country,city,gender,LocalDate.parse(dataOfReg),LocalTime.parse(timeOfReg));
               teacherArrayList.add(user);
           }
           resultSet.close();
           preparedStatement.close();
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           if (connection != null) try {
               connection.close();
           } catch (Exception ignore) {
           }
       }
       return teacherArrayList;
   }
}


