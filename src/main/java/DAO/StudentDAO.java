package DAO;

import Database.DBConnectionPool;
import Model.Student;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    public void enterStudent(Student student){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Student (UserID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getUserId());
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

    public JSONObject getSchoolAndGrade(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT School, GRADE FROM Student WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                jsonObject.put("School",resultSet.getString("School"));

                jsonObject.put("Grade",resultSet.getString("GRADE"));


            }


            resultSet.close();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonObject;

    }


}
