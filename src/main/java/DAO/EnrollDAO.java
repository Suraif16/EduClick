package DAO;

import Database.DBConnectionPool;
import Model.Requests;

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

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return arrayList;
    }
    public String getUserIdFromClass(String id){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String userID = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT UserID FROM Enroll WHERE ClassroomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                userID =  resultSet.getString("UserID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return userID;
    }

    public String checkEnableOrDisable(String userId,String classroomId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String status = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT Status FROM Enroll WHERE UserID = ? AND ClassroomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2,classroomId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                status =  resultSet.getString("Status");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return status;
    }

    public boolean acceptClassroomEnrollRequest( Requests requests ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        boolean enrollStatus = false;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "DELETE FROM Enroll_Request WHERE From_UserID = ? AND To_ClassroomID = ?";
            preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString( 1 , requests.getFromId() );
            preparedStatement.setString( 2 , requests.getToId() );

            int deleteRowCount = preparedStatement.executeUpdate();
            System.out.println( deleteRowCount );
            if ( deleteRowCount == 1 ){

                String sql1 = "INSERT INTO Enroll VALUES( ? , ? , ? )";
                preparedStatement1 = connection.prepareStatement( sql1 );

                preparedStatement1.setString( 1 , requests.getFromId() );
                preparedStatement1.setString( 2 , requests.getToId() );
                preparedStatement1.setString( 3 , "Enable");

                preparedStatement1.executeUpdate();

                connection.commit();
                enrollStatus = true;

            }else {

                connection.rollback();

            }



        }catch ( SQLException E ){

            try {

                if ( connection != null )connection.rollback();

            }catch ( SQLException ex ){

                ex.printStackTrace();

            }

        }finally {

            try{

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();

                if ( connection != null )connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }

        }

        return enrollStatus;

    }
}
