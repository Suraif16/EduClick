package Model;

import DAO.StudentDAO;
import DAO.TeacherDAO;

import java.util.List;

public class Teacher extends User{

    private String currentWorkingPlace;
    private List<String> subjects;
    private List<String> Qualifications;


    /*Getters and setters begins here*/

    public String getCurrentWorkingPlace() {
        return currentWorkingPlace;
    }

    public void setCurrentWorkingPlace(String currentWorkingPlace) {
        this.currentWorkingPlace = currentWorkingPlace;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getQualifications() {
        return Qualifications;
    }

    public void setQualifications(List<String> qualifications) {
        Qualifications = qualifications;
    }

    /*Getters and setters ends here*/

    public Teacher( User user , String currentWorkingPlace , List<String> subjects , List<String> Qualifications){

        super( user );
        this.currentWorkingPlace = currentWorkingPlace;
        this.subjects = subjects;
        this.Qualifications = Qualifications;

    }

    public Teacher( User user){
        super( user );
    }

    public void enterTeacher(){
        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.enterTeacher(this);
    }

    public void searchTeacher(){
        TeacherDAO teacherDAO = new TeacherDAO();
        teacherDAO.searchTeacher(this);
    }


}
