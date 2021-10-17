package DAO;

import Database.DBConnectionPool;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {


    public JSONObject select(String email) {

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "pswd" , "" );
        jsonObject.put( "userid" , "" );
        jsonObject.put( "emailConfirmation" , "" );
        jsonObject.put( "passwordIncorrect" , "" );

        try {


            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select Password from admin where EmailID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1 , email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String pswd = resultSet.getString("Password");
                String userid = "";

                jsonObject.put( "pswd" , pswd );
                jsonObject.put( "userid" , userid );
            }
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return jsonObject;

    }

}
