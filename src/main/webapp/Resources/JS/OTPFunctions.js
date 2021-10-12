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
    console.log("OTP GENERATED");
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (httpreq.readyState === 200 && httpreq.status ===4 ){
            console.log("server response 1");
        }

    }

    httpreq.open("POST" ,"/EduClick_war_exploded/otpGenerate" , true);
    httpreq.send()

}

verifyOTPButton.onclick = function (){
    /* again send user input to server and checks otp correction */
    const otpValue = document.getElementById("otp").value;
    console.log("value of otp" , otpValue);
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( httpreq.readyState === 200 && httpreq.status === 4){

            console.log("server response 2");

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/otpValidate" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("OPTUserValue=" + otpValue );
}

resendOTPButton.onclick = function (){
    /* to regenerate a new OTP */
    generateOTP();

}
