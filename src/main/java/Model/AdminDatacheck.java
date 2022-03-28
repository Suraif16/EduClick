package Model;

import DAO.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AdminDatacheck {

    private String userId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String profilePicture;
    private String country;
    private String city;
    private LocalTime registrationTime;
    private LocalDate registrationDate;
    private String gender;
    private String userType;


    public AdminDatacheck(String userId, String firstName,String lastName,LocalDate dataOfBirth,String mobileNumber,String profilePicture,String country,String city,String gender,LocalDate dataOfReg,LocalTime timeOfReg) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dataOfBirth;
        this.mobileNumber = mobileNumber;
        this.profilePicture = profilePicture;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.registrationDate = dataOfReg;
        this.registrationTime = timeOfReg;

    }

    public AdminDatacheck(AdminDatacheck user ) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
        this.mobileNumber = user.getMobileNumber();
        this.profilePicture = user.getProfilePicture();
        this.country = user.getCountry();
        this.city = user.getCity();
        this.gender = user.getGender();
        this.registrationDate = user.getRegistrationDate();
        this.registrationTime = user.getRegistrationTime();

    }

    public String getUserType() {
           return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /*Getters and setters begins here*/

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public LocalTime getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(LocalTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    /*Getters and setters ends here*/
    public AdminDatacheck() {

    }
   /* public AdminDatacheck getData(){
        AdminDatacheckDAO admindao = new AdminDatacheckDAO();
        return admindao.select(this);
    }*/

    public ArrayList<JSONObject> searchUser(String searchValue , String searchType ){
        AdminDatacheckDAO adminDatacheckDAO =  new AdminDatacheckDAO();
        ArrayList< AdminDatacheck > userList = new ArrayList<>();
        userList =  adminDatacheckDAO.searchUser( searchValue , searchType );
        ArrayList< JSONObject > teacherJsonList = new ArrayList<>();
        if ( userList.size() > 0){
                    for ( int i = 0 ; i < userList.size() ; i++ ){
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put( "userId" , userList.get(i).getUserId() );
                        jsonObject.put( "firstName" , userList.get(i).getFirstName() );
                        jsonObject.put( "lastName" , userList.get(i).getLastName());
                        jsonObject.put( "dataOfBirth" , userList.get(i).getDateOfBirth() );
                        jsonObject.put( "mobileNumber" , userList.get(i).getMobileNumber());
                        jsonObject.put( "profilePic" , userList.get(i).getProfilePicture());
                        jsonObject.put( "country" , userList.get(i).getCountry());
                        jsonObject.put( "city" , userList.get(i).getCity());
                        jsonObject.put( "gender" , userList.get(i).getGender() );
                        jsonObject.put( "dataOfReg" , userList.get(i).getRegistrationDate());
                        jsonObject.put( "timeOfReg" , userList.get(i).getRegistrationTime());

                        teacherJsonList.add( jsonObject );
                    }
            }
        return  teacherJsonList;
    }
}

