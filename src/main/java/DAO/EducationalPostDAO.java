package DAO;

import Database.DBConnectionPool;
import Model.EducationalWork;
import Model.Mcq;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationalPostDAO {

    public JSONObject getEPostDetails(String classroomId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT * FROM EducationalPost WHERE ClassroomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classroomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                jsonObject.put("EPostID",resultSet.getString("EPostID"));
                jsonObject.put("Date",resultSet.getString("Date"));
                jsonObject.put("Time",resultSet.getString("Time"));
                jsonObject.put("EPType",resultSet.getString("EPType"));
                jsonObject.put("Type",resultSet.getString("Type"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return jsonObject;
    }

    public EducationalWork insertEducationalWork( EducationalWork educationalWork , String EPType , String classroomId
    ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String ePostId = null;

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try{
            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );
            String sql = "INSERT INTO EducationalPost( DATE , TIME , EPtype , Type , ClassroomID ) VALUES( ? , ? , ? , ? , ? )";
            preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , String.valueOf( educationalWork.getDate() ) );
            preparedStatement.setString( 2 , String.valueOf( educationalWork.getTime() ) );
            preparedStatement.setString( 3 , EPType );
            preparedStatement.setString( 4 , educationalWork.getType() );
            preparedStatement.setString( 5 , classroomId );
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                ePostId = resultSet.getString( 1 );

            }

            if ( ePostId != null ){

                educationalWork.setPostID( ePostId );

                String sql2 = "INSERT INTO EducationalWork( EPostID , ImageStatus , Caption) VALUES( ? , ? , ? )";
                preparedStatement2 = connection.prepareStatement( sql2 );
                preparedStatement2.setString( 1 , educationalWork.getPostID() );
                preparedStatement2.setString( 2 , educationalWork.getImageStatus() );
                preparedStatement2.setString( 3 , educationalWork.getCaption() );

                preparedStatement2.execute();

                connection.commit();
                return educationalWork;

            }else{

                connection.rollback();
                return null;


            }



        }catch ( SQLException throwables ) {

            try {

                if ( connection != null )connection.rollback();

            }catch( SQLException e ){

                e.printStackTrace();

            }

            throwables.printStackTrace();
        }
        finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null ) resultSet.close();
                if ( resultSet2 != null ) resultSet2.close();

                if ( preparedStatement != null ) preparedStatement.close();
                if ( preparedStatement2 != null ) preparedStatement2.close();

                if ( connection != null ) connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }

        }

        return null;

    }

    public String insertMCQ( EducationalWork educationalWork , String EPType , String classroomId , List<Mcq> mcqList ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String ePostId = null;

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql = "INSERT INTO EducationalPost( DATE , TIME , EPtype , Type , ClassroomID ) VALUES( ? , ? , ? , ? , ? )";
            preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS );
            preparedStatement.setString( 1 , String.valueOf( educationalWork.getDate() ) );
            preparedStatement.setString( 2 , String.valueOf( educationalWork.getTime() ) );
            preparedStatement.setString( 3 , EPType );
            preparedStatement.setString( 4 , educationalWork.getType() );
            preparedStatement.setString( 5 , classroomId );
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                ePostId = resultSet.getString( 1 );

            }

            if ( ePostId != null ){

                String sql2 = "INSERT INTO Question( Question , Correct_answers , EPostID ) VALUES( ? , ? , ? )";
                preparedStatement2 = connection.prepareStatement( sql2 , Statement.RETURN_GENERATED_KEYS );

                for ( Mcq mcq : mcqList ){

                    String questionId  = null;

                    preparedStatement2.setString( 1 , mcq.getQuestion() );
                    preparedStatement2.setString( 2 , mcq.getCorrectAnswer() );
                    preparedStatement2.setString( 3 , ePostId );

                    preparedStatement2.execute();
                    resultSet2 = preparedStatement2.getGeneratedKeys();

                    if ( resultSet2.next() ){

                        questionId = resultSet2.getString( 1 );

                    }

                    if ( questionId != null ){

                        String sql3 = "INSERT INTO Question_Answer_Value VALUES( ? , ? , ? )";
                        preparedStatement3 = connection.prepareStatement( sql3 );

                        for ( int i = 0; i < 4; i++ ) {

                            preparedStatement3.setString( 1 , questionId );
                            preparedStatement3.setString( 2 , String.valueOf(( i + 1 )) );
                            preparedStatement3.setString( 3 , mcq.getAnswerI( i ) );
                            preparedStatement3.execute();

                        }

                    }else {

                        connection.rollback();
                        break;

                    }

                }

                connection.commit();

            }else{

                connection.rollback();

            }

        }catch ( SQLException e ){

            try {

                if ( connection != null )connection.rollback();

            }catch ( SQLException ex ){

                ex.printStackTrace();

            }

        }finally {

            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet2 != null )resultSet2.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();

                if ( connection != null )connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }

        }
        return ePostId;
    }
    public ArrayList<String> getEpostsIds(String classroomId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> ePostIdList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT EPostID FROM EducationalPost WHERE ClassroomID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classroomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                ePostIdList.add(resultSet.getString("EPostID"));
            }
            /*for(int i=0;i<ePostIdList.size();i++){
                System.out.println("Epost ID "+i+" : "+ePostIdList.get(i)+"\n");
            }*/
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); } catch (Exception ignore) {            }
        }
        return ePostIdList;
    }

    public List<JSONObject> select( String classroomId , String minPostId ){
        System.out.println( "classroom id : " + classroomId + " : this is minPostId : " + minPostId );
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        List< JSONObject > ePostList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        ResultSet resultSet4 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql2 = "SELECT ImageStatus , Caption FROM EducationalWork Where EPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            String sql3 = "SELECT QuestionID , Question , Correct_answers FROM Question WHERE EPostID = ?";
            preparedStatement3 = connection.prepareStatement( sql3 );

            String sql4 = "SELECT Answer_no , Answer FROM Question_Answer_Value WHERE QuestionID = ?";
            preparedStatement4 = connection.prepareStatement( sql4 );

            if ( minPostId.equals( "-1" ) ){

                String sql = "SELECT EPostID , Date , Time , EPtype , Type FROM EducationalPost WHERE ClassroomID = ? ORDER BY Date DESC , Time DESC LIMIT 5 ";
                preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString( 1 , classroomId );

            }else {

                String sql = "SELECT EPostID , Date , Time , EPtype , Type FROM EducationalPost WHERE ClassroomID = ? AND EPostID < ? ORDER BY Date DESC , Time DESC LIMIT 2 ";
                preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString( 1 , classroomId );
                preparedStatement.setString( 2 , minPostId );

            }


            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ){

                JSONObject singleEPost = new JSONObject();
                singleEPost.put( "EpostId" , resultSet.getString( 1 ) );
                singleEPost.put( "date" , resultSet.getString( 2 ) );
                singleEPost.put( "time" , resultSet.getString( 3 ) );
                singleEPost.put( "EPtype" , resultSet.getString( 4 ) );
                singleEPost.put( "type" , resultSet.getString( 5 ) );

                if ( resultSet.getString( 4 ).equals("EducationalWork")){

                    preparedStatement2.setString( 1 , ( String ) singleEPost.get( "EpostId" ) );
                    resultSet2 = preparedStatement2.executeQuery();

                    if ( resultSet2.next() ){

                        singleEPost.put( "imageStatus" , resultSet2.getString( 1 ) );
                        singleEPost.put( "caption" , resultSet2.getString( 2 ) );

                    }

                }else if ( resultSet.getString( 4 ).equals( "MCQ" ) ){

                    preparedStatement3.setString( 1 , ( String ) singleEPost.get( "EpostId" ) );
                    resultSet3 = preparedStatement3.executeQuery();

                    List< JSONObject > questionList = new ArrayList<>();

                    while ( resultSet3.next() ){

                        JSONObject singleQuestion = new JSONObject();
                        singleQuestion.put( "questionId" , resultSet3.getString( 1 ) );
                        singleQuestion.put( "question" , resultSet3.getString( 2 ) );
                        singleQuestion.put( "correctAnswer" , resultSet3.getString( 3 ) );


                        preparedStatement4.setString( 1 , ( String ) singleQuestion.get( "questionId" ) );
                        resultSet4 = preparedStatement4.executeQuery();

                        int i = 1;

                        while ( resultSet4.next() ){

                            String keyAnswerName = "answer" + i;
                            String keyAnswerNoName = "answerNo" + i;
                            singleQuestion.put( keyAnswerNoName , resultSet4.getString( 1 ) );
                            singleQuestion.put( keyAnswerName , resultSet4.getString( 2 ) );
                            i++;

                        }

                        questionList.add( singleQuestion );

                    }

                    JSONArray jsonQuestionList = new JSONArray( questionList );

                    singleEPost.put( "questionList" , jsonQuestionList );

                }

                ePostList.add( singleEPost );

            }

            connection.commit();

        } catch (SQLException e) {

            try {

                if ( connection != null )connection.rollback();

            }catch ( SQLException ex ){

                ex.printStackTrace();

            }

        }
        finally {
            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet2 != null )resultSet2.close();
                if ( resultSet3 != null )resultSet3.close();
                if ( resultSet4 != null )resultSet4.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();
                if ( preparedStatement4 != null )preparedStatement4.close();

                if ( connection != null )connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }
        }

        return ePostList;


    }



    public List<JSONObject> selectForMCQ( String classroomId , String minPostId, String userId){
        System.out.println( "classroom id : " + classroomId + " : this is minPostId : " + minPostId );
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        List< JSONObject > ePostList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;
        PreparedStatement preparedStatement5 = null;
        PreparedStatement preparedStatement6 = null;

        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        ResultSet resultSet4 = null;
        ResultSet resultSet5 = null;
        ResultSet resultSet6 = null;

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            connection.setAutoCommit( false );

            String sql2 = "SELECT ImageStatus , Caption FROM EducationalWork Where EPostID = ?";
            preparedStatement2 = connection.prepareStatement( sql2 );

            String sql3 = "SELECT QuestionID , Question , Correct_answers FROM Question WHERE EPostID = ?";
            preparedStatement3 = connection.prepareStatement( sql3 );

            String sql4 = "SELECT Answer_no , Answer FROM Question_Answer_Value WHERE QuestionID = ?";
            preparedStatement4 = connection.prepareStatement( sql4 );

            String sql5 = "SELECT AnswerID FROM Answer_Student_Post_Relationship WHERE S_UserID = ? AND EPostID = ?";
            preparedStatement5 = connection.prepareStatement(sql5);

            String sql6 = "SELECT Choice FROM MCQ_Answers WHERE AnswerID  = ?";
            preparedStatement6 = connection.prepareStatement(sql6);

            if ( minPostId.equals( "-1" ) ){

                String sql = "SELECT EPostID , Date , Time , EPtype , Type FROM EducationalPost WHERE ClassroomID = ? ORDER BY Date DESC , Time DESC LIMIT 2 ";
                preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString( 1 , classroomId );

            }else {

                String sql = "SELECT EPostID , Date , Time , EPtype , Type FROM EducationalPost WHERE ClassroomID = ? AND EPostID < ? ORDER BY Date DESC , Time DESC LIMIT 2 ";
                preparedStatement = connection.prepareStatement( sql );
                preparedStatement.setString( 1 , classroomId );
                preparedStatement.setString( 2 , minPostId );

            }


            resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ){

                JSONObject singleEPost = new JSONObject();
                singleEPost.put( "EpostId" , resultSet.getString( 1 ) );
                singleEPost.put( "date" , resultSet.getString( 2 ) );
                singleEPost.put( "time" , resultSet.getString( 3 ) );
                singleEPost.put( "EPtype" , resultSet.getString( 4 ) );
                singleEPost.put( "type" , resultSet.getString( 5 ) );






                if ( resultSet.getString( 4 ).equals("EducationalWork")){

                    preparedStatement2.setString( 1 , ( String ) singleEPost.get( "EpostId" ) );
                    resultSet2 = preparedStatement2.executeQuery();


                    if ( resultSet2.next() ){

                        singleEPost.put( "imageStatus" , resultSet2.getString( 1 ) );
                        singleEPost.put( "caption" , resultSet2.getString( 2 ) );

                    }


                }else if ( resultSet.getString( 4 ).equals( "MCQ" ) ){

                    String answerId = "";

                    preparedStatement3.setString( 1 , ( String ) singleEPost.get( "EpostId" ) );
                    resultSet3 = preparedStatement3.executeQuery();


                    preparedStatement5.setString(1,userId);
                    preparedStatement5.setString(2,(String) singleEPost.get("EpostId"));
                    resultSet5 = preparedStatement5.executeQuery();


                    if(resultSet5.next()){
                        answerId = resultSet5.getString("AnswerID");
                    }

                    preparedStatement6.setString( 1 , answerId );
                    resultSet6 = preparedStatement6.executeQuery();


                    List< JSONObject > questionList = new ArrayList<>();


                    while ( resultSet3.next() ){

                        JSONObject singleQuestion = new JSONObject();
                        singleQuestion.put( "questionId" , resultSet3.getString( 1 ) );
                        singleQuestion.put( "question" , resultSet3.getString( 2 ) );
                        singleQuestion.put( "correctAnswer" , resultSet3.getString( 3 ) );


                        preparedStatement4.setString( 1 , ( String ) singleQuestion.get( "questionId" ) );
                        resultSet4 = preparedStatement4.executeQuery();

                        int i = 1;


                        while ( resultSet4.next() ){

                            String keyAnswerName = "answer" + i;
                            String keyAnswerNoName = "answerNo" + i;
                            singleQuestion.put( keyAnswerNoName , resultSet4.getString( 1 ) );
                            singleQuestion.put( keyAnswerName , resultSet4.getString( 2 ) );
                            i++;

                        }



                        if(resultSet6.next()){

                            singleEPost.put("Answered","Yes");

                            String studentChoice = "choice";

                            singleQuestion.put(studentChoice,resultSet6.getString(1));

                        }

                        questionList.add( singleQuestion );

                    }

                    

                    JSONArray jsonQuestionList = new JSONArray( questionList );

                    singleEPost.put( "questionList" , jsonQuestionList );

                }

                ePostList.add( singleEPost );

            }

            connection.commit();

        } catch (SQLException e) {

            try {

                if ( connection != null )connection.rollback();

            }catch ( SQLException ex ){

                ex.printStackTrace();

            }

        }
        finally {
            try{

                if ( connection != null )connection.setAutoCommit( true );

                if ( resultSet != null )resultSet.close();
                if ( resultSet2 != null )resultSet2.close();
                if ( resultSet3 != null )resultSet3.close();
                if ( resultSet4 != null )resultSet4.close();
                if ( resultSet5 != null )resultSet5.close();
                if ( resultSet6 != null )resultSet6.close();

                if ( preparedStatement != null )preparedStatement.close();
                if ( preparedStatement2 != null )preparedStatement2.close();
                if ( preparedStatement3 != null )preparedStatement3.close();
                if ( preparedStatement4 != null )preparedStatement4.close();
                if ( preparedStatement5 != null )preparedStatement5.close();
                if ( preparedStatement6 != null )preparedStatement6.close();

                if ( connection != null )connection.close();

            }catch ( SQLException e ){

                e.printStackTrace();

            }
        }

        return ePostList;


    }

    public String selectClassroomId(String ePostId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        JSONObject jsonObject = new JSONObject();
        String classroomId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT ClassroomID FROM EducationalPost WHERE EPostID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ePostId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                classroomId = resultSet.getString("ClassroomID");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return classroomId;
    }



}
