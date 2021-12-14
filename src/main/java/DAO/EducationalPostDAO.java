package DAO;

import Database.DBConnectionPool;
import Model.EducationalWork;
import org.json.JSONObject;

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
    public JSONObject getEPostDetails(String postId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM EducationalPost WHERE EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                jsonObject.put("EPostID",resultSet.getString("EPostID"));
                jsonObject.put("Date",resultSet.getString("Date"));
                jsonObject.put("Time",resultSet.getString("Time"));
                jsonObject.put("EPType",resultSet.getString("EPType"));
                jsonObject.put("Type",resultSet.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return jsonObject;
    }

    public EducationalWork insertEducationalWork( EducationalWork educationalWork , String EPType , String classroomId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String ePostId = null;

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try{
            System.out.println( 11 );
            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );
            String sql = "INSERT INTO EducationalPost( DATE , TIME , EPtype , Type ) VALUES( ? , ? , ? , ? )";
            preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , String.valueOf( educationalWork.getDate() ) );
            preparedStatement.setString( 2 , String.valueOf( educationalWork.getTime() ) );
            preparedStatement.setString( 3 , EPType );
            preparedStatement.setString( 4 , educationalWork.getType() );
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                ePostId = resultSet.getString( 1 );

            }

            if ( ePostId != null ){
                System.out.println( 12 );

                educationalWork.setPostID( ePostId );
                String imagePath = null;

                String sql2 = "INSERT INTO EducationalWork( EPostID , Caption) VALUES( ? , ? )";
                preparedStatement2 = connection.prepareStatement( sql2 , Statement.RETURN_GENERATED_KEYS );
                preparedStatement2.setString( 1 , educationalWork.getPostID() );
                preparedStatement2.setString( 2 , educationalWork.getCaption() );

                preparedStatement2.execute();
                resultSet2 = preparedStatement2.getGeneratedKeys();

                if ( resultSet2.next() ){

                    imagePath = resultSet2.getString( 1 );

                }
                System.out.println( "image path = " + imagePath + " here it is...");
                if ( imagePath != null ){
                    System.out.println( 13 );

                    educationalWork.setImagePath( imagePath );

                    String sql3 = "INSERT INTO Classroom_Has_EPost VALUES( ? , ? )";
                    preparedStatement3 = connection.prepareStatement( sql3 );
                    preparedStatement3.setString( 1 , classroomId );
                    preparedStatement3.setString( 2 , educationalWork.getPostID() );
                    preparedStatement3.execute();

                    connection.commit();
                    return educationalWork;

                }else {

                    connection.rollback();
                    return null;

                }

            }else{

                connection.rollback();
                return null;


            }



        }catch ( SQLException throwables ) {

            try {

                if ( connection != null )connection.rollback();

            }catch( SQLException e ){

                e.printStackTrace();

            }

            throwables.printStackTrace();
        }
        finally {

            try{

                if ( resultSet != null ) resultSet.close();
                if ( resultSet2 != null ) resultSet2.close();

                if ( preparedStatement != null ) preparedStatement.close();
                if ( preparedStatement2 != null ) preparedStatement2.close();
                if ( preparedStatement3 != null ) preparedStatement3.close();

                if ( connection != null ) connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }

        }

        return null;

    }

}
