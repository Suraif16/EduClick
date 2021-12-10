package DAO;

import Database.DBConnectionPool;
import Model.AdminPost;
import Model.Report;
import Model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

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
            if(report.getType().equals("UserID")){
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
            int Count = 1;
            String sql = "INSERT INTO Report (Count,UserID,AnswerID,NF_postID,EpostID) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(Count));
            preparedStatement.setString(2, UserID);
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

}
