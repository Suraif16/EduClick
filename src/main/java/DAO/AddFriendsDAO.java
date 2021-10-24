package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return false;

    }


}
