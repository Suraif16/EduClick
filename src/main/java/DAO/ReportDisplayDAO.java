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
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "select Count,EpostID FROM Report";
            preparedStatement = connection.prepareStatement(sql);

            String sql1 = "SELECT Date, Time, Caption, LikeCount, ShareCount FROM NewsFeeds ";
            preparedStatement1 = connection.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Count = resultSet.getString("Count");

                String EpostID = resultSet.getString("EpostID");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("count",Count);
                jsonObject.put("epostID",EpostID);

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


