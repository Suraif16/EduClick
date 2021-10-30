const loadFollowersList = function (){
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/student/studentFollowerListLoad", true);
    httpreq.send();

    function completeLoad(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            console.log("Onna mama awa hehehehehe");

            let count = jsonLoginResponse.List.length - 1;
            for (i = 0 ; i <= count ; i++){


                classroomHtmlOutput(jsonLoginResponse.List[i].UserID,jsonLoginResponse.List[i].lastName,jsonLoginResponse.List[i].firstName);

            }
        }else{
            alert("something went wrong!!!");
        }
    }

    function classroomHtmlOutput( userID , firstName , lastName){
        const postContents = document.getElementById("postContents");
        postContents.innerHTML = "";
        const searchContent = document.getElementById( "rightPanelStudentList" );
        const rightPanel = document.getElementById("rightPanel");
        rightPanel.style.display = "flex";


        let htmlString = '<div class="rightPanelSingleStudent" style=" flex-basis: 15%;' +
            '    margin: 1% auto;' +
            '    display: flex;' +
            '    flex-direction: column;' +
            '    width: 100%;' +
            '    background-color: #4775c4;">' +
            '                <div>' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ userID +'" class="profile" style="display: flex;">' +
            '                        <div class="classroomStudentProfileName">' + firstName + " " +lastName + '</div>' +
            '                    </a>' +
            '                </div>';

        htmlString += '       <div>' +
            '                    <input style="display:block;" id="follow'+ userID +'" type="button" value="Follow" onclick="followUnfollowTeachers(' + userID +')">' +
            '                    <input style="display:none;" id="unFollow'+ userID +'" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' +userID +')">' +
            '                </div>';


        htmlString += '</div>';

        searchContent.innerHTML += htmlString;




        console.log(firstName);
        console.log(lastName);
        console.log(userID);










    }


}
