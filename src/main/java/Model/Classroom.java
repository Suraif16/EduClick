package Model;


import DAO.ClassroomDAO;

import java.util.ArrayList;

public class Classroom {
    private String classroomID;
    private String classroomName;
    private String classroomCode;
    private String Subject;
    private String grade;
    private String year;
    private String userId;

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

    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
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
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /* Constructor */

    public Classroom(String classroomName, String subject, String grade, String year , String userId) {
        this.classroomName = classroomName;
        this.Subject = subject;
        this.grade = grade;
        this.year = year;
        this.userId = userId;
    }

    public Classroom() {
    }

    public void createClassroom(){

        ClassroomDAO classroomDAO = new ClassroomDAO();
        String classroomStatus = classroomDAO.insert( this );
        this.classroomID = classroomStatus;

    }

    public void getClassDetails(ArrayList<String> arrayList){
        ClassroomDAO classroomDAO =  new ClassroomDAO();
        classroomDAO.selectClassDetails(arrayList);
    }


}
