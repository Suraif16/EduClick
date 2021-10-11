package Model;

import DAO.EnrollDAO;
import DAO.UserDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String profilePicture;
    private String country;
    private String city;
    private LocalTime registrationTime;
    private LocalDate registrationDate;
    private String gender;
    private String userType;


    ArrayList<String> arrayList = new ArrayList<String>();

    public User(String firstName, String lastName, LocalDate dateOfBirth, String mobileNumber,  String country, String city, LocalTime registrationTime, LocalDate registrationDate, String gender, String userType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.country = country;
        this.city = city;
        this.registrationTime = registrationTime;
        this.registrationDate = registrationDate;
        this.gender = gender;
        this.userType = userType;
    }

    public User(String userId, String firstName, String lastName, LocalDate dateOfBirth, String mobileNumber, String profilePicture, String country, String city, String gender, String userType) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.profilePicture = profilePicture;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.userType = userType;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User( User user ){

        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
        this.mobileNumber = user.getMobileNumber();
        this.profilePicture = user.getProfilePicture();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.gender = user.getGender();
        this.userType = user.getUserType();

    }

    public User checkUsertype(){
        UserDAO userDAO = new UserDAO();
        return userDAO.select(this);
    }
    public String getUserType() {
           return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }




    /*Getters and setters begins here*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /*Getters and setters ends here*/

    public void userRegistered(){
        UserDAO userDAO = new UserDAO();
        userDAO.insert(this);
        userId=userDAO.getGeneratedUserId();
    }

    /*to check the enroll table and get records*/

    /*public ArrayList<String> checkEnroll(String id){
        System.out.println("checkEnroll reached");
        String studentId = id;
        EnrollDAO enrollDAO =  new EnrollDAO();
        arrayList =  enrollDAO.checkEnrollment(studentId);
        return arrayList;
    }*/
}
