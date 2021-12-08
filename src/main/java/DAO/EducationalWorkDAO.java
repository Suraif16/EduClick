package DAO;

import Database.DBConnectionPool;
import Model.EducationalWork;

import java.sql.*;

public class EducationalWorkDAO {

    public String insert( EducationalWork educationalWork ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO EducationalWork( EPostID , Caption) VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , educationalWork.getPostID() );
            preparedStatement.setString( 2 , educationalWork.getCaption() );

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

        return  null;

    }

}
