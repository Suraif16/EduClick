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

public class EnrollRequestDAO {
    public void insertRecord(String classrooomId,String userId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Enroll_Request (From_UserID,To_ClassroomID) VALUES (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,classrooomId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
    }

    public void deleteRecord(String classrooomId,String userId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "DELETE FROM Enroll_Request WHERE From_UserID = ? AND To_ClassroomID = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );

            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,classrooomId);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public ArrayList< Requests > selectAll( Classroom classroom ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        ArrayList< Requests > requestsList = new ArrayList<>();

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT From_UserID FROM Enroll_Request WHERE To_ClassroomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , classroom.getClassroomID() );
            ResultSet resultSet = preparedStatement.executeQuery();

            while ( resultSet.next() ){

                String fromId = resultSet.getString( "From_UserID" );
                String classroomDescription = "wants to join to " + classroom.getClassroomName() +
                        " : " + classroom.getSubject() + " : grade " + classroom.getGrade() + " : " +
                        classroom.getYear();
                Requests requests = new Requests( fromId , classroom.getClassroomID() , "Enroll" , classroomDescription );
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
}