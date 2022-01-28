
function checkdata(){
    let search = document.getElementById( "comment" ).value;
    console.log(search);
    document.onreadystatechange = function (){
        if ( document.readyState === 'complete' ){
            /* when the document is loaded and complete this function will run*/
            console.log("page loaded");
            sendServerData();
            getServerData();
            console.log("page complete");
        }
    }
    const sendServerData = function (){

        let httpReq = new XMLHttpRequest();
        httpReq.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                completesentdata(this) /*This is where we get the response when the request was successfully sent and a successfully response is received */
            }
        }

        httpReq.open( "POST" , "/EduClick_war_exploded/Admin/AdminDatacheckServlet" , true);
        httpReq.send(search);
        function completesentdata( httpreq ) {
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

    const getServerData = function (){

        let httpReq = new XMLHttpRequest();
        httpReq.onreadystatechange = function () {

            if (this.readyState === 4 && this.status === 200) {
                completegetdata(this)
            }

        }
        httpReq.open( "GET" , "/EduClick_war_exploded/Admin/AdminDatacheckServlet" , true);
        httpReq.send();
        function completegetdata( httpreq ) {
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

}




