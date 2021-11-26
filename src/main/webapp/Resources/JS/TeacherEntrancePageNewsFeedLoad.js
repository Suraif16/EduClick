document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        sendServerDataTeacher();
        console.log("kkk");

    }

}

const sendServerDataTeacher = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherEntrancePageNewsFeedsLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;
            console.log(url);
            headerUserProfileIdAchorElement.setAttribute("href" , url);
        }else{
            alert("something went wrong!!!");
        }

    }
console.log("aaaa");

}