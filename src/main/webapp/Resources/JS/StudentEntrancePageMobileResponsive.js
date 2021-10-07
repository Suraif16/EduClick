let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let notificationStatus = false; /*if it is false the notification is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const search = document.getElementById( "searchBarText" );
const notifications = document.getElementById("notifications");

search.addEventListener( "keyup" , function ( event ){

    if(event.key === "Enter"){

        console.log(search.value);

    }

});

function showClassroomList(){

    if(classroomListStatus){

        classroomListObjection.style.display = "none";
        classroomListStatus = false;

    }else{

        classroomListObjection.style.display = "flex";
        classroomListStatus = true;

    }

}

function showNotification(){

    console.log("hi")

    if(notificationStatus){

        notifications.style.display = "none";
        notificationStatus = false;

    }else{

        notifications.style.display = "flex";
        notificationStatus = true;

}

}
document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
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

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentNewsFeedLoaded" , true);
    httpreq.send();

    function completeLogin( httpreq ){


        let jsonResponse = JSON.parse(httpreq.responseText);
        let arr = new Array();
        arr = jsonResponse;


        /*if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        } if(jsonLoginResponse.serverResponse === "Allowed") {
            /!* This is where I need work everytime as per the authentication filter*!/
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            console.log( jsonLoginResponse.firstName , jsonLoginResponse.lastName);
        }else{
            alert("something went wrong!!!");
        }*/


            /*console.log(arr[0].values().value())*/





    }




}