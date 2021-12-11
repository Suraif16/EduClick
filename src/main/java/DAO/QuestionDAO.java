package DAO;

import Database.DBConnectionPool;
import Model.Mcq;

import java.sql.*;

public class QuestionDAO {

    public String insert( Mcq mcq , String epostId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Question VALUES( ? , ? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , mcq.getQuestionId() );
            preparedStatement.setString( 2 , mcq.getQuestion() );
            preparedStatement.setString( 3 , mcq.getCorrectAnswer() );
            preparedStatement.setString( 4 , epostId );

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                return resultSet.getString( 1 );

            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }

        return null;
    }

}
