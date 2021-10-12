const submitButton = document.getElementById("button");
const passwordInput = document.getElementById("Password");
const passwordConfirmInput = document.getElementById("confirmPassword");

/*If there is an error in any of these then, these status are true*/

let firstNameErrorStatus = true;
let lastNameErrorStatus = true;
let emailErrorStatus = true;
let dobErrorStatus = true;
let userTypeErrorStatus = true;
let countryErrorStatus = true;
let cityErrorStatus = true;
let mobileNumberErrorStatus = true;
let genderErrorStatus = true;
let passwordErrorStatus = true;
let confirmPasswordErrorStatus = true;

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

            genderSelect = gender[i].value;

        }
    }

    let password = document.getElementById("Password").value;

    let confirmPassword = document.getElementById("confirmPassword").value;

    let newNumber = countryCode.concat(mobileNumber);

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

        if (jsonRegistrationResponse.EmailStatus === "InvalidEmail") {

            alert("Email alread exist!!!");

        }
        else if (jsonRegistrationResponse.EmailStatus === "ValidEmail"){

            window.location.replace("/EduClick_war_exploded/OtpPage.html");

        }else {

            alert("Something went wrong");

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

    const country = document.getElementById("country").value

    if (country != null){

        countryErrorStatus = false;

    }else{

        countryErrorStatus = false;

    }


    let mobileNumber = document.getElementById("mobileNo").value;

    let genderSelect = null;
    const gender=document.getElementsByName("gender");
    for(let i = 0;i<gender.length;i++){

        if(gender[i].checked ){

            genderSelect = gender[i].value;

        }

    }

    if(genderSelect == null){

        genderErrorStatus = true;
        document.getElementById("GenderError").innerHTML="**Please select your gender";

    }else{

        genderErrorStatus = false;
        document.getElementById("GenderError").style.display="none";

    }

    let userTypeSelect = null;
    let userType=document.getElementsByName("userType");

    for(let i = 0;i<userType.length;i++){

        if(userType[i].checked){

            userTypeSelect = userType[i].value;

        }

    }

    if (userTypeSelect == null){

        userTypeErrorStatus = true;
        document.getElementById("UserTypeError").innerHTML="**Please select who are you";

    }else{

        userTypeErrorStatus = false;
        document.getElementById("UserTypeError").style.display="none";
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


    if (firstNameValue === '' ) {
        firstNameErrorStatus = true;
        firstNameError.innerHTML="**Please enter your first Name";
        firstNameError.style.display = "contents";
    }else if((firstNameValue.match(/\d+/)!==null)){
        firstNameErrorStatus = true;
        firstNameError.innerHTML="**Please enter valid data";
        firstNameError.style.display = "contents";
    }else if(firstName.length>20){
        firstNameErrorStatus = true;
        firstNameError.innerHTML="**Do not enter more than 20 characters";
        firstNameError.style.display = "contents";
    }
    else {
        firstNameErrorStatus = false;
        firstNameError.style.display = "none";
    }

    if (lastNameValue === '') {
        lastNameErrorStatus = true;
        lastNameError.innerHTML="**Please enter your last Name";
        lastNameError.style.display = "contents";
    }else if((lastNameValue.match(/\d+/)!==null)){
        lastNameErrorStatus = true;
        lastNameError.innerHTML="**Please enter valid data";
        lastNameError.style.display = "contents";
    }else if(lastName.length>20){
        lastNameErrorStatus = true;
        lastNameError.innerHTML="**Do not enter more than 20 character";
        lastNameError.style.display = "contents";
    }else{
        lastNameErrorStatus = false;
        lastNameError.style.display = "none";
    }

    if(emailValue === '') {
        emailErrorStatus = true;
        emailError.innerHTML= "**Please enter your Email";
        emailError.style.display = "contents";


    }else if (!isEmail(emailValue)) {
        emailErrorStatus = true;
        emailError.innerHTML= "**Invalid Email";
        emailError.style.display = "contents";
    }else if(emailValue.length>320){
        emailErrorStatus = true;
        emailError.innerHTML = "**Do not enter more than 320 characters";
        emailError.style.display = "contents";
    }else{
        emailErrorStatus = false;
        emailError.style.display = "none";
    }

    if (DOBValue === '') {
        dobErrorStatus = true;
        DateOfBirthError.innerHTML= "**Please enter your Birthday";
        DateOfBirthError.style.display = "contents";

    }else if(age<13){
        dobErrorStatus = true;
        DateOfBirthError.innerHTML= "**Your age is not qualified for registration";
        DateOfBirthError.style.display = "contents";
        errorFlag = 1;

    }
    else {
        dobErrorStatus = false;
        DateOfBirthError.style.display = "none";
    }

    if (cityValue === '') {
        cityErrorStatus = true;
        cityError.innerHTML= "**Please enter your city";
        cityError.style.display = "content";
    }else if(cityValue.length>20){
        cityErrorStatus = true;
        cityError.innerHTML= "**Do not enter more than 20 characters";
        cityError.style.display = "content";
    }else {
        cityErrorStatus = false;
        cityError.style.display = "none";
    }

    if (mobileNumber === ''){
        mobileNumberErrorStatus = true;
        document.getElementById("MobileNumberError").innerHTML="**Please enter Mobile Number";


    }else if(isNaN(mobileNumber)){
        mobileNumberErrorStatus = true;
        document.getElementById("MobileNumberError").innerHTML="**Your Mobile Number is Invalid";
    }else if(mobileNumber.length>15){
        mobileNumberErrorStatus = true;
        document.getElementById("MobileNumberError").innerHTML="**Do not enter more than 15 numbers";
    }
    else {
        mobileNumberErrorStatus = false;
        document.getElementById("MobileNumberError").style.display = "none";
    }

    if (password === '') {
        passwordErrorStatus = true;
        document.getElementById("PasswordError").innerHTML="**Please enter a password";

    }
    else {
        mobileNumberErrorStatus = false;
        passwordError.style.display = "none";
    }

    if (confirmPassword === '') {
        confirmPasswordErrorStatus = true;
        confirmPasswordError.innerHTML = "**Please re-enter your password here";
    } else {
        confirmPasswordErrorStatus = false;
        confirmPasswordError.style.display = "none";
    }

}


function isEmail(email) {
    return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

submitButton.onclick = function (){

    checkInputs();

    if( !( firstNameErrorStatus || lastNameErrorStatus || emailErrorStatus || dobErrorStatus || userTypeErrorStatus || countryErrorStatus || cityErrorStatus || mobileNumberErrorStatus || genderErrorStatus || passwordErrorStatus || confirmPasswordErrorStatus) ) {

        sendServerData();

    }
}

passwordInput.addEventListener( "keyup" , function (event){

    const passwordIn = document.getElementById("Password").value;
    const x = passwordIn.length;

    if(x>=8){
        passwordErrorStatus = false;
        document.getElementById("PasswordError").style.visibility="hidden";
    }
    else{
        passwordErrorStatus = true;
        document.getElementById("PasswordError").style.visibility="visible";
        document.getElementById("PasswordError").innerHTML="Your Password must contain 8 characters";
    }

});
passwordConfirmInput.addEventListener( "keyup" , function (event){

    const password = document.getElementById("Password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const confirmPasswordError = document.getElementById("ConfirmPasswordError");

    if( password !== confirmPassword || passwordErrorStatus ){
        confirmPasswordErrorStatus = true;
        confirmPasswordError.innerHTML = "**Does not match with above password";

    }else{
        confirmPasswordErrorStatus = false;
        confirmPasswordError.innerHTML = "**You have successfully enter the password";
        confirmPasswordError.style.color="blue";
    }

});
