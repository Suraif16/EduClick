package DAO;

import Database.DBConnectionPool;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsFeedsDAO {

    public JSONArray getNFDetails(ArrayList<String> newsFeedsIDList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < newsFeedsIDList.size(); i++) {
                String sql = "SELECT Date, Time, Caption FROM NewsFeeds WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newsFeedsIDList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String date = resultSet.getString("Date");
                    String time = resultSet.getString("Time");
                    String caption = resultSet.getString("Caption");

                    JSONObject jsonObject = new JSONObject();
                    
                    jsonObject.put("Date",date);
                    jsonObject.put("Time",time);
                    jsonObject.put("Caption",caption);
                    jsonArray.put(jsonObject);

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonArray;
    }









}
