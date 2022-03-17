

function checkdata(){

    getServerData();

}

const getServerData = function (){
    let search = document.getElementById( "comment" ).value;
    console.log(search);

    let httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            completegetdata(this)
        }

    }
    httpReq.open( "GET" , "/EduClick_war_exploded/AdminDatacheckServlet" , true);
    httpReq.send();
    function completegetdata( httpreq ) {
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            if(jsonLoginResponse.getLastName === search){
                document.getElementById("getLastName").innerHTML = jsonLoginResponse.getLastName;
                console.log(jsonLoginResponse.getLastName);
            }

        }else{
            alert("something went wrong!!!");
        }
    }
}


