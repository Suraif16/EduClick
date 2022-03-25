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

    public JSONArray getAdminPostDetails(  ){


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


            String sql1 = "SELECT NFPostID,Date, Time, Caption,ImageStatus FROM NewsFeeds ";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT ImagePath,NFPostID FROM News_Feed_Image ";
            preparedStatement2 = connection.prepareStatement( sql2 );

            resultSet = preparedStatement.executeQuery();
            System.out.println("dao");
            while( resultSet.next() ){
                String Count = resultSet.getString("Count");
                String EpostID = resultSet.getString("EpostID");
                System.out.println("EpostID " +EpostID);
                System.out.println("Count " +Count);
                resultSet1 = preparedStatement1.executeQuery();
                while ( resultSet1.next() ){
                    String NFPostID = resultSet1.getString("NFPostID");
                    if(EpostID==NFPostID){
                        System.out.println("NFPostID "+NFPostID);
                        String Date = resultSet1.getString("Date");
                        String Time = resultSet1.getString("Time");
                        String Caption = resultSet1.getString("Caption");
                        String ImageStatus = resultSet1.getString("ImageStatus");
                        resultSet2 = preparedStatement2.executeQuery();
                        while ( resultSet2.next() ){
                            String ANFPostID = resultSet2.getString("NFPostID");
                            if(ANFPostID==NFPostID){
                                System.out.println("ANFPostID "+ANFPostID);
                                String ImagePath = resultSet2.getString("ImagePath");
                                System.out.println("ImagePath "+ImagePath);
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("count",Count);
                                jsonObject.put("nFPostID",NFPostID);
                                jsonObject.put("date",Date);
                                jsonObject.put("time",Time);
                                jsonObject.put("caption",Caption);
                                jsonObject.put("imageStatus",ImageStatus);
                                System.out.println("ImageStatus "+ImageStatus);
                                jsonObject.put("imagePath",ImagePath);
                                jsonArray.put(jsonObject);
                            }else{
                                String ImagePath ="0";
                                System.out.println(ImagePath);
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("count",Count);
                                jsonObject.put("nFPostID",NFPostID);
                                jsonObject.put("date",Date);
                                jsonObject.put("time",Time);
                                jsonObject.put("caption",Caption);
                                jsonObject.put("imageStatus",ImageStatus);
                                System.out.println("ImageStatus "+ImageStatus);
                                jsonObject.put("imagePath",ImagePath);
                                jsonArray.put(jsonObject);
                            }
                        }
                    }
                }
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


