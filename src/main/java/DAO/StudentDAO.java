package DAO;

import Database.DBConnectionPool;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }


}
