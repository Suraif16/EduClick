const verifyOTPButton = document.getElementById("verifyOTP");
const resendOTPButton = document.getElementById("resendOTP");
document.onreadystatechange = function (){
    /* runs when the DOM is laoded */
    if (document.readyState === 'complete'){
        generateOTP();
    }
}

const generateOTP = function (){
    /* calls a servlet to generate an OTP , Here I only send a resquest I don't wait for any responses*/

    let httpreq = new XMLHttpRequest();
    httpreq.open("POST" ,"/EduClick_war_exploded/otpGenerate" , true);
    httpreq.send()

}

verifyOTPButton.onclick = function (){
    /* again send user input to server and checks OTP correction */
    const otpValue = document.getElementById("OTP").value;
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200){

             responseComplete( this )
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/otpValidate" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("OPTUserValue=" + otpValue );

    const responseComplete = function ( httpreq ){
        /* Here if the OTP is valid then the user is sent to the relavent page*/
        let jsonObject = JSON.parse( httpreq.responseText )

        if ( jsonObject.OTPStatus === "valid"){

            if ( jsonObject.UserType === "Teacher" ){

                window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");

            }else if (jsonObject.UserType === "Student"){

                window.location.replace("/EduClick_war_exploded/Student/student.html");

            }

        }else if (jsonObject.OTPStatus === "invalid"){

            alert("Invalid OTP!!!");

        }else{

            alert("Something went wrong!!!");

        }

    }

}

resendOTPButton.onclick = function (){
    /* to regenerate a new OTP */
    generateOTP();
    timer(300);
}
