package DAO;

import Database.DBConnectionPool;
import Model.Report;

import java.sql.*;
import java.text.SimpleDateFormat;
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
            preparedStatement.setString(2, UserID);//it didnt work
            preparedStatement.setString(3, AnswerID);
            preparedStatement.setString(4, NF_postID);
            preparedStatement.setString(5, EpostID);

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
    public Report select(Report report) {
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select ReportID,Count,UserID,AnswerID,NF_postID,EpostID FROM Report";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String ReportID = resultSet.getString("ReportID");
                Integer Count = resultSet.getInt("Count");
                String UserID = resultSet.getString("UserID");
                String AnswerID = resultSet.getString("AnswerID");
                String NF_postID = resultSet.getString("NF_postID");
                String EpostID = resultSet.getString("EpostID");

                report.setReportID(ReportID);
                report.setCount(Count);
                report.setUserID(UserID);
                report.setAnswerID(AnswerID);
                report.setNF_postID(NF_postID);
                report.setEpostID(EpostID);
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

        return report;
    }
}
