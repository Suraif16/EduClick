package DAO;

import Database.DBConnectionPool;
import Model.NewsFeeds;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class NewsFeedsImageDAO {

    public JSONArray getImagePath(ArrayList<String> newsFeedsIDList) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsImagesDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for (int i = 0; i < newsFeedsIDList.size(); i++) {
                String sql = "SELECT imagePath FROM News_Feed_Image WHERE NFPostID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newsFeedsIDList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String imagePath = resultSet.getString("imagePath");


                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("ImagePath",imagePath);

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

    public String insert(NewsFeeds newsFeeds){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO News_Feed_Image( NFPostID) VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , newsFeeds.getPostID() );
            preparedStatement.setString( 2 , newsFeeds.getCaption() );

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



}
