searchValue.addEventListener( "keyup" , function (event){

    console.log(searchBarValue);
    sendServerData(searchBarValue);

});

/*function searchForTeacher(){

    const searchBarValue = document.getElementById("searchBarText").value;


    if(event.key === "Enter"){
        searchForTeacher(searchValue);
    }

});

function searchForTeacher(searchValue) {


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){

            completeSearch( this ); /!*This is where we get the response when the request was successfully sent and a successfully response is received *!/
        }
    }
    /!* ************************ *!/
=======

            completeSearch( this );
        }
    }
>>>>>>> main
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchValue=" + searchValue);


}
const completeSearch = function( httpreq ){

    let jsonResponse = JSON.parse( httpreq.responseText);

    if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
        window.location.replace("/EduClick_war_exploded/Login.html");
    }else if(jsonResponse.serverResponse === "Allowed") {
        /* This is where I need work everytime as per the authentication filter

        console.log("jsonResponse");

    }else{
        alert("something went wrong!!!");
    }

    

}



}*/

const sendServerData = function (value){
    /* This function gets the username from the server*/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("value=" + value);

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            console.log(jsonLoginResponse);
            /* This is where I need work everytime as per the authentication filter*/
            console.log(jsonLoginResponse.firstName);
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
        }else{
            alert("something went wrong!!!");
        }

    }


}
