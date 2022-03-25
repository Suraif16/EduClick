package DAO;

import Database.DBConnectionPool;
import Model.Admin;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ChartDAO<teacherArrayList> {


    public Admin count(Admin admin) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher = 0;
        int countTeacher = 0;
        int countStudent = 0;
        int todaycountStudent = 0;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select UserType,RegistrationDate FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Usertype = resultSet.getString("UserType");
                String Registrationdate = resultSet.getString("RegistrationDate");

                if (Usertype.equals("Teacher")) {
                    countTeacher++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountTeacher++;
                    }
                } else {
                    countStudent++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountStudent++;
                    }
                }
            }
            admin.setCountTeacher(countTeacher);
            admin.setTodaycountTeacher(todaycountTeacher);
            admin.setCountStudent(countStudent);
            admin.setTodaycountStudent(todaycountStudent);

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
        return admin;
    }
    public JSONArray getChartDetails() {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher = 0;
        int countTeacher = 0;
        int countStudent = 0;
        int todaycountStudent = 0;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        System.out.println("Date Format with MM/dd/yyyy : " + strDate);
        JSONArray jsonArray = new JSONArray();

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "select UserType,RegistrationDate FROM Users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String Usertype = resultSet.getString("UserType");
                String Registrationdate = resultSet.getString("RegistrationDate");
                if (Usertype.equals("Teacher")) {
                    countTeacher++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountTeacher++;
                    }
                } else {
                    countStudent++;
                    if (strDate.equals(Registrationdate)) {
                        todaycountStudent++;
                    }
                }




                JSONObject jsonObject = new JSONObject();
                jsonObject.put("aPId",countTeacher);
                jsonObject.put("caption",todaycountTeacher);
                jsonObject.put("date",countStudent);
                jsonObject.put("time",todaycountStudent);


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


