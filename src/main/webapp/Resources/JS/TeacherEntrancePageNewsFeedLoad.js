document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        LoadTUserName();
        sendServerDataTeacher();
        console.log("kkk");

    }

}

const sendServerDataTeacher = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLog( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherEntrancePageNewsFeedsLoad" , true);
    httpreq.send();


    function completeLog( httpreq ){

       // const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const headerName = document.getElementById("headerUserName");
            headerName.innerHTML = jsonLoginResponse.FullName;
            console.log(headerName);

        }else{
            alert("something went wrong!!!");
        }

    }
console.log("aaaa");

}


const LoadTUserName = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherProfileNameLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const headerName = document.getElementById("headerUserName");
            headerName.innerHTML = jsonLoginResponse.FullName;
            console.log(headerName);

        }else{
            alert("something went wrong!!!");
        }

    }


}

