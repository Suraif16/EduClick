package Model;

import DAO.AddFriendsDAO;
import DAO.FollowsDAO;
import DAO.FriendRequestDAO;
import DAO.UserDAO;
import org.json.JSONObject;

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

    public User( String userId ) {
        this.userId = userId;
    }

    public  User(){}

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

    public User(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User getUser(){
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

    public ArrayList<JSONObject> searchTeacher(String teacherName , User user ){
        
        UserDAO userDAO =  new UserDAO();
        ArrayList< User > teacherArrayList = new ArrayList<>();
        if( user!= null){
             teacherArrayList =  userDAO.searchTeacher( teacherName , user.getUserId() );
        }else {
             teacherArrayList =  userDAO.searchTeacher( teacherName , "0" );
        }
        ArrayList< JSONObject > teacherJsonList = new ArrayList<>();
        /* if it is a guest then the user object in the session will be null */

        if ( teacherArrayList.size() > 0){

            if ( user != null ){

                if ( user.getUserType().equals("Teacher") ){

                    AddFriendsDAO addFriendsDAO = new AddFriendsDAO();
                    FriendRequestDAO friendRequestDAO = new FriendRequestDAO();

                    for ( int i = 0 ; i < teacherArrayList.size() ; i++ ){
                        /* here for each found teacher it checks if the teacher is a friend of the searching user
                         * if a friend it sets teh friend status to true, else false*/
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put( "userID" , teacherArrayList.get(i).getUserId() );
                        jsonObject.put( "firstName" , teacherArrayList.get(i).getFirstName() );
                        jsonObject.put( "lastName" , teacherArrayList.get(i).getLastName() );
                        jsonObject.put( "friendStatus" , addFriendsDAO.checkIsFriend( user.getUserId() , teacherArrayList.get(i).getUserId() ) );
                        jsonObject.put( "friendRequestStatus" , friendRequestDAO.checkIsRequested( user.getUserId() , teacherArrayList.get(i).getUserId() ));
                        teacherJsonList.add( jsonObject );

                    }

                }else {

                    FollowsDAO followsDAO = new FollowsDAO();

                    for ( int i = 0 ; i < teacherArrayList.size() ; i++ ){

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put( "userID" , teacherArrayList.get(i).getUserId() );
                        jsonObject.put( "firstName" , teacherArrayList.get(i).getFirstName() );
                        jsonObject.put( "lastName" , teacherArrayList.get(i).getLastName() );
                        jsonObject.put( "followStatus" , followsDAO.checkIsFollow( user.getUserId() , teacherArrayList.get(i).getUserId() ) );
                        teacherJsonList.add( jsonObject );

                    }

                }

            }else {

                for ( int i = 0 ; i < teacherArrayList.size() ; i++ ){

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put( "userID" , teacherArrayList.get(i).getUserId() );
                    jsonObject.put( "firstName" , teacherArrayList.get(i).getFirstName() );
                    jsonObject.put( "lastName" , teacherArrayList.get(i).getLastName() );
                    teacherJsonList.add( jsonObject );

                }

            }

        }

        return  teacherJsonList;
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
