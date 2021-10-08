package Model;

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

    public Student(User user , String school , String grade ){

        super(user);
        this.school = school;
        this.grade = grade;

    }

}
