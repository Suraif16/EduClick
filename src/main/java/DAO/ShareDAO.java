package DAO;

import Database.DBConnectionPool;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ShareDAO {

    public String insert(LocalTime time, LocalDate date, String userId, String receiver, String sharedPostID ) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Share( Time , Date , UserID, ReceiveUserID , NFPostID) VALUES( ? , ? , ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(time));
            preparedStatement.setString(2, String.valueOf(date));
            preparedStatement.setString(3, userId);
            preparedStatement.setString(4, receiver);
            preparedStatement.setString(5, sharedPostID);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                return resultSet.getString(1);

            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }

        return null;


    }

    public JSONArray count(String PostId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT UserID FROM Share WHERE NFPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1,PostId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String count = resultSet.getString("UserID");

                jsonArray.put(Integer.parseInt("count"), count);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            if (connection != null) try { connection.close(); }catch (Exception ignore) {}

        }

        return jsonArray;
    }

    //**********************


    public JSONArray getSharedNFReceiver(String ReceiveUserID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT UserID FROM Share WHERE ReceiveUserID = ? LIMIT 15";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ReceiveUserID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String UserID = resultSet.getString("UserID");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("UserID", UserID);

                jsonArray.put(jsonObject);

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
