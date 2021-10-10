package DAO;

import Database.DBConnectionPool;
import Model.Classroom;

import java.sql.*;
import java.util.ArrayList;

public class ClassroomDAO {

    /*ArrayList<Classroom> classDetails = new ArrayList<Classroom>();

    public ArrayList<Classroom> getClassDetails() {
        return classDetails;
    }*/

    public String insert(Classroom classroom ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String generatedKey = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Classroom(CR_Name , YearOfExamination , Grade , Subject , UserID) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement( sql , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1 , classroom.getClassroomName() );
            preparedStatement.setString( 2 , classroom.getYear() );
            preparedStatement.setString( 3 , classroom.getGrade() );
            preparedStatement.setString( 4 , classroom.getSubject() );
            preparedStatement.setString( 5 , classroom.getUserId() );

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if( resultSet.next() ){

                generatedKey = resultSet.getString(1);

            }



        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return generatedKey;
    }

    public ArrayList<Classroom> selectClassDetails(ArrayList<String> arrayList){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        ArrayList<Classroom> classDetails = new ArrayList<Classroom>();


        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for(int i=0;i<arrayList.size();i++){
                String sql = "SELECT ClassroomID,CR_Name,YearOfExamination,Grade,Subject FROM Classroom WHERE ClassroomID = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, arrayList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    //THINK OF A METHOD TO RETRIEVE DATA
                    String classroomID = resultSet.getString( "ClassroomID" );
                    String CR_Name = resultSet.getString( "CR_Name" );
                    String YearOfExamination = resultSet.getString( "YearOfExamination" );
                    String grade = resultSet.getString( "Grade" );
                    String subject = resultSet.getString( "Subject" );
                    Classroom classroom = new Classroom( CR_Name , subject , grade , YearOfExamination , null );
                    classroom.setClassroomID( classroomID );
                    classDetails.add(classroom);
                }




            }
            //System.out.println(classDetails.get(1).getClassroomName());




        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }
        return classDetails ;

    }

    public ArrayList<Classroom> selectAll( String userId ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        ArrayList<Classroom> classroomList = new ArrayList<>();

        try{

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "select CLassroomID, CR_Name , YearOfExamination , Grade , Subject from Classroom where UserID = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement( sql );
            preparedStatement.setString( 1, userId );
            ResultSet resultSet = preparedStatement.executeQuery();

            while( resultSet.next() ){

                String classroomID = resultSet.getString( "ClassroomID" );
                String CR_Name = resultSet.getString( "CR_Name" );
                String YearOfExamination = resultSet.getString( "YearOfExamination" );
                String grade = resultSet.getString( "Grade" );
                String subject = resultSet.getString( "Subject" );
                Classroom classroom = new Classroom( CR_Name , subject , grade , YearOfExamination , null );
                classroom.setClassroomID( classroomID );
                classroomList.add(classroom);

            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); }catch (Exception ignore) {}
        }

        return classroomList;

    }

}
