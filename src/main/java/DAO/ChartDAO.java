package DAO;

import Database.DBConnectionPool;
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


public class ChartDAO<teacherArrayList> {

    public JSONArray getChartDetails() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        int countTeacher = 0;
        int countStudent = 0;


        JSONArray jsonArray = new JSONArray();
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "select DISTINCT RegistrationDate FROM Users";
            preparedStatement = connection.prepareStatement(sql);

            String sql1 = "select UserType FROM Users WHERE RegistrationDate = ?";
            preparedStatement1 = connection.prepareStatement(sql1);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                String Registrationdate = resultSet.getString("RegistrationDate");

                preparedStatement1.setString(1, Registrationdate);
                resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                        String Usertype = resultSet1.getString("UserType");
                        if (Usertype.equals("Teacher")) {
                            countTeacher++;
                        }
                        if (Usertype.equals("Student")) {
                            countStudent++;
                        }
                }
                jsonObject.put("registrationDate",Registrationdate);
                jsonObject.put("countTeacher",countTeacher);
                jsonObject.put("countStudent",countStudent);


                jsonArray.put(jsonObject);
            }

            resultSet.close();
            preparedStatement.close();


            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();

                if ( connection != null )connection.close();
            }catch ( SQLException E ){ E.printStackTrace(); }
        }
        return jsonArray;
    }

}


