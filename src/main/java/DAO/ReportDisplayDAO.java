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


    public JSONArray getAdminPostDetails() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "select Count,EpostID FROM Report";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Count = resultSet.getString("Count");
                String EpostID = resultSet.getString("EpostID");

                while (resultSet1.next()) {
                    String sql1 = "SELECT NFPostID,Date, Time, Caption FROM NewsFeeds ";
                    preparedStatement1 = connection.prepareStatement(sql1);
                    resultSet = preparedStatement.executeQuery();
                    String NFPostID = resultSet.getString("NFPostID");

                    if(EpostID==NFPostID){
                    String Date = resultSet.getString("Date");
                    String Time = resultSet.getString("Time");
                    String Caption = resultSet.getString("Caption");
                        String sql2 = "SELECT ImagePath,NFPostID FROM News_Feed_Image ";
                        preparedStatement2 = connection.prepareStatement(sql2);
                        resultSet2 = preparedStatement.executeQuery();
                        while (resultSet2.next()){
                            String ANFPostID = resultSet2.getString("NFPostID");
                            if(NFPostID==ANFPostID){
                                String ImagePath = resultSet2.getString("ImagePath");
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("count",Count);
                                jsonObject.put("nFPostID",NFPostID);
                                jsonObject.put("date",Date);
                                jsonObject.put("time",Time);
                                jsonObject.put("caption",Caption);
                                jsonObject.put("ImagePath",ImagePath);
                                jsonArray.put(jsonObject);
                            }else {
                                String ImagePath ="0";
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("count",Count);
                                jsonObject.put("nFPostID",NFPostID);
                                jsonObject.put("date",Date);
                                jsonObject.put("time",Time);
                                jsonObject.put("caption",Caption);
                                jsonObject.put("ImagePath",ImagePath);
                                jsonArray.put(jsonObject);
                            }
                        }
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("count",Count);
                jsonObject.put("epostID",EpostID);

                jsonArray.put(jsonObject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();
                if ( resultSet2 != null )resultSet2.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();

                if ( connection != null )connection.close();

            }catch ( SQLException E ){
                E.printStackTrace();
            }
        }
        return jsonArray;
    }
}


