package Model;

import DAO.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Requests {

    private String fromId;
    private String toId;
    private String type; /* Enroll , Friend */
    private String description;
    private String userName;
    private String userProfile;

    /* Here fromId is always a userId , toId will depend on the type of the request,
    * if the request is a friend request they toId is a userId , else if the request is a
    * enroll request type then toId is a ClassroomId, Since we use Bigint for both userId and
    * ClassroomId it doesn't change anything.
    * Here fromId is the userId of the user who sends the request, and toId is to a user who receives
    * the request, or to a classroom which receives the request
    * Here the Description will will be the classroom details if the type is enroll request
    * else it would say wants to be your friend*/

    public Requests( String fromId, String toId, String type , String description) {

        this.fromId = fromId;
        this.toId = toId;
        this.type = type;
        this.description = description;

    }

    public Requests(){

    }

    public Requests( String fromId , String toId ){

        this.fromId = fromId;
        this.toId = toId;

    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public ArrayList< Requests > selectRequests(Classroom classroom ){

            EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
            return enrollRequestDAO.selectAll( classroom );


    }

    public ArrayList< Requests > selectRequests( String userId ){

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        return friendRequestDAO.selectAll( userId );


    }

    public void requestEnroll(String classroomId,String userId){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.insertRecord(classroomId,userId , date , time );
    }


    public String deleteEnroll(String classroomId,String userId){
        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.deleteRecord(classroomId,userId);
    }

    public String alreadyEnrolledCheck(String classroomId,String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        String status = enrollRequestDAO.checkEnrollment(classroomId,userId);
        if(status == null){
            return "Not Enrolled";
        }
        else{
            return "Enrolled";
        }

    }

    public void addRequest(){

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        friendRequestDAO.insert( this , date , time );


    }

    public void cancelRequest(){

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        friendRequestDAO.delete( this );


    }
    public ArrayList<String> getStudentFriends(String userId){
        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        ArrayList<String> friendList = friendRequestDAO.getStudentFriendKeys(userId);
        return friendList;
    }

    public ArrayList<String> getStudentFollowers(String userId){
        FollowsDAO followsDAO = new FollowsDAO();
        ArrayList<String> followersList = followsDAO.getStudentFollowsKeys(userId);
        return followersList;
    }

    public ArrayList<String> getTeacherFriends(String userId){

        AddFriendsDAO addFriendDAO = new AddFriendsDAO();
        ArrayList<String> friendList = addFriendDAO.getTeacherFriendKeys(userId);
        return friendList;

    }

    public ArrayList<String> getTeacherFollowers(String userId){

        FollowsDAO followsDAO = new FollowsDAO();
        ArrayList<String> followersList = followsDAO.getTeacherFollowersKeys(userId);
        return followersList;

    }

    public boolean confirmEnrollRequest(){

        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.acceptClassroomEnrollRequest( this );

    }
    public ArrayList<String> requestCheck(String classroomId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        return enrollRequestDAO.requestCheck(classroomId);
    }

    public String deleteRequest(String classroomId,String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        return enrollRequestDAO.deleteRequest(classroomId,userId);
    }

    public void deleteEnrollRequest(){

        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.deleteEnrollRequest( this.fromId , this.toId );

    }

    public void acceptFriendRequest(){

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        friendRequestDAO.acceptFriendRequest( this.fromId , this.toId );

    }

}

