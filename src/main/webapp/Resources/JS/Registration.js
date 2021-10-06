const submitButton = document.getElementById("button");

const sendServerData = function () {

    let firstName = document.getElementById("firstName").value;

    let lastName = document.getElementById("lastName").value;

    let email = document.getElementById("email").value;

    let dateOfBirth = document.getElementById("DOB").value;

    var userTypeSelect;
    var userType=document.getElementsByName("userType");
    for(i = 0;i<userType.length;i++){
        if(userType[i].checked){
            console.log(userType[i].value);
            userTypeSelect = userType[i].value;
        }
    }

    let country = document.getElementById("country").value;

    let city = document.getElementById("city").value;

    let mobileNumber = document.getElementById("mobileNo").value;

    let countryCode = document.getElementById("countryCode").value;

    var genderSelect;
    var gender=document.getElementsByName("gender");
    for(i = 0;i<gender.length;i++){
        if(gender[i].checked){
            console.log(gender[i].value);
            genderSelect = gender[i].value;
        }
    }

    let password = document.getElementById("Password").value;

    let confirmPassword = document.getElementById("confirmPassword").value;

    let newNumber = countryCode.concat(mobileNumber);


    console.log(firstName);
    console.log(lastName);
    console.log(email);
    console.log(dateOfBirth);
    console.log(country);
    console.log(city);
    console.log(countryCode);
    console.log(mobileNumber);
    console.log(newNumber);
    console.log(password);
    console.log(confirmPassword);

    let httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            completeRegistration(this) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpReq.open("POST", "/EduClick_war_exploded/Registration", true);
    httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httpReq.send("firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&dateOfBirth=" + dateOfBirth + "&userTypeSelect=" + userTypeSelect + "&country=" + country + "&city=" + city + "&newNumber=" + newNumber + "&genderSelect=" + genderSelect + "&Password=" + password + "&confirmPassword=" + confirmPassword);

    function completeRegistration(httpreq) {

        let jsonRegistrationResponse = JSON.parse(httpreq.responseText); /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/
        console.log(jsonRegistrationResponse.User);

        console.log(jsonRegistrationResponse.EmailStatus);

        if (jsonRegistrationResponse.EmailStatus === "InvalidEmail") {
            console.log("Invalid Email");
        }
        else {
            (jsonRegistrationResponse.EmailStatus === "ValidEmail")
            {
                console.log("Valid Email");
                // window.location.replace("/EduClick_war_exploded/Student/Student.html");


            }

        }

        if (jsonRegistrationResponse.User === "Teacher") {
            console.log("in if");
            // window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
        } else {
            (jsonRegistrationResponse.User === "Student")
            {
                console.log("in if");
                // window.location.replace("/EduClick_war_exploded/Student/Student.html");


            }

        }


    }
}
function checkInputs() {

    const firstName = document.getElementById("firstName").value;
    const firstNameError = document.getElementById("FirstNameError");

    const lastName = document.getElementById("lastName").value;
    const lastNameError = document.getElementById("LastNameError");

    const email = document.getElementById("email").value;
    const emailError = document.getElementById("EmailError");

    const DOB = document.getElementById("DOB").value;
    const DateOfBirthError = document.getElementById("DateOfBirthError");

    const city = document.getElementById("city").value;
    const cityError = document.getElementById("CityError");

    const password = document.getElementById("Password").value;
    const passwordError = document.getElementById("PasswordError");

    const confirmPassword = document.getElementById("confirmPassword").value;
    const confirmPasswordError = document.getElementById("ConfirmPasswordError");

/*    let genderSelect;
    const gender=document.getElementsByName("gender");
    const genderError = document.getElementsByName("GenderError");
    for(let i = 0;i<gender.length;i++){
        if(gender[i].checked){
            console.log(gender[i].value);
            genderSelect = gender[i].value;

        }

    }

 */



    let mobileNumber = document.getElementById("mobileNo").value;





    const firstNameValue = firstName.trim();
    const lastNameValue = lastName.trim();
    const emailValue = email.trim();
    const DOBValue=DOB.trim();
    const passwordValue=password.trim();
    const confirmPasswordValue=confirmPassword.trim();
 //   const mobileNumberValue = /^\d{15}$/;
    const cityValue = city.trim();


    //count age start here
    //get current year
    let today = new Date();
    let date = today.getFullYear();

    //get birth year
    let birthYear = DOB.substr(0,4);

    const age = date-birthYear;

    //end of count age

    if (firstNameValue === '') {
        firstNameError.style.display = "contents";
    }else {
        firstNameError.style.display = "none";
    }

    if (lastNameValue === '') {
        lastNameError.style.display = "contents";
    }else{
        lastNameError.style.display = "none";
    }

    if(emailValue === '') {
        emailError.innerHTML= "Enter your Email";
        emailError.style.display = "contents";

    } else if (!isEmail(emailValue)) {
        emailError.innerHTML= "Invalid Email";
        emailError.style.display = "contents";
    }else{
        emailError.style.display = "none";
    }

    if (DOBValue === '') {
        DateOfBirthError.style.display = "contents";
    }else if(age<13){
        DateOfBirthError.innerHTML= "Enter valid Birthday";
        DateOfBirthError.style.display = "contents";
    }
    else {
        DateOfBirthError.style.display = "none";
    }

    if (cityValue === '') {
        cityError.style.display = "contents";
    }else {
        cityError.style.display = "none";
    }


     //consider                                         *********************************
  /*  if(genderSelect.value === null){
        genderError.style.display = "contents";
    }else{
        genderError.style.display = "none";

   */

    if (mobileNumber === ''){
        document.getElementById("MobileNumberError").innerHTML="**Please enter Mobile Number";
        return false;

    }else if(isNaN(mobileNumber)){
        document.getElementById("MobileNumberError").innerHTML="Your Mobile Number is Invalid";
        return false;
    }
    else {
        document.getElementById("MobileNumberError").style.display = "none";
    }

    if (passwordValue === '') {
        passwordError.style.display = "contents";
    }
    else {
        passwordError.style.display = "none";
    }
    

    if (confirmPasswordValue === '') {
        confirmPasswordError.style.display = "contents";
    }else if(confirmPasswordValue!==passwordValue){
        confirmPasswordError.innerHTML= "Does not match with password";
        confirmPasswordError.style.display = "contents";
    } else {
        confirmPasswordError.style.display = "none";
    }


}

function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}




submitButton.onclick = function (){
    checkInputs();
  //  sendServerData();
}
