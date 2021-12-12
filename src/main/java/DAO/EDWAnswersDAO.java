package DAO;

import Database.DBConnectionPool;
import Model.Answers;

import java.sql.*;

public class EDWAnswersDAO {
    public String insert(Answers answers,String answerId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        String generatedImageId = "";

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO EDW_Answers(AnswerID,Content) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,answerId);
            preparedStatement.setString(2,answers.getAnswer());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.next() ){

                generatedImageId = resultSet.getString( 1 );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception ignore) {
            }
        }
        System.out.println("case eka nmekada? : "+generatedImageId);
        return generatedImageId;
    }
}
