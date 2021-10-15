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



        let status = sendInsertData(id);
        if(status=="Requested"){
            disableButton.style.display = "block";
            enableButton.style.display = "none";
        }




    }else if(enableButton.style.display == "none"){





        let status = sendDeleteData(id);
        if(status=="Deleted"){
            disableButton.style.display = "none";
            enableButton.style.display = "block";
        }



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
    return "Deleted";

}

let sendInsertData = function (id){
    let action = "request"
    sendData(id,action);
    console.log(id);
    return "Requested";
}

let sendData = function (id,action){
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            let jsonResponse = JSON.parse(httpreq.responseText);

            console.log(jsonResponse.serverResponse)
            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                //window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/

                console.log("Im hereeee")
                if(jsonResponse.Enroll === "Requested"){

                    console.log("hjksahdiuahdisd")
                    /*disableButton.style.display = "block";
                    enableButton.style.display = "none";*/

                    return "Requested";
                }
                else if(jsonResponse.Enroll === "Deleted"){
                    /*disableButton.style.display = "none";
                    enableButton.style.display = "block";*/

                    return "Deleted";
                }
                else if(jsonResponse.Enroll === "Already Enrolled"){
                    /*disableButton.style.display = "none";
                    enableButton.style.display = "block";*/

                    alert("Already Enrolled")
                }

                else{
                    console.log("Something went wrong!!");
                }

            }else{
                alert("something went wrong!!!");
            }
        }

    }

    httpreq.open("POST" ,"/EduClick_war_exploded/student/enrollRequest" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id +"&action=" + action);






}



