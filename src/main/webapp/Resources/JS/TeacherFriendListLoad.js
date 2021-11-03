document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        LoadUserName();

    }

}


const LoadUserName = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherProfileNameLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const headerName = document.getElementById("teacherUserNameHeader");
            headerName.innerHTML = jsonLoginResponse.FullName;
            console.log(headerName);

        }else{
            alert("something went wrong!!!");
        }

    }


}

const loadTeacherFriendList = function (){

}