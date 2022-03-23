package DAO;

import Database.DBConnectionPool;
import Model.Classroom;
import Model.Requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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

            resultSet.close();
            preparedStatement.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return requestsList;

    }

    public void insert(Requests requests , LocalDate date , LocalTime time ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Friend_Request VALUES( ? , ? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , requests.getFromId() );
            preparedStatement.setString( 2 , requests.getToId() );
            preparedStatement.setString( 3  , time.toString() );
            preparedStatement.setString( 4 , date.toString() );
            preparedStatement.executeUpdate();

            preparedStatement.close();

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

    public void acceptFriendRequest( String fromId , String toId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        PreparedStatement preparedStatement1 = null;

        PreparedStatement preparedStatement2 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "DELETE FROM Friend_Request WHERE From_UserID = ? AND To_UserID = ?";
            preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString( 1 , fromId );
            preparedStatement.setString( 2 , toId );


            int x = preparedStatement.executeUpdate();

            if ( x == 0 ){

                connection.rollback();

            }else {

                String sql1 = "INSERT INTO Add_Friends VALUES( ? , ? );";
                preparedStatement1 = connection.prepareStatement( sql1 );

                preparedStatement1.setString( 1 , toId );
                preparedStatement1.setString( 2 , fromId );

                int y = preparedStatement1.executeUpdate();
                if( y == 0 ){

                    connection.rollback();

                }else {

                    preparedStatement2 = connection.prepareStatement( sql1 );
                    preparedStatement2.setString( 1 , fromId );
                    preparedStatement2.setString( 2 , toId );

                    int z = preparedStatement2.executeUpdate();
                    if ( z==0 ){

                        connection.rollback();

                    }else {

                        connection.commit();


                    }

                }

            }

        }catch ( SQLException e ){

            e.printStackTrace();
            try{

                if ( connection != null )connection.rollback();

            }catch ( SQLException E ){

                E.printStackTrace();

            }

        }finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();

                if ( connection != null )connection.close();

            }catch ( SQLException E ){

                E.printStackTrace();

            }

        }

    }

}


