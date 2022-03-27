package DAO;

import Database.DBConnectionPool;

import Model.Admin;
import Model.Student;
import Model.Teacher;
import Model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


    public Admin count(Admin admin) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher = 0;
        int countTeacher = 0;
        int countStudent = 0;
        int todaycountStudent = 0;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
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
            admin.setCountTeacher(countTeacher);
            System.out.println(countTeacher);
            admin.setTodaycountTeacher(todaycountTeacher);
            System.out.println(todaycountTeacher);
            admin.setCountStudent(countStudent);
            System.out.println(countStudent);
            admin.setTodaycountStudent(todaycountStudent);
            System.out.println(todaycountStudent);

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

        return admin;
    }

    public ArrayList<User> searchUser(String searchValue, String searchType, String myUserId) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> teacherArrayList = new ArrayList<>();
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE (FirstName LIKE ? OR LastName LIKE ?) AND ( UserType = ? AND UserID <> ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchValue + "%");
            preparedStatement.setString(2, "%" + searchValue + "%");
            preparedStatement.setString(3, searchType);
            preparedStatement.setString(4, myUserId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String userId = resultSet.getString("UserID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                System.out.println(userId);
                User user = new User(userId, firstName, lastName);
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

    public String getTeacherFullName(String userId) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String fullName = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT FirstName, LastName FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                fullName = firstName + " " + lastName;
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
        return fullName;
    }


    public JSONArray getTeacherFollowersList(ArrayList<String> followsList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> teacherFollowsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < followsList.size(); i++) {
                String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE UserID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, followsList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("UserID",userID);
                    jsonObject.put("firstName",firstName);
                    jsonObject.put("lastName",lastName);
                    jsonArray.put(jsonObject);

                }

                resultSet.close();
                preparedStatement.close();
            }
            for(int i=0;i< teacherFollowsDetails.size();i++){
                System.out.println(teacherFollowsDetails.get(i).getUserId());
                System.out.println(teacherFollowsDetails.get(i).getFirstName());
                System.out.println(teacherFollowsDetails.get(i).getLastName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }


    public JSONArray getTeacherFriendsDetails(ArrayList<String> friendList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> teacherFriendsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < friendList.size(); i++) {
                String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE UserID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, friendList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("UserID",userID);
                    jsonObject.put("firstName",firstName);
                    jsonObject.put("lastName",lastName);
                    jsonArray.put(jsonObject);

                }

                resultSet.close();
                preparedStatement.close();
            }
            for(int i=0;i< teacherFriendsDetails.size();i++){
                System.out.println(teacherFriendsDetails.get(i).getUserId());
                System.out.println(teacherFriendsDetails.get(i).getFirstName());
                System.out.println(teacherFriendsDetails.get(i).getLastName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }
    public JSONArray getStudentFriendsDetails(ArrayList<String> friendList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> studentFriendsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < friendList.size(); i++) {
                String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE UserID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, friendList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    JSONObject jsonObject = new JSONObject();

                    /*User user = new User(userID, firstName, lastName);
                    studentFriendsDetails.add(user);*/

                    jsonObject.put("UserID",userID);
                    jsonObject.put("firstName",firstName);
                    jsonObject.put("lastName",lastName);
                    jsonArray.put(jsonObject);

                }

                resultSet.close();
                preparedStatement.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }


    public JSONArray getStudentFollowersDetails(ArrayList<String> followersList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> studentFriendsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < followersList.size(); i++) {
                String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE UserID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, followersList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("UserID",userID);
                    jsonObject.put("firstName",firstName);
                    jsonObject.put("lastName",lastName);
                    jsonArray.put(jsonObject);

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }


    public JSONArray getNFownerDetails(ArrayList<String> NFTeacherIDList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsOwnerDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < NFTeacherIDList.size(); i++) {
                String sql = "SELECT FirstName, LastName,UserID FROM Users WHERE UserID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, NFTeacherIDList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String userID = resultSet.getString("UserID");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("UserID",userID);
                    jsonObject.put("firstName",firstName);
                    jsonObject.put("lastName",lastName);
                    jsonArray.put(jsonObject);

                }

                resultSet.close();
                preparedStatement.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }


    public String getOwnerName(String userID) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String fullName = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT FirstName, LastName FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                fullName = firstName + " " + lastName;

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

        return fullName;
    }

    public JSONObject getFirstnameLastName(String userID) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT FirstName, LastName FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");

                String lastName = resultSet.getString("LastName");

                jsonObject.put("NotifierFirstName",firstName);

                jsonObject.put("NotifierLastName",lastName);

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

        return jsonObject;
    }

    public JSONObject getStudentDetails(String userId) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT FirstName, LastName, ProfilePic, DOB, MobileNum, Country, City  FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();

                String firstName = resultSet.getString("FirstName");

                String lastName = resultSet.getString("LastName");

                jsonObject.put("FirstName",firstName);

                jsonObject.put("LastName",lastName);

                jsonObject.put("DOB",resultSet.getString("DOB"));

                jsonObject.put("MobileNum",resultSet.getString("MobileNum"));

                jsonObject.put("Country", resultSet.getString("Country"));

                jsonObject.put("City",resultSet.getString("City"));

                jsonObject.put("SchoolAndGrade",student.getSchoolAndGrade(userId));

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
        System.out.println("In DAO"+jsonObject);

        return jsonObject;
    }

    public JSONObject getTeacherDetails(String userId) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT FirstName, LastName, ProfilePic, Country, City  FROM Users WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TeacherDAO teacherDAO = new TeacherDAO();

                String firstName = resultSet.getString("FirstName");

                String lastName = resultSet.getString("LastName");

                jsonObject.put("FirstName",firstName);

                jsonObject.put("LastName",lastName);

                jsonObject.put("Country", resultSet.getString("Country"));

                jsonObject.put("City",resultSet.getString("City"));

                jsonObject.put("WorkingPlace",teacherDAO.getTeacherWorkingPlace(userId));

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

        return jsonObject;
    }




}


