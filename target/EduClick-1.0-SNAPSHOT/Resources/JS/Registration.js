const submitButton = document.getElementById("button");
console.log("Haaaaa!");

const sendServerData = function () {

    console.log("Hahahahahaha");

    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let dateOfBirth = document.getElementById("DOB").value;
    //let userTypeTeacher = document.getElementById("teacher").value;
    //let userTypeStudent = document.getElementById("student").value;

    var userType=document.getElementsByName("userType");
    for(i = 0;i<userType.length;i++){
        if(userType[i].checked){
            console.log(userType[i].value);
        }
    }
    let country = document.getElementById("country").value;
    let city = document.getElementById("city").value;
    let mobileNumber = document.getElementById("mobileNo").value;
    var gender=document.getElementsByName("gender");
    for(i = 0;i<gender.length;i++){
        if(gender[i].checked){
            console.log(gender[i].value);
        }
    }
    //let genderMale = document.getElementById("male").value;
    //let genderFemale = document.getElementById("female").value;
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;


  /* if(firstName==''){
        alert("First Name field cannot be empty!");
        return false;
    }
    if(lastName==''){
        alert("Last Name field cannot be empty!");
        return false;
    }
    if(email==''){
        alert("Email field cannot be empty!");
        return false;
    }



    if(country==''){
        alert("Country field cannot be empty!");
        return false;
    }
    if(city==''){
        alert("City field cannot be empty!");
        return false;
    }
    if(mobileNumber==''){
        alert("Mobile Number field cannot be empty!");
        return false;
    }
    if(mobileNumber.length>10){
        alert("Mobile Number cannot be more than 10 numbers!");
        return false;
    }*/





    console.log(firstName);
    console.log(lastName);
    console.log(email);
    console.log(dateOfBirth);
    //console.log(userTypeTeacher);
    //console.log(userTypeStudent);
    console.log(country);
    console.log(city);
    console.log(mobileNumber);
    //console.log(genderMale);
    //console.log(genderFeale);
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
    httpReq.send("firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&dateOfBirth=" + dateOfBirth + "&userType=" + userType + "&country=" + country + "&city=" + city + "&mobileNumber=" + mobileNumber + "&gender=" + gender + "&Password=" + password + "&confirmPassword=" + confirmPassword);

    function completeRegistration(httpreq) {

        let jsonRegistrationResponse = JSON.parse(httpreq.responseText); /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/
        console.log(jsonRegistrationResponse.User);
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

