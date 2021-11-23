package DAO;

import Database.DBConnectionPool;
import Model.Classroom;
import Model.Requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {

    public ArrayList< Requests > selectAll( String userId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        ArrayList< Requests > requestsList = new ArrayList<>();

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT From_UserID FROM Friend_Request WHERE To_UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , userId );
            ResultSet resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ){

                String fromId = resultSet.getString( "From_UserID" );
                String description = "want to be your friend";
                Requests requests = new Requests( fromId , userId , "Friend" , description );
                requestsList.add( requests );
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return requestsList;

    }

    public void insert( Requests requests ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Friend_Request VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , requests.getFromId() );
            preparedStatement.setString( 2 , requests.getToId() );
            preparedStatement.executeUpdate();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public Boolean checkIsRequested( String fromUserId , String toUserId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM Friend_Request WHERE From_UserID = ? AND To_UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , fromUserId );
            preparedStatement.setString( 2 , toUserId );
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

    public void delete( Requests requests ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "DELETE FROM Friend_Request WHERE From_UserID = ? AND To_UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , requests.getFromId() );
            preparedStatement.setString( 2 , requests.getToId() );
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }

    }

    public ArrayList<String> getStudentFriendKeys(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> friendList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT To_UserID FROM Friend_Request WHERE From_UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                friendList.add(resultSet.getString("To_UserID"));
            }
            System.out.println(friendList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return friendList;
    }

}


