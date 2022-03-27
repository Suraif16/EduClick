const loadTeacherProfileDetailsList = function (){

    console.log("akshjgjhs ashj dgjhas djghasd")
    let httpreq = new XMLHttpRequest();
    /*const postContents = document.getElementById("postContents");
    postContents.innerHTML = "";*/

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/teacherProfileDetails", true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId="+getUserIdClientSide());

    function completeLoad(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            console.log("Profiule teacher details load runningg!!!!!!")

            document.getElementById("country").innerHTML = "Country : "+jsonLoginResponse.teacherDetails.Country
            document.getElementById("city").innerHTML = "City : "+jsonLoginResponse.teacherDetails.City
            document.getElementById("mobile").innerHTML = "Mobile Number : "+jsonLoginResponse.teacherDetails.MobileNum
            document.getElementById("workingPlace").innerHTML = "Working Place : "+jsonLoginResponse.teacherDetails.WorkingPlace.CurrentWorkingPlace
        }else{
            alert("something went wrong!!!");
        }
    }
}