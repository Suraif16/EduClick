const searchValue = document.getElementById("searchBarText");

searchValue.addEventListener( "keyup" , function (event){

    if(event.key === "Enter"){
        searchForTeacher(searchValue);
    }

});

function searchForTeacher(value) {
    console.log(value);
    console.log("ggg");
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeSearch( this );
        }
    }
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("value=" + value);
    console.log("ggghh" + value);


}
const completeSearch = function( httpreq ){

    let jsonResponse = JSON.parse( httpreq.responseText);

    if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
        window.location.replace("/EduClick_war_exploded/Login.html");
    }else if(jsonResponse.serverResponse === "Allowed") {
        /* This is where I need work everytime as per the authentication filter*/

        console.log("jsonResponse");

    }else{
        alert("something went wrong!!!");
    }



}

