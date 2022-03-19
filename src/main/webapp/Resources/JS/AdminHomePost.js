let addClassroomFormStatus = true; /*if it is false the addClassroomForm is hidden*/
let addClassroomFormErrorStatus = false; /* if it is false then classroomFormRowErrorMessage is hidden*/

const addClassroomForm = document.getElementById("addNewsFeedForm");
const classroomFormRowErrorMessage = document.getElementById( "classroomFormRowErrorMessage" );


function showAddClassroomFrom(){
    if(addClassroomFormStatus){
        addClassroomForm.style.display = "none";
        addClassroomFormStatus = false;
    }else {
        addClassroomForm.style.display = "flex";
        addClassroomFormStatus = true;
    }
}

function showClassroomFormRowErrorMessage( message ){
    if(addClassroomFormErrorStatus){
        classroomFormRowErrorMessage.style.display = "none";
        addClassroomFormErrorStatus = false;
    }else {
        classroomFormRowErrorMessage.style.display = "flex";
        classroomFormRowErrorMessage.innerHTML = message;
        addClassroomFormErrorStatus = true;
    }
}



const sendServerData = function (){
    let textMsg = document.getElementById("addNewsFeedFormTextArea").value;


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/AdminPost" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("textMsg=" + textMsg );

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const request = document.getElementById("request");
            request.innerHTML += '<div class="post">' +
                '<div class="postContentContainer">'+
                '<div class="postProfileSection">'+
                '<a href="#" class="postProfile">'+
                '<div class="postProfileImage">'+
                '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                '</div>'+
                '<div class="postProfileName" >Admin</div>'+
                '<div class="postTimeAndDate">18:32:26 | 03/25/2015</div>'+
                '</a>'+
                '</div>'+
                '</div>'+
                '<div class="postContentContainer">'+
                '<div class="postData">'+
                '<div class="postMessage">'+
                textMsg+
                '</div>'+
                '</div>'+
                '</div>'+
                '</div>';
            classroomName.value = "";
            addClassroomForm.style.display = "none";
            addClassroomFormStatus = false;
        }else{
            alert("something went wrong!!!");
        }


    }
}

const postQuestionsMessages = function (){
    sendServerData();
    showAddClassroomFrom();
}