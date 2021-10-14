let notificationStatus = false; /*if it is false the notification is hidden*/
const notifications = document.getElementById("notifications");

function showNotification(){

    console.log("hi bro")

    if(notificationStatus){

        notifications.style.display = "none";
        notificationStatus = false;

    }else{

        notifications.style.display = "flex";
        notificationStatus = true;

    }

}