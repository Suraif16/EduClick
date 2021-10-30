const loadFollowersList = function (){
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/student/studentFollowerListLoad", true);
    httpreq.send();

    function completeLoad(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            console.log("Onna mama awa hehehehehe");

            let count = jsonLoginResponse.List.length - 1;
            for (i = 0 ; i <= count ; i++){
                console.log(jsonLoginResponse.List[i].firstName);
                console.log(jsonLoginResponse.List[i].lastName);
                console.log(jsonLoginResponse.List[i].UserID);

                classroomHtmlOutput(jsonLoginResponse.List[i].UserID,jsonLoginResponse.List[i].lastName,jsonLoginResponse.List[i].firstName);

            }
        }else{
            alert("something went wrong!!!");
        }
    }

    function classroomHtmlOutput( userId , firstName , lastName ){
        postContents = document.getElementById("postContents");
        postContents.innerHTML = "";



    }


}
