
const loadTeacherFollowersList = function (){
    console.log("1234");

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/teacher/teacherFollowerListLoad", true);
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

                console.log("hiii");
                classroomHtmlOutput(jsonLoginResponse.List[i].UserID,jsonLoginResponse.List[i].firstName,jsonLoginResponse.List[i].lastName);

            }
        }else{
            alert("something went wrong!!!");
        }
    }

    function classroomHtmlOutput( userID , firstName , lastName){
        console.log("very good");
        console.log(firstName);
        console.log(lastName);
        console.log(userID);

        const postContents = document.getElementById("postContents");
        postContents.innerHTML = "";
        const searchContent = document.getElementById( "rightPanelStudentList" );
        const rightPanel = document.getElementById("rightPanel");
        rightPanel.style.display = "flex";
        rightPanel.style.width = "40%";
        rightPanel.style.marginLeft = "30%";

        console.log("abc-1");


        let htmlString = '<div id="rightPanel" style="margin: 2% auto 2% auto;' +
            'left: 400%;' +
            '    width: 90%;' +
            '    padding: 1%;' +
            '    display: flex; background-color: #4775c4;' +
            '    flex-direction: column;' +
            '    max-height: 76%;">' +
            '            <div id="rightPanelStudentList" >' +
            '                    <div id="innerDiv" style="display: inline-flex; background-color:  #4775c4;">' +
            '                        <a id="innerA" href="/EduClick_war_exploded/userProfileRedirect?userId='+ userID +'" class="profile" class="profile" style="flex: 1;display: flex;">' +
            '                            <div class="profileImage classroomStudentProfilePicture" >' +
            '                                <img class="profileIcon" src="../Resources/Icons/account_circle_white_24dp.svg" style="margin-left: 20px;" >' +
            '                            </div>' +
            '                            <div class="classroomStudentProfileName" style="color: white; width: 490px;">' + firstName + " " +lastName + '</div>' +
            '                        </a>' +
            '                    </div>' +
            '                    <div>' +
            '                        <input style="display:none; width: 100%; background-color: black;" id="addFriend'+userID+'" type="button" value="Add Friend" onclick="addFriendCancel(' + userID +')">' +
            '                        <input style="display:block;width: 100%; background-color: black;" id="cancelRequest'+userID+'" type="button" value="Follow" class="studentDisable" onclick="addFriendCancel(' +userID +')">' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        rightPanel.innerHTML +=htmlString;

        console.log(firstName+"^^^^^^^^^^^");
        console.log(lastName+"^^^^^^^^");
        console.log(userID+"^^^^^^^^^^^^^^^");



    }


}

