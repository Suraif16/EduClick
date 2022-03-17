

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
            if(jsonLoginResponse.getFirstName === search || jsonLoginResponse.getLastName === search ){

                document.getElementById("getUserId").innerHTML = jsonLoginResponse.getUserId;
                console.log(jsonLoginResponse.getUserId);
                document.getElementById("getFirstName").innerHTML = jsonLoginResponse.getFirstName;
                console.log(jsonLoginResponse.getFirstName);
                document.getElementById("getLastName").innerHTML = jsonLoginResponse.getLastName;
                console.log(jsonLoginResponse.getLastName);
                document.getElementById("getGender").innerHTML = jsonLoginResponse.getGender;
                console.log(jsonLoginResponse.getGender);
                document.getElementById("getDateOfBirth").innerHTML = jsonLoginResponse.getDateOfBirth;
                console.log(jsonLoginResponse.getDateOfBirth);
                document.getElementById("getCountry").innerHTML = jsonLoginResponse.getCountry;
                console.log(jsonLoginResponse.getCountry);
                document.getElementById("getRegistrationDate").innerHTML = jsonLoginResponse.getRegistrationDate;
                console.log(jsonLoginResponse.getRegistrationDate);
                document.getElementById("getRegistrationTime").innerHTML = jsonLoginResponse.getRegistrationTime;
                console.log(jsonLoginResponse.getRegistrationTime);

            }

        }else{
            alert("something went wrong!!!");
        }
    }
}


