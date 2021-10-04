package DAO;

import Database.DBConnectionPool;
import Model.Classroom;

import java.sql.*;

public class ClassroomDAO {

    public String insert( Classroom classroom ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String generatedKey = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Classroom(CR_Name , Year , Grade , Subject , UserID) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1 , classroom.getClassroomName() );
            preparedStatement.setString( 2 , classroom.getYear() );
            preparedStatement.setString( 3 , classroom.getGrade() );
            preparedStatement.setString( 4 , classroom.getSubject() );
            preparedStatement.setString( 5 , classroom.getUserId() );

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if( resultSet.next() ){

                generatedKey = resultSet.getString(1);

            }



        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return generatedKey;
    }

}
