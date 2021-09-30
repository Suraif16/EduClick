

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        console.log("page loaded");
        sendServerData();
        console.log("page complete");

    }

}

const sendServerData = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherNewsFeedLoaded" , true);
    // httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            console.log( jsonLoginResponse.firstName , jsonLoginResponse.lastName)
        }else{
            alert("something went wrong!!!");
        }

    }


}