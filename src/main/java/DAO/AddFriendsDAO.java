package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddFriendsDAO {

    public Boolean checkIsFriend( String userId , String friendUserId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM Add_Friends WHERE UserID = ? AND Friend_UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , userId );
            preparedStatement.setString( 2 , friendUserId );
            ResultSet resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ){
                return true;
            }

            resultSet.close();
            preparedStatement.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return false;

    }

    public ArrayList<String> getTeacherFriendKeys(String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> friendList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            System.out.println("kkk");
            String sql = "SELECT Friend_UserID FROM add_friends WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                friendList.add(resultSet.getString("Friend_UserID"));
            }
            System.out.println(friendList+"*************");

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }
        return friendList;
    }



}
