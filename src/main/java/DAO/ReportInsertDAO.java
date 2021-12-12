package DAO;

import Database.DBConnectionPool;
import Model.Admin;
import Model.AdminPost;
import Model.Report;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReportInsertDAO {


    private static String generatedReportId;
    private static String UserID;
    private static String AnswerID;
    private static String NF_postID;
    private static String EpostID;


    public static String insert(Report report){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            if(report.getType().equals("educationalPostOPtion")){
                 UserID = report.getContentID();
                 AnswerID = "";
                 NF_postID = "";
                 EpostID = "";
            }else if(report.getType().equals("AnswerID")){
                 UserID = "";
                 AnswerID = report.getContentID();
                 NF_postID = "";
                 EpostID = "";
            }else if(report.getType().equals("NF_postID")){
                 UserID = "";
                 AnswerID = "";
                 NF_postID = report.getContentID();
                 EpostID = "";
            }else if(report.getType().equals("EpostID")){
                 UserID = "";
                 AnswerID = "";
                 NF_postID = "";
                 EpostID = report.getContentID();
            }
            System.out.println(UserID);
            System.out.println(AnswerID);
            System.out.println(NF_postID);
            System.out.println(EpostID);
            int Count = 1;
            int i=Integer.parseInt(UserID);
            String sql = "INSERT INTO Report (Count,UserID,AnswerID,NF_postID,EpostID) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,Count );
            preparedStatement.setInt(2, i);
            preparedStatement.setString(3, AnswerID);
            preparedStatement.setString(4, NF_postID);
            preparedStatement.setString(5, EpostID);
            //preparedStatement.setString(2, String.valueOf(report.getDate()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                generatedReportId = resultSet.getString(1);

            }
            //returns userID
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return generatedReportId;
    }
    /*public Admin count(Admin admin) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        int todaycountTeacher = 0;
        int countTeacher = 0;
        int countStudent = 0;
        int todaycountStudent = 0;

        java.util.Date date = new Date();
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
            System.out.println(countTeacher);
            admin.setTodaycountTeacher(todaycountTeacher);
            System.out.println(todaycountTeacher);
            admin.setCountStudent(countStudent);
            System.out.println(countStudent);
            admin.setTodaycountStudent(todaycountStudent);
            System.out.println(todaycountStudent);

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
    }*/
}
