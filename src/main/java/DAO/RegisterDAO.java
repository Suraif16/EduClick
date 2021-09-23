package DAO;

import Database.DBConnectionPool;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegisterDAO {
    public static String registerUser(User user) {
        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        LocalDate dateofBirth = user.getDateOfBirth();
        String country = user.getCountry();
        String mobileno = user.getMobileNumber();
        String city = user.getCity();
        String gender = user.getGender();

        Connection con = null;
        PreparedStatement preparedStatement= null;

        con = (Connection) DBConnectionPool.getInstance();
        String query = "INSERT INTO Users(FirstName,LastName,DOB,MobileNum,Country) VALUES (?,?,?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3, String.valueOf(dateofBirth));
            preparedStatement.setString(4,mobileno);
            preparedStatement.setString(5,country);

            int i=preparedStatement.executeUpdate();

            if (i!=0){
                return "SUCCESS";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Something went wrong!!";
    }


}
