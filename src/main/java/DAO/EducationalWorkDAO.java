package DAO;

import Database.DBConnectionPool;

import Model.EducationalWork;

import java.sql.*;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EducationalWorkDAO {
    public JSONObject getEPostContent(String postId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM EducationalWork WHERE EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                jsonObject.put("ImagePath",resultSet.getString("ImagePath"));
                jsonObject.put("Caption",resultSet.getString("Caption"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return jsonObject;
    }

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
