const loginButton = document.getElementById("loginButton");
const passwordInput = document.getElementById("password");
const inCorrectPassword = document.getElementById("incorrectPassword");
let passwordIncorrectCount = 0;

const sendServerData = function (){
    inCorrectPassword.style.display = "none";
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/Login" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("Email=" + email + "&Password=" + password );

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText); /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/

        if ( jsonLoginResponse.User === "Admin"){

            window.location.replace("/EduClick_war_exploded/Admin/AdminHome-DashBoard.html");
        }
        else if ( jsonLoginResponse.User === "User"){
            if( jsonLoginResponse.otpSend === "True"){

                window.location.replace( "/EduClick_war_exploded/OtpPage.html");

            }
            else if ( jsonLoginResponse.Usertype === "Teacher"){
                window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
            }
             else if ( jsonLoginResponse.Usertype === "Student")
            {
                 window.location.replace("/EduClick_war_exploded/Student/student.html");
             }
            else{
                alert(" Something went wrong!");
            }
        }
        else if( jsonLoginResponse.User === "incorrect password"){

            inCorrectPassword.innerHTML = "Incorrect Password";
            inCorrectPassword.style.display = "flex";

            passwordIncorrectCount += 1;

            if (passwordIncorrectCount >= 3){

                // window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
                /*here if the password is incorrect the user will be sent to a different page to handle it*/
            }

        }
        else if( jsonLoginResponse.User === "User does not exist"){

            inCorrectPassword.innerHTML = "Email doesn't exist. Register before login.";
            inCorrectPassword.style.display = "flex";


        }
        else{
            alert(" Something went wrong!");
        }
    }


}

loginButton.onclick = function (){
    sendServerData();
}

passwordInput.addEventListener( "keyup" , function (event){

    if(event.key === "Enter"){
        sendServerData();
    }

});