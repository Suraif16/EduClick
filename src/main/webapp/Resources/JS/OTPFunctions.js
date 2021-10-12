const verifyOTPButton = document.getElementById("verifyOTP");
const resendOTPButton = document.getElementById("resendOTP");
document.onreadystatechange = function (){

    if (document.readyState === 'complete'){
        generateOTP();
    }
}

const generateOTP = function (){

    console.log("OTP GENERATED");

}

verifyOTPButton.onclick = function (){

    const otpValue = document.getElementById("otp").value;
    console.log("value of otp" , otpValue);

}

resendOTPButton.onclick = function (){

    generateOTP();

}
