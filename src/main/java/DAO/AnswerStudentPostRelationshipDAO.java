package DAO;

import Database.DBConnectionPool;
import Model.Answers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerStudentPostRelationshipDAO {
    public String saveUserAnswerPostDetails(Answers answer,String answerId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Answer_Student_Post_Relationship (S_UserID,AnswerID,EPostID) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,answer.getUserId());
            preparedStatement.setString(2,answerId);
            preparedStatement.setString(3,answer.getQuestionId());

            preparedStatement.executeUpdate();
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
        return "Done";

    }
    public String getAnswerId(Answers answers){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String answerId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT AnswerID FROM Answer_Student_Post_Relationship WHERE S_UserID = ? AND EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,answers.getUserId());
            preparedStatement.setString(2,answers.getQuestionId());

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                answerId = resultSet.getString("AnswerID");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("AnswerID in AnsPostStuDAO : "+answerId);
        return answerId;
    }


    public void saveMCQAnswerPostStudentRelationship(String userId,String answerId,String postId){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Answer_Student_Post_Relationship (S_UserID,AnswerID,EPostID) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,userId);
            preparedStatement.setString(2,answerId);
            preparedStatement.setString(3,postId);

            preparedStatement.executeUpdate();
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

        System.out.println("Everything saved in database for MCQ answer loading");

    }

    public JSONArray getMcqResult( String mcqPostId ){
        System.out.println( mcqPostId + " this is it ");

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        JSONArray jsonArray = new JSONArray();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "SELECT S_UserID , AnswerID FROM Answer_Student_Post_Relationship WHERE EPostID = ?";
            preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , mcqPostId );

            String sql1 = "SELECT Marks FROM Answer WHERE AnswerID = ?";
            preparedStatement1 = connection.prepareStatement( sql1 );

            String sql2 = "SELECT FirstName , LastName , ProfilePic FROM Users WHERE UserID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ){

                JSONObject singleMcqResult = new JSONObject();

                singleMcqResult.put( "userId" , resultSet.getString( 1 ) );

                preparedStatement1.setString( 1 , resultSet.getString( 2 ) );
                resultSet1 = preparedStatement1.executeQuery();

                preparedStatement2.setString( 1 , resultSet.getString( 1 ) );
                resultSet2 = preparedStatement2.executeQuery();

                if ( resultSet1.next() ){

                    singleMcqResult.put( "marks" , resultSet1.getString( 1 ) );

                }

                if ( resultSet2.next() ){

                    singleMcqResult.put( "firstName" , resultSet2.getString( 1 ) );
                    singleMcqResult.put( "lastName" , resultSet2.getString( 2 ) );
                    singleMcqResult.put( "profilePic" , resultSet2.getString( 3 ) );

                }

                jsonArray.put( singleMcqResult );
            }

            connection.commit();

        } catch (SQLException throwables) {

            try{

                if ( connection != null )connection.rollback();

            }catch ( SQLException E ){

                E.printStackTrace();

            }

            throwables.printStackTrace();


        }finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet1 != null )resultSet1.close();
                if ( resultSet2 != null )resultSet2.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement1 != null )preparedStatement1.close();
                if ( preparedStatement2 != null )preparedStatement2.close();

                if ( connection != null )connection.close();

            }catch ( SQLException E ){

                E.printStackTrace();

            }

        }

        return jsonArray;

    }

}
