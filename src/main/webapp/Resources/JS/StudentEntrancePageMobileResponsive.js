let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let notificationStatus = false; /*if it is false the notification is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const notifications = document.getElementById("notifications");



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

