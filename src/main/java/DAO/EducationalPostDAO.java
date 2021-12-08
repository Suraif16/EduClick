package DAO;

import Database.DBConnectionPool;
import Model.EducationalWork;

import java.sql.*;

public class EducationalPostDAO {

    public String insert( EducationalWork educationalWork , String EPType ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO EducationalPost( DATE , TIME , EPtype , Type ) VALUES( ? , ? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , String.valueOf( educationalWork.getDate() ) );
            preparedStatement.setString( 2 , String.valueOf( educationalWork.getTime() ) );
            preparedStatement.setString( 3 , EPType );
            preparedStatement.setString( 4 , educationalWork.getType() );
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                return resultSet.getString( 1 );

            }

            resultSet.close();
            preparedStatement.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return null;
    }

}
