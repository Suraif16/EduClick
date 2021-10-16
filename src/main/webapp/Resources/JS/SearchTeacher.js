const searchValue = document.getElementById("searchBarText").value;

searchValue.addEventListener( "keyup" , function (event){

   // const searchBarValue = document.getElementById("searchBarText").value;
   // console.log(searchBarValue);
    if(event.key === "Enter"){
        searchForTeacher(searchValue);
    }

});

function searchForTeacher(searchValue) {

   // let searchBarValue = document.getElementById("searchBarText").value;


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){

            completeSearch( this );
        }
    }
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchValue=" + searchValue);


}
const completeSearch = function( httpreq ){
    console.log("b123bb");

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

