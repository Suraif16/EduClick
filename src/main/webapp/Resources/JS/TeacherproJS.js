let rightPanelStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const rightPanel = document.getElementById("rightPanel");

function showRightPanel(){

    if(rightPanelStatus){

        rightPanel.style.display = "none";
        rightPanelStatus = false;

    }else{

        rightPanel.style.display = "flex";
        rightPanelStatus = true;

    }

}


function enableDisableStatus( id ){

    let enableStringValue = "enable" + id;

    let disableStringValue = "disable" + id;

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);



    if (disableButton.style.display === "none"){

        /*defaultView.getComputedStyle(enableButton)*/

        sendInsertData(id);




    }else if(enableButton.style.display == "none"){



        sendDeleteData(id);



        console.log("Deleting!!!!")
    }
    else {
        console.log("Something went wrong!!");
    }

}

/*let sendInsertData = function (id){
    let httpreq = new XMLHttpRequest();
    httpreq.open("POST" ,"/EduClick_war_exploded/student/enrollRequest" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id);
    console.log(id);
}

let sendDeleteData = function (id){
    console.log("Deleting!!!!")
}*/

let sendDeleteData = function (id){
    let action = "delete"
    sendData(id,action);
    console.log("Action is : "+action);

}

let sendInsertData = function (id){
    let action = "request"
    sendData(id,action);
    console.log(id);
}

let sendData = function (id,action){
    let httpreq = new XMLHttpRequest();
    httpreq.open("POST" ,"/EduClick_war_exploded/student/enrollRequest" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id +"&action=" + action);

    let jsonnResponse = JSON.parse(httpreq.responseText);

    if(jsonResponse.Enroll === "Requested"){
        disableButton.style.display = "block";
        enableButton.style.display = "none";
    }
    else if(jsonResponse.Enroll === "Deleted"){
        disableButton.style.display = "none";
        enableButton.style.display = "block";
    }
    else{
        console.log("Something went wrong!!");
    }
}



