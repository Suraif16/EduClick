package DAO;

import Database.DBConnectionPool;

import Model.User;
import Model.Admin;

import java.sql.*;
import java.time.LocalDate;

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

    public User select(User user) {
        /*Here the login table from the database is accessed to check if the password is correct,
         * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String userType = "";
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select FirstName, LastName, ProfilePic, DOB, MobileNum, UserType, Gender, Country, City from Users where UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String dataOfBirth = resultSet.getString("DOB");
                String mobileNumber = resultSet.getString("MobileNum");
                String profilePicture = resultSet.getString("ProfilePic");
                String country = resultSet.getString("Country");
                String city = resultSet.getString("City");
                String gender = resultSet.getString("Gender");
                userType = resultSet.getString("UserType");

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setDateOfBirth(LocalDate.parse(dataOfBirth));
                user.setMobileNumber(mobileNumber);
                user.setProfilePicture(profilePicture);
                user.setCountry(country);
                user.setCity(city);
                user.setGender(gender);
                user.setUserType(userType);

                System.out.println("hi" + userType + "id:dfgsfd645");
            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return user;
    }

    public int countTeacher() {
        /*Here the login table from the database is accessed to check if the password is correct,
         * if the admin logs in then the userid is set to "", otherwise to a user id*/
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int count=0;
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select count(*) from Users where UserType = 'Teacher' ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
             count = resultSet.getInt(1);
            System.out.println( count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return count;
    }

    public int count ( ){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher=0;
        int countTeacher=0;
        int countStudent=0;
        int todaycountStudent=0;
        try {
            connection =dbConnectionPool.dataSource.getConnection();
            String sql = "select UserType,RegistrationDate FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String UT = resultSet.getString("UserType");
                String RD = resultSet.getString("RegistrationDate");
                if (UT.equals("Teacher") ) {
                    countTeacher++;
                    if (java.time.LocalTime.now().equals(RD)) {
                        todaycountTeacher++;
                    }
                } else {
                    countStudent++;
                    if (java.time.LocalTime.now().equals(RD)) {
                        todaycountStudent++;
                    }
                }
            }
            Admin.getcountteacher(countTeacher);
            Admin.gettodaycountteacher(todaycountTeacher);
            Admin.getcountstudent(countStudent);
            Admin.gettodaycountstudent(todaycountStudent);
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }


        return 0;
    }
}
