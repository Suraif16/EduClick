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

}

verifyOTPButton.onclick = function (){
    /* again send user input to server and checks otp correction */
    const otpValue = document.getElementById("otp").value;
    console.log("value of otp" , otpValue);

}

resendOTPButton.onclick = function (){
    /* to regenerate a new OTP */
    generateOTP();

}
