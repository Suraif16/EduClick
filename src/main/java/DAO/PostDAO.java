package DAO;

import Database.DBConnectionPool;
import Model.NewsFeeds;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

public class PostDAO {
    public ArrayList<String> getNewsFeedsID(String userId) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> NFKeyList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT NFPostID FROM Posts WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NFKeyList.add(resultSet.getString("NFPostID"));
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
        return NFKeyList;
    }

    public ArrayList<String> getNFTeacherKeys(String userId) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> NFKeyList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT T_UserID FROM Posts WHERE UserID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NFKeyList.add(resultSet.getString("T_UserID"));
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
        return NFKeyList;
    }


    public JSONArray getIDNF(String userID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT NFPostID FROM Posts WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String NewsFeedID = resultSet.getString("NFPostID");

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("NewsFeedID", NewsFeedID);

                jsonArray.put(jsonObject);

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
        return jsonArray;
    }

    public String insert(NewsFeeds newsFeeds, String friendsID, String userId) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Posts( NFPostID, UserID , T_UserID ) VALUES( ? , ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newsFeeds.getPostID());
            preparedStatement.setString(2, friendsID);
            preparedStatement.setString(3, userId);
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


    public JSONArray getPostReceiver(String T_UserID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<User> NewsFeedsDetails = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT NFPostID FROM Posts WHERE UserID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, T_UserID);

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

    public ArrayList<String> getLoadedNewsFeedsId(String AUserID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> NewsFeedsIdList = new ArrayList<>();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT NFPostID FROM Posts WHERE UserID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(AUserID));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewsFeedsIdList.add(resultSet.getString("NFPostID"));


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
        return NewsFeedsIdList;
    }


    public String getOwnerId(Object NewsFeedID) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        //  JSONObject jsonObject = new JSONObject();
        String UserID = "";


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT T_UserID FROM Posts WHERE NFPostID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (String) NewsFeedID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserID = resultSet.getString("T_UserID");


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
        return UserID;
    }

    public ArrayList<String> getInsertedNewsFeedsId(String UserID) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> NewsFeedsIdList = new ArrayList<>();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "SELECT NFPostID FROM Posts WHERE  T_UserID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserID));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NewsFeedsIdList.add(resultSet.getString("NFPostID"));


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

        return NewsFeedsIdList;
    }

}