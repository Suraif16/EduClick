package Model;

import java.sql.Date;

public class Classroom {
    private String classroomID;
    private String classroomName;
    private String classroomCode;
    private String Subject;
    private int grade;
    private Date year;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /* Constructor */

    public Classroom(String classroomName, String subject, int grade, Date year , String userId) {
        this.classroomName = classroomName;
        this.Subject = subject;
        this.grade = grade;
        this.year = year;
        this.userId = userId;
    }

    public void createClassroom(){

    }
}
