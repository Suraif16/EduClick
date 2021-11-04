let refresh = 0;
/*if(refresh==1){


}*/
const loadFriendsList = function (){
    console.log("Friends called!")
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running friendds")
            if (refresh==0 && refresh1==0){
                loadFriends(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */
            }
        else{
                window.location.reload();
            }
        refresh = 1;

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/student/studentFriendListLoad", true);
    httpreq.send();

    function loadFriends(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            console.log("Onna mama awa hehehehehe friends");

            let count = jsonLoginResponse.List.length - 1;
            for (i = 0 ; i <= count ; i++){


                classroomHtmlOutput(jsonLoginResponse.List[i].UserID,jsonLoginResponse.List[i].firstName,jsonLoginResponse.List[i].lastName);

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
        rightPanel.style.width = "400%";
        rightPanel.style.marginLeft = "270%";


        /*let htmlString = '<div class="rightPanelSingleStudent" style=" flex-basis: 15%;' +
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

        searchContent.innerHTML += htmlString;*/


        let htmlString = '<div id="rightPanel" style="margin: 2% auto 2% auto;' +
            'left: 40%;' +
            '    width: 90%;' +
            '    padding: 1%;' +
            '    display: flex;' +
            '    flex-direction: column;' +
            '    max-height: 76%;">' +
            '            <div id="rightPanelStudentList"  >' +
            '                    <div id="innerDiv" style="display: inline-flex;">' +
            '                        <a id="innerA" href="/EduClick_war_exploded/userProfileRedirect?userId='+ userID +'" class="profile" class="profile" style="flex: 1;display: flex">' +
            '                            <div class="profileImage classroomStudentProfilePicture">' +
            '                                <img class="profileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                            </div>' +
            '                            <div class="classroomStudentProfileName" >' + firstName + " " +lastName + '</div>' +
            '                        </a>' +
            '                    </div>' +
            '                    <div>' +
            '                        <input style="display:block; width: 100%;" id="follow" type="button" value="Follow" onclick="followUnfollowTeachers(' + userID +')">' +
            '                        <input style="display:none;width: 100%;" id="unFollow" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' +userID +')">' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        rightPanel.innerHTML +=htmlString;


        console.log(firstName);
        console.log(lastName);
        console.log(userID);










    }







}