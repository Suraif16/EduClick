
document.onreadystatechange = function(){

    if(document.readyState === 'complete'){
        console.log("ClassroomListLoad");
        getClassroomList();

    }

}

const getClassroomList = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoadClassroomList" , true);
    httpreq.send();

    function complete( httpreq ){

        let jsonResponse = JSON.parse(httpreq.responseText);

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/

            console.log(jsonResponse.UserObject);
            console.log(jsonResponse.UserObject.city);

        }else{
            alert("something went wrong!!!");
        }

    }


}