package Model;

import DAO.AddFriendsDAO;
import DAO.EnrollRequestDAO;
import DAO.FriendRequestDAO;

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
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.insertRecord(classroomId,userId);
    }


    public void deleteEnroll(String classroomId,String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.deleteRecord(classroomId,userId);
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

        FriendRequestDAO friendRequestDAO = new FriendRequestDAO();
        friendRequestDAO.insert( this );


    }



}
