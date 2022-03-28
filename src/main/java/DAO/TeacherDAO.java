package DAO;

import Database.DBConnectionPool;
import Model.Teacher;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    public void enterTeacher(Teacher teacher){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Teacher (UserID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getUserId());
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

    public JSONObject getTeacherWorkingPlace(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT CurrentWorkingPlace FROM Teacher WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                jsonObject.put("CurrentWorkingPlace",resultSet.getString("CurrentWorkingPlace"));


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
