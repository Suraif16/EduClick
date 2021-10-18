package DAO;

import Database.DBConnectionPool;
import Model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

    public void searchTeacher(String teacherName){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT FROM Teacher WHERE () VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getUserId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

}
