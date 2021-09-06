const loginButton = document.getElementById("loginButton");


loginButton.onclick = function (){

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
        let loginResponse = httpreq.responseText.toString();
        console.log(loginResponse);
        /*if ( loginResponse === "true"){
            console.log("in if");*/
            window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
        /*}
        else{
            alert("sorry");
        }*/
    }


}