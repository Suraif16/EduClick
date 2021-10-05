package DAO;

import Database.DBConnectionPool;
import Model.Classroom;

import java.sql.*;
import java.util.ArrayList;

public class ClassroomDAO {

    ArrayList<Classroom> classDetails = new ArrayList<Classroom>();

    public ArrayList<Classroom> getClassDetails() {
        return classDetails;
    }

    public String insert(Classroom classroom ){

        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        String generatedKey = null;

        try {

            connection = dbConnectionPool.dataSource.getConnection();
            String sql = "INSERT INTO Classroom(CR_Name , Year , Grade , Subject , UserID) VALUES (?,?,?,?,?)";
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

    public void selectClassDetails(ArrayList<String> arrayList){
        DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
        Connection connection = null;

        try {
            connection = dbConnectionPool.dataSource.getConnection();

            for(int i=0;i<arrayList.size();i++){
                String sql = "SELECT CR_Name,Year,Grade,Subject FROM Classroom WHERE ClassroomID = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, arrayList.get(i));

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()){
                    //THINK OF A METHOD TO RETRIEVE DATA
                    Classroom classroom = new Classroom();
                    classroom.setClassroomName(resultSet.getString("CR_Name"));
                    classroom.setYear(resultSet.getString("Year"));
                    classroom.setGrade(resultSet.getString("Grade"));
                    classroom.setSubject(resultSet.getString("Subject"));
                    classDetails.add(classroom);
                }




            }
            //System.out.println(classDetails.get(1).getClassroomName());




        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
