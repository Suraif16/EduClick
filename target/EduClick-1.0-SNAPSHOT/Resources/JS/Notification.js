let notificationStatus = false; /*if it is false the notification is hidden*/
const notifications = document.getElementById("notifications");
const notificationRequestButton = document.getElementById( "notificationRequest" );
let initialRequestCount = 0;

function showNotification(){

    console.log("hi bro how are you")

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
        console.log("a;dlskjf;a");
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
    console.log("hi hi hi farzan")
    let count = jsonResponse.requestList.length;

    if( count > 0 ){

        if( initialRequestCount < count ){
            initialRequestCount = count;
            notificationRequestButton.style.backgroundColor = "red";
            console.log("everytime")
            for(i = 0 ; i < count ; i++ ){

                let acceptFunction;
                let declineFunction;

                if(jsonResponse.requestList[i].type === "Enroll"){

                    acceptFunction = "EnrollRequestAccept" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;
                    declineFunction = "EnrollRequestDecline" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;

                }else{

                    acceptFunction = "FriendRequestAccept" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;
                    declineFunction = "FriendRequestDecline" + "(" + jsonResponse.requestList[i].fromId + "," +jsonResponse.requestList[i].toId + ")" ;

                }

                console.log(jsonResponse.requestList[i])

                request.innerHTML += '<div class="singleNotification">' +

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

                    '<div>'+

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

}

function EnrollRequestAccept(fromId , toId ){

    console.log( "Enroll request accepted" , fromId , toId );

}

function EnrollRequestDecline(fromId , toId ){

    console.log( "Enroll request Decline" , fromId , toId );

}

setInterval( getRequestData , 5000);

