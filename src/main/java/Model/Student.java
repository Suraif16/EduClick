package Model;

import DAO.EnrollDAO;
import DAO.EnrollRequestDAO;
import DAO.FollowsDAO;
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

    public void followTeacher( String T_UserId ){

        FollowsDAO followsDAO = new FollowsDAO();
        followsDAO.insert( this.getUserId() , T_UserId );

    }

    public void unfollowTeacher( String T_UserId ){

        FollowsDAO followsDAO = new FollowsDAO();
        followsDAO.delete( this.getUserId() , T_UserId );

    }


}
