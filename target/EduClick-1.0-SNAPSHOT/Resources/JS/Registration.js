const submitButton = document.getElementById("button");
const passwordInput = document.getElementById("Password");
const passwordConfirmInput = document.getElementById("confirmPassword");
let errorFlag = 0;


const sendServerData = function () {

    let firstName = document.getElementById("firstName").value;

    let lastName = document.getElementById("lastName").value;

    let email = document.getElementById("email").value;

    let dateOfBirth = document.getElementById("DOB").value;

    let today = new Date();
    let date = today.getFullYear();

    let birthYear = dateOfBirth.substr(0,4);

    const age = date-birthYear;


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

    if(firstName && lastName && email && dateOfBirth && country && city && newNumber &&  password && confirmPassword && age>13){

        if(password.length == confirmPassword.length  && password == confirmPassword && password.length>=8) {

            httpReq.send("firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&dateOfBirth=" + dateOfBirth + "&userTypeSelect=" + userTypeSelect + "&country=" + country + "&city=" + city + "&newNumber=" + newNumber + "&genderSelect=" + genderSelect + "&Password=" + password + "&confirmPassword=" + confirmPassword);
        }
    }



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
        if(jsonRegistrationResponse.Reg === "Done"){
            window.location.replace("/EduClick_war_exploded/OtpPage.html");
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

    let mobileNumber = document.getElementById("mobileNo").value;

    let genderSelect;
    const gender=document.getElementsByName("gender");
    for(let i = 0;i<gender.length;i++){
        if(gender[i].checked ){
            console.log(gender[i].value);
            genderSelect = gender[i].value;
            document.getElementById("GenderError").style.display="none";
        }
        else if(gender[i].checked===false){
            document.getElementById("GenderError").innerHTML="**Please select your gender";

        }

    }

    let userTypeSelect;
    let userType=document.getElementsByName("userType");
    for(let i = 0;i<userType.length;i++){
        if(userType[i].checked){
            console.log(userType[i].value);
            userTypeSelect = userType[i].value;
            document.getElementById("UserTypeError").style.display="none";
        }
        else if(userType[i].checked===false){
            document.getElementById("UserTypeError").innerHTML="**Please select who are you";

        }

    }

    const firstNameValue = firstName.trim();
    const lastNameValue = lastName.trim();
    const emailValue = email.trim();
    const DOBValue=DOB.trim();

    const cityValue = city.trim();

    let today = new Date();
    let date = today.getFullYear();

    let birthYear = DOB.substr(0,4);

    const age = date-birthYear;

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
        emailError.innerHTML= "**Enter your Email";
        emailError.style.display = "contents";


    } else if (!isEmail(emailValue)) {
        emailError.innerHTML= "**Invalid Email";
        emailError.style.display = "contents";

    }else{
        emailError.style.display = "none";
    }

    if (DOBValue === '') {
        DateOfBirthError.style.display = "contents";

    }else if(age<13){
        DateOfBirthError.innerHTML= "**Your age is not qualified for registration";
        DateOfBirthError.style.display = "contents";
        errorFlag = 1;

    }
    else {
        DateOfBirthError.style.display = "none";
    }

    if (cityValue === '') {
        cityError.style.display = "contents";

    }else {
        cityError.style.display = "none";
    }

    if (mobileNumber === ''){
        document.getElementById("MobileNumberError").innerHTML="**Please enter Mobile Number";


    }else if(isNaN(mobileNumber)){
        document.getElementById("MobileNumberError").innerHTML="**Your Mobile Number is Invalid";

        return false;
    }
    else {
        document.getElementById("MobileNumberError").style.display = "none";
    }

    if (password === '') {
        document.getElementById("PasswordError").innerHTML="**Enter a password";
       // passwordError.style.display = "contents";
        errorFlag = 1;
    }
    else {
        passwordError.style.display = "none";
    }

    if (confirmPassword === '') {
        confirmPasswordError.innerHTML = "**Re-enter your password here";
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
    sendServerData();
}
passwordInput.addEventListener( "keyup" , function (event){

    const passwordIn = document.getElementById("Password").value;
    const x = passwordIn.length;

    if(x>=8){
        document.getElementById("PasswordError").style.visibility="hidden";
    }
    else{
        document.getElementById("PasswordError").style.visibility="visible";
        document.getElementById("PasswordError").innerHTML="Your Password must contain 8 characters";
    }

});
passwordConfirmInput.addEventListener( "keyup" , function (event){

    const password = document.getElementById("Password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const confirmPasswordError = document.getElementById("ConfirmPasswordError");

    if(password!==confirmPassword){
        confirmPasswordError.innerHTML = "**Does not match with above password";
        confirmPasswordError.style.display = "contents";

    }else{
        confirmPasswordError.innerHTML = "**You have successfully enter the password";
        confirmPasswordError.style.display = "contents";
        confirmPasswordError.style.color="blue";
    }
    }
);