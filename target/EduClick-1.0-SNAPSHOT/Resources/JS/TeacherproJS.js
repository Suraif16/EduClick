let rightPanelStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const rightPanel = document.getElementById("rightPanel");

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        LoadName();

    }

}

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

    let requestedStringValue = "requested" + id

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);

    let requestedButton = document.getElementById(requestedStringValue);



    if (disableButton.style.display === "none" && requestedButton.style.display == "none"){

        /*defaultView.getComputedStyle(enableButton)*/



         sendInsertData(id);
        /*if(status=="Requested"){
            disableButton.style.display = "block";
            enableButton.style.display = "none";
        }*/




    }else if(enableButton.style.display == "none" && requestedButton.style.display == "none"){





        sendDeleteData(id);
        /*if(status=="Deleted"){
            disableButton.style.display = "none";
            enableButton.style.display = "block";
        }*/



        console.log("Deleting!!!!")
    }
    else if(disableButton.style.display === "none" && enableButton.style.display == "none"){

       sendRequestData(id);

    }
    else{
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

let sendRequestData = function (id){
    let action = "apply"
    sendData(id,action);
    console.log(id);
}

let sendData = function (id,action){

    let enableStringValue = "enable" + id;

    let disableStringValue = "disable" + id;

    let requestedStringValue = "requested" + id

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);

    let requestedButton = document.getElementById(requestedStringValue);

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
                if(jsonResponse.Enroll_Status === "Enrolled"){

                    console.log("hjksahdiuahdisd")

                    disableButton.style.display = "block";
                    enableButton.style.display = "none";
                    requestedButton.style.display = "none";

                    /*disableButton.style.display = "block";
                    enableButton.style.display = "none";*/
                    /*disableButton.style.display = "block";
                    enableButton.style.display = "none";*/

                    /*return "Requested";*/
                }
                else if(jsonResponse.Enroll_Status === "Enroll Requested"){
                    /*disableButton.style.display = "none";
                    enableButton.style.display = "block";*/
                    /*disableButton.style.display = "none";
                    enableButton.style.display = "block";*/

                    disableButton.style.display = "none";
                    enableButton.style.display = "none";
                    requestedButton.style.display = "block";


                    /*return "Deleted";*/
                }
                else {
                    disableButton.style.display = "none";
                    enableButton.style.display = "block";
                    requestedButton.style.display = "none";
                }
            }
                /*else if(jsonResponse.Enroll === "Already Enrolled"){
                    /!*disableButton.style.display = "none";
                    enableButton.style.display = "block";*!/

                    alert("Already Enrolled")
                }
                else if(jsonResponse.Enroll === "No Enrollment"){
                    /!*disableButton.style.display = "none";
                    enableButton.style.display = "block";*!/

                    alert("No Enrollment found in the database");
                }

                else{
                    console.log("Something went wrong!!");
                }

            }else{
                alert("something went wrong!!!");
            }*/
        }

    }

    httpreq.open("POST" ,"/EduClick_war_exploded/enrollRequest" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id +"&action=" + action);



}

//
// const LoadName = function (){
//
//     let httpreq = new XMLHttpRequest();
//     httpreq.onreadystatechange = function (){
//
//         if (this.readyState === 4 && this.status === 200){
//             completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
//         }
//
//     }
//
//     httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherProfileNameLoad" , true);
//     httpreq.send();
//
//     function completeLogin( httpreq ){
//
//         let jsonLoginResponse = JSON.parse(httpreq.responseText);
//
//         if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
//             window.location.replace("/EduClick_war_exploded/Login.html");
//         }else if(jsonLoginResponse.serverResponse === "Allowed") {
//
//             const name = document.getElementById("teacherUserName");
//             name.innerHTML = jsonLoginResponse.FullName;
//             console.log(name);
//
//             const headerName = document.getElementById("teacherUserNameHeader");
//             headerName.innerHTML = jsonLoginResponse.FullName;
//             console.log(headerName);
//
//         }else{
//             alert("something went wrong!!!");
//         }
//
//     }
//
//
// }

/*document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /!* when the document is loaded and complete this function will run*!/
        sendServerData();


    }

}

const sendServerData = function (){
    /!* This function gets the username from the server*!/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /!*This is where we get the response when the request was successfully sent and a successfully response is received *!/
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            console.log(jsonLoginResponse);
            /!* This is where I need work everytime as per the authentication filter*!/
            console.log(jsonLoginResponse.firstName);
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
        }else{
            alert("something went wrong!!!");
        }

    }


}*/

const search1 = document.getElementById( "searchBarText" );

search.addEventListener( "keyup" , function ( event ){

    if(event.key === "Enter"){


        let httpreq = new XMLHttpRequest();

        httpreq.onreadystatechange = function (){

            if ( httpreq.readyState === 4 && httpreq.status === 200){

                window.location.replace("/EduClick_war_exploded/Search.html")

            }
        }
        let url = "/EduClick_war_exploded/Search?searchValue="+search1.value+"&searchType=Teacher";
        httpreq.open( "GET" , url ,true);
        httpreq.send();

    }

});



const LoadName = function (){


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherProNameLoad" , true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + getUserIdClientSide());

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const headerName = document.getElementById("profileUserName");
            headerName.innerHTML = jsonLoginResponse.FullName;


        }else{
            alert("something went wrong!!!");
        }

    }


}
