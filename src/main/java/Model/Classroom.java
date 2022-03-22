package Model;


import DAO.ClassroomDAO;
import DAO.ClassroomHasEPostDAO;
import DAO.EnrollDAO;
import DAO.EnrollRequestDAO;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private String classroomID;
    private String classroomName;
    private String Subject;
    private String grade;
    private String Year;
    private String userId;


    ArrayList<Classroom> classDetails = new ArrayList<Classroom>();

    public String getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(String classroomID) {
        this.classroomID = classroomID;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }


    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /* Constructor */

    public Classroom(String classroomName, String subject, String grade, String Year , String userId) {
        this.classroomName = classroomName;
        this.Subject = subject;
        this.grade = grade;
        this.Year = Year;
        this.userId = userId;
    }

    public Classroom() {

    }

    public Classroom(String userId){

        this.userId = userId;

    }

    public Classroom(String classroomID, String classroomName) {
        this.classroomID = classroomID;
        this.classroomName = classroomName;
    }

    public void createClassroom(){

        ClassroomDAO classroomDAO = new ClassroomDAO();
        String classroomStatus = classroomDAO.insert( this );
        this.classroomID = classroomStatus;

    }

    public ArrayList<Classroom> getClassDetails(ArrayList<String> arrayList){
        ClassroomDAO classroomDAO =  new ClassroomDAO();
        classDetails =classroomDAO.selectClassDetails(arrayList);

        return classDetails;

    }

    public  ArrayList<Classroom> getListOfCLassRooms(){

        ArrayList<Classroom> classroomList = new ArrayList<>();
        ClassroomDAO classroomDAO = new ClassroomDAO();
        classroomList = classroomDAO.selectAll( this.userId );
        return classroomList;

    }
    /*public ArrayList<String> checkEposts(String classroomId){
        ClassroomHasEPostDAO classroomHasEPostDAO = new ClassroomHasEPostDAO();
        return classroomHasEPostDAO.getEpostsIds(classroomId);

    }*/
    public String getClassroomOwnerId(String classroomId){
        ClassroomDAO classroomDAO = new ClassroomDAO();
        return classroomDAO.getClassroomOwnderId(classroomId);
    }
    public String checkEnableOrDisable(){
        /*System.out.println("Enable Model : "+this.getUserId());
        System.out.println("Enable Model : "+this.getClassroomID());*/
        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.checkEnableOrDisable(this.getUserId(),this.getClassroomID());

    }

    public List<JSONObject> SelectClassroomStudentEnrollList( String classroomId ){

        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.selectStudentEnrollList( classroomId );

    }

    public void updateEnableDisableStatus( String userId , String classroomId , String status ){

        EnrollDAO enrollDAO = new EnrollDAO();
        enrollDAO.updateStatus( userId , classroomId , status );

    }
    public ArrayList<String> getStudentListInClass(String classroomId){
        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.getStudentsListInClass(classroomId);
    }

    public ArrayList<String> getEnrolledClassrooms(String userId){
        EnrollDAO enrollDAO = new EnrollDAO();
        return enrollDAO.checkEnrollment(userId);

    }
    public ArrayList<String> getRequestedClassroomList(String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        return enrollRequestDAO.getRequestedClassroomList(userId);
    }

    public JSONObject getClassroomDetails(){

        ClassroomDAO classroomDAO = new ClassroomDAO();
        return classroomDAO.selectClassroomDetails( this.classroomID );

    }

    public void deleteClassroom(){

        ClassroomDAO classroomDAO = new ClassroomDAO();
        classroomDAO.deleteClassroom( this.classroomID );

    }
    public JSONObject getStudentClassroomDetails(String classroomId){
        ClassroomDAO classroomDAO = new ClassroomDAO();
        return classroomDAO.getStudentClassroomDetails(classroomId);
    }
}
