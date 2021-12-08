package DAO;

import Database.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassroomHasEPostDAO {

    public void insert( String classroomId , String EpostID ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Classroom_Has_EPost VALUES( ? , ? )";
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1 , classroomId );
            preparedStatement.setString( 2 , EpostID );
            preparedStatement.execute();

            preparedStatement.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

    }

    public ArrayList<String> getEpostsIds(String classroomId){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;
        ArrayList<String> ePostIdList = new ArrayList<String>();

        try {
            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "SELECT EPostID FROM Classroom_has_Epost WHERE ClassroomID = ?";
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


}
