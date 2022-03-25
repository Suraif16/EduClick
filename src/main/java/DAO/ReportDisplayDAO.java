package DAO;

import Database.DBConnectionPool;
import Model.Report;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReportDisplayDAO {

    public JSONArray getAdminNewsfeedDetails(  ){


        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        JSONArray jsonArray = new JSONArray();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "select Count,NF_postID FROM Report";
            preparedStatement = connection.prepareStatement( sql );


            String sql1 = "SELECT Date, Time, Caption,ImageStatus FROM NewsFeeds WHERE NFPostID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT ImagePath FROM News_Feed_Image WHERE NFPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            resultSet = preparedStatement.executeQuery();
            System.out.println("dao");

            while( resultSet.next() ){
                JSONObject jsonObject = new JSONObject();

                String Count = resultSet.getString("Count");
                String NF_postID = resultSet.getString("NF_postID");
                jsonObject.put("count",Count);
                jsonObject.put("nFPostID",NF_postID);

                preparedStatement1.setString( 1 , NF_postID );
                resultSet1 = preparedStatement1.executeQuery();

                preparedStatement2.setString( 1 , NF_postID );
                resultSet2 = preparedStatement2.executeQuery();

                if ( resultSet1.next() ){
                    String Date = resultSet1.getString("Date");
                    String Time = resultSet1.getString("Time");
                    String Caption = resultSet1.getString("Caption");
                    String ImageStatus = resultSet1.getString("ImageStatus");
                    System.out.println("ImagePath "+ImageStatus);

                    jsonObject.put("date",Date);
                    jsonObject.put("time",Time);
                    jsonObject.put("caption",Caption);
                    jsonObject.put("imageStatus",ImageStatus);
                    System.out.println("ImageStatus "+ImageStatus);
                }

                if ( resultSet2.next() ){
                    String ImagePath = resultSet2.getString("ImagePath");
                    System.out.println("ImagePath "+ImagePath);

                    jsonObject.put("imagePath",ImagePath);
                }

                jsonArray.put( jsonObject );
            }

            connection.commit();
        } catch (SQLException throwables) {
            try{
                if ( connection != null )connection.rollback();
            }catch ( SQLException E ){
                E.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            try{
                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();
                if ( resultSet2 != null )resultSet2.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();

                if ( connection != null )connection.close();
            }catch ( SQLException E ){ E.printStackTrace(); }
        }
        return jsonArray;
    }
    public JSONArray getAdminEducationalPostDetails(  ){


        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        JSONArray jsonArray = new JSONArray();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "select Count,EpostID FROM Report";
            preparedStatement = connection.prepareStatement( sql );


            String sql1 = "SELECT Date, Time FROM Educationalpost WHERE EPostID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT ImageStatus,Caption FROM Educationalwork WHERE EPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            resultSet = preparedStatement.executeQuery();
            System.out.println("dao");

            while( resultSet.next() ){
                JSONObject jsonObject = new JSONObject();

                String Count = resultSet.getString("Count");
                String EpostID = resultSet.getString("EpostID");
                jsonObject.put("count",Count);
                jsonObject.put("epostID",EpostID);

                preparedStatement1.setString( 1 , EpostID );
                resultSet1 = preparedStatement1.executeQuery();

                preparedStatement2.setString( 1 , EpostID );
                resultSet2 = preparedStatement2.executeQuery();

                if ( resultSet1.next() ){
                    String Date = resultSet1.getString("Date");
                    String Time = resultSet1.getString("Time");

                    jsonObject.put("date",Date);
                    jsonObject.put("time",Time);

                    System.out.println("Date "+Date);
                }

                if ( resultSet2.next() ){
                    String Caption = resultSet1.getString("Caption");
                    String ImageStatus = resultSet1.getString("ImageStatus");
                    System.out.println("ImagePath "+ImageStatus);

                    jsonObject.put("caption",Caption);
                    jsonObject.put("imageStatus",ImageStatus);
                }

                jsonArray.put( jsonObject );
            }

            connection.commit();
        } catch (SQLException throwables) {
            try{
                if ( connection != null )connection.rollback();
            }catch ( SQLException E ){
                E.printStackTrace();
            }
            throwables.printStackTrace();
        }finally {
            try{
                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();
                if ( resultSet2 != null )resultSet2.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();

                if ( connection != null )connection.close();
            }catch ( SQLException E ){ E.printStackTrace(); }
        }
        return jsonArray;
    }

}


