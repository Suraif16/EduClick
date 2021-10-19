const value = document.getElementById("searchBarText");

value.addEventListener( "keyup" , function (event){

    const values = document.getElementById("searchBarText").value;
    if(event.key === "Enter"){
        console.log(values);
        sendServerData(values);
    }

    //sendServerData(searchBarValue);

});

/*function searchForTeacher(){

    const searchBarValue = document.getElementById("searchBarText").value;

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeSearch( this ); /!*This is where we get the response when the request was successfully sent and a successfully response is received *!/
        }
    }
    /!* ************************ *!/
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchBarText=" + searchBarValue);

    function completeSearch(httpreq){

        console.log(httpreq.responseText);
        let jsonSearchResponse = JSON.parse(httpreq.responseText);

    }




}*/

const sendServerData = function (values){
    /* This function gets the username from the server*/

    console.log("values senddata :"+values)
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            let jsonLoginResponse = JSON.parse(httpreq.responseText);



            if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
                //window.location.replace("/EduClick_war_exploded/Login.html");
                console.log("amo amo")
            }else if(jsonLoginResponse.serverResponse === "Allowed") {
                console.log("AMoAMO AMO")

            }else{
                alert("something went wrong!!!");
            }
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("values=" + values);
    
}