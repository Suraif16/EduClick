const loadStudentsProfileDetailsList = function (){
    let httpreq = new XMLHttpRequest();
    /*const postContents = document.getElementById("postContents");
    postContents.innerHTML = "";*/

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/studentProfileDetails", true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId="+getUserIdClientSide());

    function completeLoad(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            console.log("Profiule student details load runningg!!!!!!")
            /*document.getElementById("country").innerHTML = "Country : "+jsonLoginResponse.studentDetails.Country
            document.getElementById("city").innerHTML = "City : "+jsonLoginResponse.studentDetails.City
            document.getElementById("mobile").innerHTML = "Mobile Number : "+jsonLoginResponse.studentDetails.MobileNum
            document.getElementById("school").innerHTML = "School : "+jsonLoginResponse.studentDetails.SchoolAndGrade.School
            document.getElementById("grade").innerHTML = "Grade : "+jsonLoginResponse.studentDetails.SchoolAndGrade.Grade*/

            if(jsonLoginResponse.studentDetails.Country==undefined){
                document.getElementById("country").innerHTML = "Country : Not Entered"
            }else{
                document.getElementById("country").innerHTML = "Country : "+jsonLoginResponse.studentDetails.Country
            }

            if(jsonLoginResponse.studentDetails.City==undefined){
                document.getElementById("city").innerHTML = "City : Not Entered"
            }else{
                document.getElementById("city").innerHTML = "City : "+jsonLoginResponse.studentDetails.City
            }
            if(jsonLoginResponse.studentDetails.MobileNum==undefined){
                document.getElementById("mobile").innerHTML = "Mobile Number : Not Entered"
            }
            else{
                document.getElementById("mobile").innerHTML = "Mobile Number : "+jsonLoginResponse.studentDetails.MobileNum
            }
            if(jsonLoginResponse.studentDetails.SchoolAndGrade.School==undefined){
                document.getElementById("school").innerHTML = "School : Not Entered"
            }else{
                document.getElementById("school").innerHTML = "School : "+jsonLoginResponse.studentDetails.SchoolAndGrade.School
            }
            if(jsonLoginResponse.studentDetails.SchoolAndGrade.Grade==undefined){
                document.getElementById("grade").innerHTML = "Grade : Not Entered"
            }else{
                document.getElementById("grade").innerHTML = "Grade : "+jsonLoginResponse.studentDetails.SchoolAndGrade.Grade
            }
        }else{
            alert("something went wrong!!!");
        }
    }
}