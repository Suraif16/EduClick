package Model;

import java.util.List;

public class Teacher extends User{

    private String UserId;
    private String currentWorkingPlace;
    private List<String> subjects;
    private List<String> Qualifications;

    /*Getters and setters begins here*/

    @Override
    public String getUserId() {
        return UserId;
    }

    @Override
    public void setUserId(String userId) {
        UserId = userId;
    }

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



}
