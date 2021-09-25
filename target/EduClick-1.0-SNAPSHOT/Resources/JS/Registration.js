const submitButton = document.getElementById("submitButton");

const sendServerData = function (){

    let firstName = document.getElementByName("firstName").value;
    let lastName = document.getElementByName("lastName").value;
    let email = document.getElementByName("email").value;
    let dateOfBirth = document.getElementByName("dateOfBirth").value;
    let userType = document.getElementByName("userType").value;
    let country = document.getElementByName("country").value;
    let city = document.getElementByName("city").value;
    let mobileNumber = document.getElementByName("mobileNumber").value;
    let gender = document.getElementByName("gender").value;
    let password = document.getElementByName("password").value;
    let confirmPassword = document.getElementByName("confirmPassword").value;

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeRegistration( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/Registration" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("firstName=" + firstName + "&lastName=" + lastName + "&email=" + email + "&dateOfBirth=" + dateOfBirth + "&userType=" + userType + "&country=" + country + "&city=" + city + "&mobileNumber=" + mobileNumber + "&gender=" + gender + "&Password=" + password + "&confirmPassword=" + confirmPassword );

    function completeRegistration( httpreq ){

        let jsonRegistrationResponse = JSON.parse(httpreq.responseText); /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/
        console.register(jsonRegistrationResponse.User);
        if ( jsonRegistrationResponse.User === "Teacher"){
            console.register("in if");
            // window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
        }
        else{
            ( jsonRegistrationResponse.User === "Student"){
                console.register("in if");
                // window.location.replace("/EduClick_war_exploded/Student/Student.html");


        }

    }


}

submitButton.onclick = function (){
    sendServerData();
}

