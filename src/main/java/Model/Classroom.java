package Model;


import DAO.ClassroomDAO;

import java.util.ArrayList;

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

        ClassroomDAO classroomDAO = new ClassroomDAO();
        return classroomDAO.selectAll( this.userId );

    }


}
