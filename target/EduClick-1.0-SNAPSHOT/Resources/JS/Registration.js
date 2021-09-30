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

    let countrCode = document.getElementById("countryCode").value;

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

    let newNumber = countrCode.concat(mobileNumber);


    console.log(firstName);
    console.log(lastName);
    console.log(email);
    console.log(dateOfBirth);
    console.log(country);
    console.log(city);
    console.log(countrCode);
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

submitButton.onclick = function (){
    sendServerData();
}

