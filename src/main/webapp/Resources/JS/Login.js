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

        let jsonLoginResponse = JSON.parse(httpreq.responseText); /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/
        console.log(jsonLoginResponse.User);
        if ( jsonLoginResponse.User === "Admin"){
            console.log("in if");
            window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");
        }
        else{
            alert("sorry");
        }

    }


}