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
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        ResultSet resultSet4 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "select Count,NF_postID FROM Report";
            preparedStatement = connection.prepareStatement( sql );


            String sql1 = "SELECT Date, Time, Caption,ImageStatus FROM NewsFeeds WHERE NFPostID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT ImagePath FROM News_Feed_Image WHERE NFPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            String sql3 = "SELECT T_UserID FROM Posts WHERE NFPostID = ?";
            preparedStatement3 = connection.prepareStatement( sql3);

            String sql4 = "SELECT FirstName,LastName,ProfilePic FROM Users WHERE UserID = ?";
            preparedStatement4 = connection.prepareStatement( sql4 );

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

                preparedStatement3.setString(1, NF_postID);
                resultSet3 = preparedStatement3.executeQuery();
                String UserID = null;
                if (resultSet3.next()) {
                    UserID = resultSet3.getString("T_UserID");

                    System.out.println("UserID " + UserID);
                }

                preparedStatement4.setString(1, UserID);
                resultSet4 = preparedStatement4.executeQuery();
                if (resultSet4.next()) {
                    String FirstName = resultSet4.getString("FirstName");
                    String LastName = resultSet4.getString("LastName");
                    String ProfilePic = resultSet4.getString("ProfilePic");
                    jsonObject.put("firstName", FirstName);
                    jsonObject.put("lastName", LastName);
                    jsonObject.put("profilePic", ProfilePic);
                    System.out.println("ProfilePic "+ ProfilePic);
                    System.out.println("FirstName " + FirstName);
                    System.out.println("LastName " + LastName);

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
                if ( resultSet3 != null )resultSet3.close();
                if ( resultSet4 != null )resultSet4.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();
                if ( preparedStatement4 != null )preparedStatement4.close();

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
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        ResultSet resultSet4 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "select Count,EpostID FROM Report";
            preparedStatement = connection.prepareStatement( sql );


            String sql1 = "SELECT Date, Time ,ClassroomID FROM EducationalPost WHERE EPostID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT ImageStatus,Caption FROM EducationalWork WHERE EPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            String sql3 = "SELECT UserID FROM Classroom WHERE ClassroomID = ?";
            preparedStatement3 = connection.prepareStatement( sql3 );

            String sql4 = "SELECT FirstName,LastName,ProfilePic FROM Users WHERE UserID = ?";
            preparedStatement4 = connection.prepareStatement( sql4 );

            resultSet = preparedStatement.executeQuery();
            System.out.println("dao");

            while( resultSet.next() ) {
                JSONObject jsonObject = new JSONObject();

                String Count = resultSet.getString("Count");
                String EpostID = resultSet.getString("EpostID");
                jsonObject.put("count", Count);
                jsonObject.put("epostID", EpostID);

                preparedStatement1.setString(1, EpostID);
                resultSet1 = preparedStatement1.executeQuery();

                preparedStatement2.setString(1, EpostID);
                resultSet2 = preparedStatement2.executeQuery();

                String ClassroomID = null;
                if (resultSet1.next()) {
                    String Date = resultSet1.getString("Date");
                    String Time = resultSet1.getString("Time");
                    ClassroomID = resultSet1.getString("ClassroomID");

                    jsonObject.put("date", Date);
                    jsonObject.put("time", Time);

                    System.out.println("ClassroomID " + ClassroomID);
                }

                if (resultSet2.next()) {
                    String Caption = resultSet2.getString("Caption");
                    String ImageStatus = resultSet2.getString("ImageStatus");
                    System.out.println("ImageStatus " + ImageStatus);

                    jsonObject.put("caption", Caption);
                    jsonObject.put("imageStatus", ImageStatus);
                }

                preparedStatement3.setString(1, ClassroomID);
                resultSet3 = preparedStatement3.executeQuery();
                String UserID = null;
                if (resultSet3.next()) {
                    UserID = resultSet3.getString("UserID");

                    System.out.println("UserID " + UserID);
                }

                preparedStatement4.setString(1, UserID);
                resultSet4 = preparedStatement4.executeQuery();
                if (resultSet4.next()) {
                    String FirstName = resultSet4.getString("FirstName");
                    String LastName = resultSet4.getString("LastName");
                    String ProfilePic = resultSet4.getString("ProfilePic");
                    jsonObject.put("firstName", FirstName);
                    jsonObject.put("lastName", LastName);
                    jsonObject.put("profilePic", ProfilePic);

                    System.out.println("FirstName " + FirstName);
                    System.out.println("LastName " + LastName);
                    System.out.println("ProfilePic "+ ProfilePic);

                }
                jsonArray.put(jsonObject);
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
                if ( resultSet3 != null )resultSet3.close();
                if ( resultSet4 != null )resultSet4.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();
                if ( preparedStatement4 != null )preparedStatement4.close();

                if ( connection != null )connection.close();
            }catch ( SQLException E ){ E.printStackTrace(); }
        }
        return jsonArray;
    }

}


