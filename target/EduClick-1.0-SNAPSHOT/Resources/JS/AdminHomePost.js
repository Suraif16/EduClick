let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let addClassroomFormStatus = false; /*if it is false the addClassroomForm is hidden*/
let addClassroomFormErrorStatus = false; /* if it is false then classroomFormRowErrorMessage is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const addClassroomForm = document.getElementById("addClassroomForm");
const classroomFormRowErrorMessage = document.getElementById( "classroomFormRowErrorMessage" );
const submitButton = document.getElementById("postButton");

function showClassroomList(){
    if(classroomListStatus){
        classroomListObjection.style.display = "none";
        classroomListStatus = false;
    }else{
        classroomListObjection.style.display = "flex";
        classroomListStatus = true;
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
    let textMsg = document.getElementById("classroomName").value;


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/Admin" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("textMsg=" + textMsg );

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        /*here when we receive the response
        from the server, we convert it to JSON as it will be sent as JSON from the servlet.
        Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
        in the JSON object specified by the servlet*/

        /*if ( jsonLoginResponse.User === "Admin"){

            window.location.replace("/EduClick_war_exploded/Admin/AdminHome-DashBoard.html");
        }
        else{
            alert(" Something went wrong!");
        }*/console.log(jsonLoginResponse);
    }
}

submitButton.onclick = function (){
    sendServerData();
}