package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnrollDAO {

    public ArrayList<String> checkEnrollment(String studentId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        ArrayList<String> arrayList = new ArrayList<String>();


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
    }


}