package DAO;

import Database.DBConnectionPool;
import Model.Report;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportInsertDAO {


    private static String generatedReportId;

    public static String insert(String contentID, String type){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedKey = null;

        try {
            String UserID = "";
            String AnswerID = "";
            String NF_postID = "";
            String EpostID = "";
            connection = dbConnectionPool.dataSource.getConnection();
            if(type.equals("userReportOPtion")){
                 UserID = contentID;
                 AnswerID = "0";
                 NF_postID = "0";
                 EpostID = "0";
            }else if(type.equals("answerOPtion")){
                 UserID = "0";
                 AnswerID = contentID;
                 NF_postID = "0";
                 EpostID = "0";
            }else if(type.equals("postOPtion")){
                 UserID = "0";
                 AnswerID = "0";
                 NF_postID = contentID;
                 EpostID = "0";
            }else if(type.equals("educationalPostOPtion")){
                 UserID = "0";
                 AnswerID = "0";
                 NF_postID = "0";
                 EpostID = contentID;
            }
            System.out.println(UserID+" DAO");
            System.out.println(AnswerID+" DAO");
            System.out.println(NF_postID+" DAO");
            System.out.println(EpostID+" DAO");
            int Count = 1;
            String sql = "INSERT INTO Report (Count,Report_delete_flag,UserID,AnswerID,NF_postID,EpostID) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,Count);
            preparedStatement.setBoolean(2,false);
            preparedStatement.setString(3, String.valueOf(UserID));
            preparedStatement.setString(4, String.valueOf(AnswerID));
            preparedStatement.setString(5, String.valueOf(NF_postID));
            preparedStatement.setString(6, String.valueOf(EpostID));
            preparedStatement.executeUpdate();



            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if( resultSet.next() ){
                generatedKey = resultSet.getString(1);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return generatedKey;
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
                ignore.printStackTrace();
            }
        }

        return report;
    }

    public void update(String ID, Integer count){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        System.out.println(ID+" update");
        System.out.println(count+" update");
        System.out.println(String.valueOf(count));
        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "UPDATE Report SET Count = ? WHERE ReportID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString(1 , String.valueOf(count));
            preparedStatement.setString(2 ,String.valueOf(ID));
            preparedStatement.executeUpdate();
            preparedStatement.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }


    }

}
