const verifyOTPButton = document.getElementById("verifyOTP");
const resendOTPButton = document.getElementById("resendOTP");
document.onreadystatechange = function (){
    /* runs when the DOM is laoded */
    if (document.readyState === 'complete'){
        generateOTP();
    }
}

const generateOTP = function (){
    /* calls a servlet to generate an OTP */
    let httpreq = new XMLHttpRequest();
    /*httpreq.onreadystatechange = function (){

        if (httpreq.readyState === 200 && httpreq.status ===4 ){



        }

    }*/

    httpreq.open("POST" ,"/EduClick_war_exploded/otpGenerate" , true);
    httpreq.send()

}

verifyOTPButton.onclick = function (){
    /* again send user input to server and checks otp correction */
    const otpValue = document.getElementById("otp").value;
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

}
