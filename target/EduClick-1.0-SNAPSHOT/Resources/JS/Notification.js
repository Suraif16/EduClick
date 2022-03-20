let notificationStatus = false; /*if it is false the notification is hidden*/
const notifications = document.getElementById("notifications");
const notificationRequestButton = document.getElementById( "notificationRequest" );
let initialRequestCount = 0;

function showNotification(){
    /* when clicked on the notification button this function will hide and show the notification pannel*/
    if(notificationStatus){

        notifications.style.display = "none";
        notificationStatus = false;

    }else{
        notificationRequestButton.style.backgroundColor = "#4775c4";
        notifications.style.display = "flex";
        notificationStatus = true;

    }

}

const getRequestData = function(){
    /*Gets the set of requests from the server*/
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if( httpreq.readyState === 4 && httpreq.status === 200){

            serverResponseComplete( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/displayRequest" , true);
    httpreq.send();

    const serverResponseComplete = function( httpreq ){


        let jsonResponse = JSON.parse( httpreq.responseText);

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/

            displayRequest( jsonResponse );

        }else{
            alert("something went wrong!!!");
        }




    }


}

const displayRequest = function ( jsonResponse ){

    const request = document.getElementById("request");

    let count = jsonResponse.requestList.length;
    /* this checks are there any request?*/
    if( count > 0 ){
        /* this checks are there any new requests? , if so prints*/
        if( initialRequestCount < count ){
            initialRequestCount = count;
            notificationRequestButton.style.backgroundColor = "red";
            console.log(jsonResponse.requestList)
            for(i = 0 ; i < count ; i++ ){

                let acceptFunction;
                let declineFunction;
                /* according to the type of the request when user clicks on accept or decline buttons the relavent function should run ,
                * here this creates the acceptFucntion and decline function accrodingly*/
                if(jsonResponse.requestList[i].type === "Enroll"){

                    acceptFunction = "EnrollRequestAccept" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;
                    declineFunction = "EnrollRequestDecline" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;

                }else{

                    acceptFunction = "FriendRequestAccept" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;
                    declineFunction = "FriendRequestDecline" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;

                }

                /* This is where the each request element is created*/

                request.innerHTML += '<div class="singleNotification" id="'+ jsonResponse.requestList[i].type + jsonResponse.requestList[i].fromId + "" + jsonResponse.requestList[i].toId +'">' +

                    '<div>' +

                    '<a href="TeacherProfile.html?id='+ jsonResponse.requestList[i].fromId +'"'+' class="profile">' +

                    '<div class="profileImage requestProfilePicture">'+

                    '<img class="profileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +

                    '</div>' +

                    '<div class="requestProfileName">' + jsonResponse.requestList[i].userName + '</div>' +

                    '</a>' +

                    '</div>' +

                    '<div>' +

                    ' '+ jsonResponse.requestList[i].description +

                    '</div>'+

                    '<div id="buttons' + jsonResponse.requestList[i].type + jsonResponse.requestList[i].fromId + "" + jsonResponse.requestList[i].toId + '">' +

                    '<input type="button" value="Accept" onclick="' + acceptFunction + '"' +'>'+

                    '<input type="button" value="Decline" class="requestDecline" onclick="' + declineFunction + '"' +'>'+

                    '</div>' +

                    '</div>';


            }

        }

    }

}

function FriendRequestAccept( fromId , toId ){

    console.log( "friend request accepted" , fromId , toId );

}

function FriendRequestDecline(fromId , toId ){

    console.log( "friend request Decline" , fromId , toId );
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status === 200){

            let jsonResponse = JSON.parse( this.responseText );

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/
                const singleNotificaiton = document.getElementById( "Friend" + fromId + "" + toId );

                singleNotificaiton.style.display = "none";
                declineFriendRequestActivityStatus();

            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/user/cancelFriendRequest" , true );
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send( "fromId=" + fromId + "&toId=" + toId );

}

function EnrollRequestAccept( fromId , toId ){

    console.log( "Enroll request accepted" , fromId , toId );
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status === 200){

            let jsonResponse = JSON.parse( this.responseText );

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/
                console.log( "enroll status : " , jsonResponse.enrollStatus )
                const singleNotificaiton = document.getElementById( "Enroll" + fromId + "" + toId );

                if ( jsonResponse.enrollStatus === true ){

                    singleNotificaiton.style.display = "none";
                    acceptEnrollRequestActivityStatus();


                }else {

                    alert( "something went wrong")

                }


            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/AcceptEnrollRequest" , true );
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send( "fromId=" + fromId + "&toId=" + toId );

}

function EnrollRequestDecline(fromId , toId ){

    console.log( "Enroll request Decline" , fromId , toId );

}
/*every function seconds the getRequestData function is called*/
setInterval( getRequestData , 5000);

