package DAO;

import Database.DBConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO {
    public ArrayList<String> getNewsFeedsTeacherKeys(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> NFTeacherKeyList = new ArrayList<String>();
        
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT NFPostID FROM Posts WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                NFTeacherKeyList.add(resultSet.getString("NFPostID"));
            }
            System.out.println(NFTeacherKeyList+"*************");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return NFTeacherKeyList;
    }



}
