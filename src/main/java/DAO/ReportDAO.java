package DAO;

import Database.DBConnectionPool;
import Model.Admin;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ReportDAO {


    public Admin selectreport(Admin admin) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select ReportID,Count,UserID,NF_postID,EpostID FROM Report";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String ReportID = resultSet.getString("ReportID");
                String Count = resultSet.getString("Count");
                String UserID = resultSet.getString("UserID");
                String NF_postID = resultSet.getString("NF_postID");
                String EpostID = resultSet.getString("EpostID");

                if (NF_postID.equals("NULL")&&EpostID.equals("NULL")) {
                    //accont
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ReportID",ReportID);
                    jsonObject.put("UserID",UserID);
                    jsonObject.put("Count",Count);

                } else if (UserID.equals("NULL")&&EpostID.equals("NULL")) {

                    //Newsfeedpost
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ReportID",ReportID);
                    jsonObject.put("NF_postID",NF_postID);
                    jsonObject.put("Count",Count);
                }else if (UserID.equals("NULL")&&NF_postID.equals("NULL")) {
                    //post
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ReportID",ReportID);
                    jsonObject.put("EpostID",EpostID);
                    jsonObject.put("Count",Count);

                }
            }


            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }

        return admin;
    }


}


