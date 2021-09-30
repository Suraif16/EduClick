import {serverRequests} from "serverRequests.js";

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        console.log("page loaded");
        serverRequests( "POST" , "/teacher/teacherNewsFeedLoaded");
        console.log("page complete");

    }

}