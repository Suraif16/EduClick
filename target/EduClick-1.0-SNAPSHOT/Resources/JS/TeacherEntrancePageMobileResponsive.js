let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let notificationStatus = false; /*if it is false the notification is hidden*/
let addClassroomFormStatus = false /*if it is false the addClassroomForm is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const search = document.getElementById( "searchBarText" );
const notifications = document.getElementById("notifications");
const addClassroomForm = document.getElementById("addClassroomForm");

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

function showAddClassroomFrom(){

    if(addClassroomFormStatus){

        addClassroomForm.style.display = "none";
        addClassroomFormStatus = false;

    }else {

        addClassroomForm.style.display = "flex";
        addClassroomFormStatus = true;

    }

}