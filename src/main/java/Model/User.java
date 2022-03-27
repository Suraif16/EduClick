package Model;

import DAO.*;
import org.json.JSONArray;
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

    public ArrayList<JSONObject> searchUser(String searchValue , String searchType , User user ){
        
        UserDAO userDAO =  new UserDAO();
        ArrayList< User > userList = new ArrayList<>();
        if( user!= null){
             userList =  userDAO.searchUser( searchValue , searchType , user.getUserId() );
        }else {
             userList =  userDAO.searchUser( searchValue , searchType , "0" );
        }
        ArrayList< JSONObject > teacherJsonList = new ArrayList<>();
        /* if it is a guest then the user object in the session will be null */

        if ( userList.size() > 0){

            if ( user != null ){

                AddFriendsDAO addFriendsDAO = new AddFriendsDAO();
                FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
                FollowsDAO followsDAO = new FollowsDAO();

                if ( user.getUserType().equals("Teacher") ){

                    for ( int i = 0 ; i < userList.size() ; i++ ){
                        /* here for each found teacher it checks if the teacher is a friend of the searching user
                         * if a friend it sets teh friend status to true, else false*/
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put( "userID" , userList.get(i).getUserId() );
                        jsonObject.put( "firstName" , userList.get(i).getFirstName() );
                        jsonObject.put( "lastName" , userList.get(i).getLastName() );
                        jsonObject.put( "friendStatus" , addFriendsDAO.checkIsFriend( user.getUserId() , userList.get(i).getUserId() ) );
                        jsonObject.put( "friendRequestStatus" , friendRequestDAO.checkIsRequested( user.getUserId() , userList.get(i).getUserId() ) );
                        jsonObject.put( "receivedFriendRequestStatus" , friendRequestDAO.checkIsRequested( userList.get(i).getUserId() , user.getUserId() ) );
                        jsonObject.put( "followStatus" , followsDAO.checkIsFollow( userList.get(i).getUserId() , user.getUserId() ) );
                        teacherJsonList.add( jsonObject );

                    }

                }else {

                    for ( int i = 0 ; i < userList.size() ; i++ ){

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put( "userID" , userList.get(i).getUserId() );
                        jsonObject.put( "firstName" , userList.get(i).getFirstName() );
                        jsonObject.put( "lastName" , userList.get(i).getLastName() );
                        jsonObject.put( "friendStatus" , addFriendsDAO.checkIsFriend( user.getUserId() , userList.get(i).getUserId() ) );
                        jsonObject.put( "friendRequestStatus" , friendRequestDAO.checkIsRequested( user.getUserId() , userList.get(i).getUserId() ));
                        jsonObject.put( "receivedFriendRequestStatus" , friendRequestDAO.checkIsRequested( userList.get(i).getUserId() , user.getUserId() ) );
                        jsonObject.put( "followStatus" , followsDAO.checkIsFollow( user.getUserId() , userList.get(i).getUserId() ) );
                        teacherJsonList.add( jsonObject );

                    }

                }

            }else {

                for ( int i = 0 ; i < userList.size() ; i++ ){

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put( "userID" , userList.get(i).getUserId() );
                    jsonObject.put( "firstName" , userList.get(i).getFirstName() );
                    jsonObject.put( "lastName" , userList.get(i).getLastName() );
                    teacherJsonList.add( jsonObject );

                }

            }

        }

        return  teacherJsonList;
    }



    public ArrayList<Classroom> TeacherClassroomList(String userId){
        ArrayList<Classroom> classroomList = new ArrayList<>();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        classroomList = classroomDAO.selectAll(userId);
        return classroomList;
    }

    public String getFullName(String userId){
        UserDAO userDAO = new UserDAO();
        String fullName = userDAO.getTeacherFullName(userId);
        return fullName;
    }

    public JSONArray getStudentFriendsDetails(ArrayList<String> friendList){
        UserDAO userDAO = new UserDAO();
        JSONArray studentFriendsDetails = userDAO.getStudentFriendsDetails(friendList);
        return studentFriendsDetails;
    }

    public JSONArray getStudentFollowersDetails(ArrayList<String> followersList){
        UserDAO userDAO = new UserDAO();
        JSONArray studentFollowersDetails = userDAO.getStudentFollowersDetails(followersList);
        return studentFollowersDetails;
    }

    public JSONArray getTeacherFollowersList(ArrayList<String> followersList){
        UserDAO userDAO = new UserDAO();
        JSONArray teacherFollowersList = userDAO.getTeacherFollowersList(followersList);
        return teacherFollowersList;
    }

    public JSONArray getTeacherFriendsDetails(ArrayList<String> friendList){
        UserDAO userDAO = new UserDAO();
        JSONArray teacherFriendsDetails = userDAO.getTeacherFriendsDetails(friendList);
        return teacherFriendsDetails;
    }

    public JSONArray getNFownerName(ArrayList<String> NFTeacherIDList){
        UserDAO userDAO = new UserDAO();
        JSONArray teacherNameList = userDAO.getNFownerDetails(NFTeacherIDList);
        return teacherNameList;
    }

    public String getUserIdFromClass(String userId,String id){
        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.getUserIdFromClass(userId,id);
    }
    /*public JSONArray getFollowersList(String userId){
        FollowsDAO followsDAO = new FollowsDAO();
        JSONArray followersIdList = followsDAO.getFollowersIdLists(userId);

        return followersIdList;
    }*/

    /*public JSONArray getFriendsList(String userId){
        UserDAO userDAO = new UserDAO();
        JSONArray teacherNameList = userDAO.getNFownerDetails(userId);
        return teacherNameList;
    }*/

    public void updateBellIcon(String userId){
        BellIconDAO bellIconDAO = new BellIconDAO();
        bellIconDAO.updateRecord(userId);
    }

    public void insertBellIcon(String userId){
        BellIconDAO bellIconDAO = new BellIconDAO();
        bellIconDAO.insertRecord(userId);
    }
    public JSONObject getFirstnameLastName(String userId){
        UserDAO userDAO = new UserDAO();
        return userDAO.getFirstnameLastName(userId);

    }

    public JSONObject getBellIconDetails(String userId){
        BellIconDAO bellIconDAO = new BellIconDAO();
        return bellIconDAO.getBellIconDetails(userId);
    }

    public String getWorkPlace(){

        UserDAO userDAO = new UserDAO();
        return  userDAO.getWorkPlace( this.getUserId() );

    }


}

