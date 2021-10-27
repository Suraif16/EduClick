package DAO;

import Database.DBConnectionPool;

import Model.Teacher;
import Model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDAO<teacherArrayList> {

    /*ArrayList<String> arrayList = new ArrayList<String>();

    public ArrayList<String> getArrayList() {
        return arrayList;
    }*/

    public String generatedUserId;

    public String getGeneratedUserId() {
        return generatedUserId;
    }

    public void setGeneratedUserId(String generatedUserId) {
        this.generatedUserId = generatedUserId;
    }

    private int countTeacher;
    private int todaycountTeacher;
    private int countStudent;
    private int todaycountStudent;

    public int getCountTeacher() {
        return countTeacher;
    }

    public void setCountTeacher(int countTeacher) {
        this.countTeacher = countTeacher;
    }

    public int getTodaycountTeacher() {
        return todaycountTeacher;
    }

    public void setTodaycountTeacher(int todaycountTeacher) {
        this.todaycountTeacher = todaycountTeacher;
    }

    public int getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }

    public int getTodaycountStudent() {
        return todaycountStudent;
    }

    public void setTodaycountStudent(int todaycountStudent) {
        this.todaycountStudent = todaycountStudent;
    }

    public String insert(User user) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Users (FirstName,Lastname,DOB,MobileNum,UserType,Gender,Country,City,RegistrationDate,RegistrationTime) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, String.valueOf(user.getDateOfBirth()));
            preparedStatement.setString(4, user.getMobileNumber());
            preparedStatement.setString(5, user.getUserType());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, String.valueOf(user.getRegistrationDate()));
            preparedStatement.setString(10, String.valueOf(user.getRegistrationTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedUserId = resultSet.getString(1);

            }
            //returns userID
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

    /*public int countTeacher() {

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
    }*/


    public void count() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher = 0;
        int countTeacher = 0;
        int countStudent = 0;
        int todaycountStudent = 0;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : "+strDate);
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select UserType,RegistrationDate FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Usertype = resultSet.getString("UserType");
                String Registrationdate = resultSet.getString("RegistrationDate");

                if (Usertype.equals("Teacher")) {
                    countTeacher++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountTeacher++;
                    }
                } else {
                    countStudent++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountStudent++;
                    }
                }
            }

            //Admin admin = new Admin(countTeacher,todaycountTeacher,countStudent,todaycountStudent);
            //admin.setCountStudent(countTeacher);
            setCountTeacher(countTeacher);
            System.out.println(countTeacher);
            setTodaycountTeacher(todaycountTeacher);
            System.out.println(todaycountTeacher);
            setCountStudent(countStudent);
            System.out.println(countStudent);
            setTodaycountStudent(todaycountStudent);
            System.out.println(todaycountStudent);


            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }


    }

    public ArrayList< User > searchTeacher( String teacherName , String myUserId ) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList< User > teacherArrayList = new ArrayList<>();
        System.out.println("useridmine" + myUserId);
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE (FirstName LIKE ? OR LastName LIKE ?) AND ( UserType = 'Teacher' AND UserID <> ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString( 1 , "%" + teacherName + "%" );
            preparedStatement.setString( 2 , "%" + teacherName + "%" );
            preparedStatement.setString( 3 , myUserId );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String userId = resultSet.getString("UserID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                System.out.println( userId );
                User user = new User(userId, firstName, lastName);
                teacherArrayList.add( user );

            }


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

    public String getTeacherFullName(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String fullName = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT FirstName, LastName FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                fullName = firstName + " " + lastName;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        return fullName;
    }




}



    /*public ArrayList<String> checkEnrollment(String studentId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT ClassroomID FROM Enroll WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                arrayList.add(resultSet.getString("ClassroomID"));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return arrayList;
    }*/

