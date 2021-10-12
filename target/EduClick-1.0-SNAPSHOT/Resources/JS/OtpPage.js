var timerOn = true;


function timer(remaining) {
    var btn = document.getElementById("resendOTP");
    if(remaining>=0) {
        btn.disabled = true;
        btn.style.backgroundColor = "gray";
        btn.style.cursor = "auto";

        var m = Math.floor(remaining / 60);
        var s = remaining % 60;

        m = m < 10 ? '0' + m : m;
        s = s < 10 ? '0' + s : s;
        document.getElementById('timer').innerHTML = m + ':' + s;
        remaining -= 1;


        if (remaining >= 0 && timerOn) {
            setTimeout(function () {
                timer(remaining);
            }, 1000);
            return;
        }



    }
    btn.disabled = false;
    btn.style.backgroundColor = "#4775c4";
    btn.style.cursor = "pointer";

    
}

    timer(300);




