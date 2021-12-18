package DAO;

import Database.DBConnectionPool;
import Model.Answers;
import org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {
    public String saveAnswers(Answers answers){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedAnswerId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Answer (Date,Time) VALUES (?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(answers.getDate()));
            preparedStatement.setString(2, String.valueOf(answers.getTime()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedAnswerId = resultSet.getString(1);

            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("Generated AnswerID is : "+generatedAnswerId);

        return generatedAnswerId;
    }

    public JSONObject selectAnswerDetails(String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM Answer WHERE AnswerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,answerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                jsonObject.put("AnswerID",resultSet.getString("AnswerID"));
                jsonObject.put("Date",resultSet.getString("Date"));
                jsonObject.put("Time",resultSet.getString("Time"));
                jsonObject.put("Marks",resultSet.getString("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        return jsonObject;
    }


    public List< JSONObject > selectEpostAnswer( String EPostId ){

        List< JSONObject > answerList = new ArrayList<>();

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "SELECT S_UserID , AnswerID FROM Answer_Student_Post_Relationship WHERE EPostID = ?";
            preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , EPostId );

            resultSet = preparedStatement.executeQuery();

            String sql1 = "SELECT Date , Time , Marks FROM Answer WHERE AnswerID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT Content , ImageStatus FROM EDW_Answers WHERE AnswerID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            String sql3 = "SELECT FirstName , ProfilePic FROM USERS WHERE UserID = ?";
            preparedStatement3 = connection.prepareStatement( sql3 );

            while( resultSet.next() ){

                JSONObject singleAnswer = new JSONObject();
                singleAnswer.put( "userId" , resultSet.getString( 1 ) );
                singleAnswer.put( "answerId" , resultSet.getString( 2 ) );

                preparedStatement1.setString( 1 , resultSet.getString( 2 ) );

                resultSet1 = preparedStatement1.executeQuery();

                if ( resultSet1.next() ){

                    singleAnswer.put( "answerDate" , resultSet1.getString( 1 ) );
                    singleAnswer.put( "answerTime" , resultSet1.getString( 2 ) );
                    singleAnswer.put( "marks" , resultSet1.getString( 3 ) );

                    preparedStatement2.setString( 1 , resultSet.getString( 2 ) );

                    resultSet2 = preparedStatement2.executeQuery();

                    if ( resultSet2.next() ){

                        singleAnswer.put( "content" , resultSet2.getString( 1 ) );
                        singleAnswer.put( "imageStatus" , resultSet2.getString( 2 ) );

                        preparedStatement3.setString( 1 , resultSet.getString( 1 ) );

                        resultSet3 = preparedStatement3.executeQuery();

                        if ( resultSet3.next() ){

                            singleAnswer.put( "studentName" , resultSet3.getString( 1 ) );
                            singleAnswer.put( "profilePicture" , resultSet3.getString( 2 ) );

                        }else{

                            connection.rollback();

                        }

                    }else{

                        connection.rollback();

                    }

                }else {

                    connection.rollback();

                }

                answerList.add( singleAnswer );

            }

            connection.commit();


        }catch ( SQLException e ){

            try {

                if ( connection != null )connection.rollback();

            }catch ( SQLException E ){

                E.printStackTrace();

            }

        }finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();
                if ( resultSet2 != null )resultSet2.close();
                if ( resultSet3 != null )resultSet3.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();

                if ( connection != null )connection.close();


            }catch ( SQLException e ){

                e.printStackTrace();

            }

        }

        return answerList;

    }


    public String enterMCQMarks(Answers answers){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedAnswerId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            String sql = "INSERT INTO Answer (Date,Time,Marks) VALUES (?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(answers.getDate()));
            preparedStatement.setString(2, String.valueOf(answers.getTime()));
            preparedStatement.setString(3, String.valueOf(answers.getMarks()));

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                generatedAnswerId = resultSet.getString(1);

            }
            resultSet.close();
            preparedStatement.close();





        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }



        return generatedAnswerId;
    }

}
