//document.getElementById("demo").innerHTML = 5;

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
        console.log(jsonLoginResponse.Teacher);
        //document.getElementById("demo").innerHTML = jsonLoginResponse.Teacher;

        let count = jsonLoginResponse.Teacher;
        document.getElementById("demo").innerHTML = count;
    }

}




