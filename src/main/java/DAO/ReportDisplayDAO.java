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

public class ReportDisplayDAO {


    public JSONArray getAdminPostDetails() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "select ReportID,Count,Report_delete_flag,UserID,AnswerID,NF_postID,EpostID FROM Report";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String ReportID = resultSet.getString("ReportID");
                String Count = resultSet.getString("Count");
                String Report_delete_flag = resultSet.getString("Report_delete_flag");
                String UserID = resultSet.getString("UserID");
                String AnswerID = resultSet.getString("AnswerID");
                String NF_postID = resultSet.getString("NF_postID");
                String EpostID = resultSet.getString("EpostID");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("aPId",ReportID);
                jsonObject.put("caption",Count);
                jsonObject.put("date",Report_delete_flag);
                jsonObject.put("time",UserID);
                jsonObject.put("status",AnswerID);
                jsonObject.put("status",NF_postID);
                jsonObject.put("status",EpostID);

                jsonArray.put(jsonObject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
        return jsonArray;
    }
}


