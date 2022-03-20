package DAO;

import Database.DBConnectionPool;
import Model.NewsFeeds;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class NewsFeedsDAO {

    public JSONArray getNFDetails(String SharedPostID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
       // ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();


                String sql = "SELECT Date, Time, Caption FROM NewsFeeds WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,SharedPostID);

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



public String insert(NewsFeeds newsFeeds){

    DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
    Connection connection = null;

    try{

        connection = dbConnectionPool.dataSource.getConnection();
        String sql = "INSERT INTO NewsFeeds( DATE , TIME , Caption ) VALUES( ? , ? , ? )";
        PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
        preparedStatement.setString( 1 , String.valueOf( newsFeeds.getDate() ) );
        preparedStatement.setString( 2 , String.valueOf( newsFeeds.getTime() ) );
        preparedStatement.setString( 3 , String.valueOf( newsFeeds.getCaption() ) );
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

    public String getPostedTime(String postId) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String NewsFeedsTime = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();

                String sql = "SELECT Time FROM NewsFeeds WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, postId);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {

                    String time = resultSet.getString("Time");
                    NewsFeedsTime = time;


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
        return NewsFeedsTime;
    }

    public String getNewsFeedsDetails(String SharedPostID) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

                String sql = "SELECT Date, Time, Caption FROM NewsFeeds WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, SharedPostID);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String date = resultSet.getString("Date");
                    String time = resultSet.getString("Time");
                    String caption = resultSet.getString("Caption");

                    jsonObject.put("Date",date);
                    jsonObject.put("Time",time);
                    jsonObject.put("Caption",caption);


                }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return String.valueOf(jsonObject);
    }



}
