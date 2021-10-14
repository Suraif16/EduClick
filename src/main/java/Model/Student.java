package Model;

import DAO.EnrollDAO;
import DAO.EnrollRequestDAO;
import DAO.StudentDAO;

import java.util.ArrayList;

public class Student extends User{

    private String school;
    private String grade;


    /*Getters and setters begins here*/

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    /*Getters and setters ends here*/

    public Student(User user  ){

        super(user);

    }


    public ArrayList<String> checkEnroll(String id){
        System.out.println("checkEnroll reached");
        String studentId = id;
        EnrollDAO enrollDAO =  new EnrollDAO();
        arrayList =  enrollDAO.checkEnrollment(studentId);
        return arrayList;
    }

    public void enterStudent(){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.enterStudent(this);
    }

    public void requestEnroll(String classroomId,String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.insertRecord(classroomId,userId);
    }

    public void deleteEnroll(String classroomId,String userId){
        EnrollRequestDAO enrollRequestDAO = new EnrollRequestDAO();
        enrollRequestDAO.deleteRecord(classroomId,userId);
    }

}
