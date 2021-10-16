const searchValue = document.getElementById("searchBarText");

searchValue.addEventListener( "keyup" , function (event){

    const searchBarValue = document.getElementById("searchBarText").value;
    console.log(searchBarValue);
    searchForTeacher();

});

function searchForTeacher() {

    const searchBarValue = document.getElementById("searchBarText").value;
    console.log(searchBarValue);

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            console.log("hh");
            completeSearch( this );
        }
    }
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchBarValue=" + searchBarValue);
    console.log("bbb");

}
const completeSearch = function( httpreq ){


    let jsonResponse = JSON.parse( httpreq.responseText);

    if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
        window.location.replace("/EduClick_war_exploded/Login.html");
    }else if(jsonResponse.serverResponse === "Allowed") {
        /* This is where I need work everytime as per the authentication filter*/

        displayRequest( jsonResponse );

    }else{
        alert("something went wrong!!!");
    }

    

}

