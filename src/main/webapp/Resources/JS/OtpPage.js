var timerOn = true;
var count =0;


function timer(remaining) {
    if(count<3){
        var m = Math.floor(remaining / 60);
        var s = remaining % 60;

        m = m < 10 ? '0' + m : m;
        s = s < 10 ? '0' + s : s;
        document.getElementById('timer').innerHTML = m + ':' + s;
        remaining -= 1;

        if(remaining >= 0 && timerOn) {
            setTimeout(function() {
                timer(remaining);
            }, 1000);
            return;
        }

        if(!timerOn) {
            // Do validate stuff here
        }

        // Do timeout stuff here
        var btn = document.getElementById("resendOTP");
        btn.disabled = false;
        if(btn.enable = true){
            btn.style.backgroundColor = "#4775c4";
            btn.style.cursor = "pointer";
        }
        count++;
        console.log(count)
    }else{
        var btn = document.getElementById("resendOTP");
        btn.disabled = true;
        if(btn.disabled==true){
            btn.style.backgroundColor = "gray";
            btn.style.cursor = "auto";
        }
    }






    //alert('Timeout for OTP');
}

    timer(120);




