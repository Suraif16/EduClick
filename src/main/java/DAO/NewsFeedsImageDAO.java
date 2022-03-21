package DAO;

import Database.DBConnectionPool;
import Model.NewsFeeds;
import Model.Post;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class NewsFeedsImageDAO extends Post {

    public JSONObject getImagePath(Object newsFeedsIDList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
       // ArrayList<String> imagePathList = new ArrayList<String>();
        JSONObject jsonObject = new JSONObject();


        try {
            connection = dbConnectionPool.dataSource.getConnection();


                String sql = "SELECT imagePath FROM News_Feed_Image WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, (String) newsFeedsIDList);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String path = resultSet.getString("imagePath");
                    jsonObject.put("path",path);

                }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }

        return jsonObject;
    }

    public String insert(NewsFeeds newsFeeds){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO News_Feed_Image( NFPostID ) VALUES( ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , newsFeeds.getPostID());
            preparedStatement.execute();

            String i = newsFeeds.getPostID();
            System.out.println(i + " -> This is newsfeed id ");

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

        return  null;

    }

    public JSONObject getNewsFeedsImageDetails(String postId) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT ImagePath FROM News_Feed_Image WHERE NFPostID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, postId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String imagePath = resultSet.getString("imagePath");

                jsonObject.put("ImagePath",imagePath);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }

        }
        return jsonObject;


    }



}
