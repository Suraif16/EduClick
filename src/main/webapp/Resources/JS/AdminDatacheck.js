const search = document.getElementById( "searchBarText" );

search.addEventListener( "keyup" , function ( event ){
    if(event.key === "Enter"){
        let httpreq = new XMLHttpRequest();
        httpreq.onreadystatechange = function (){
            if ( httpreq.readyState === 4 && httpreq.status === 200){
                window.location.replace("/EduClick_war_exploded/Search.html")
            }
        }
        let url = "/EduClick_war_exploded/Search?searchValue="+search.value+"&searchType=Teacher";
        httpreq.open( "GET" , url ,true);
        httpreq.send();
    }
});

document.onreadystatechange = function (){
    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        console.log("page loaded");
        sendServerData();
        console.log("page complete");
    }
}
const sendServerData = function (){

    let httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            completecount(this) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }
    httpReq.open( "GET" , "/EduClick_war_exploded/Admin" , true);
    httpReq.send();


    function completecount( httpreq ) {
        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            document.getElementById("countTotal").innerHTML = jsonLoginResponse.counttotal;
            console.log(jsonLoginResponse.counttotal);

        }else{
            alert("something went wrong!!!");
        }

    }

}


