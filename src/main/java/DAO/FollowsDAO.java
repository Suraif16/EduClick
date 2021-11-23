package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FollowsDAO {

    public Boolean checkIsFollow( String userId , String friendUserId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM Follows WHERE S_UserID = ? AND T_UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , userId );
            preparedStatement.setString( 2 , friendUserId );
            ResultSet resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ){
                return true;
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return false;

    }

    public void insert( String S_UserId , String T_UserId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Follows VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , S_UserId );
            preparedStatement.setString( 2 , T_UserId );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public void delete( String S_UserId , String T_UserId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "DELETE FROM Follows WHERE S_UserID = ? AND T_UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , S_UserId );
            preparedStatement.setString( 2 , T_UserId );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }

    }

    public static ArrayList<String> getStudentFollowsKeys(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> followsList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT T_UserID FROM Follows WHERE S_UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                followsList.add(resultSet.getString("T_UserID"));
            }
            System.out.println(followsList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return followsList;
    }

}
