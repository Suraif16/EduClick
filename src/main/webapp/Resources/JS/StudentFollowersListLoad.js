

const loadFollowersList = function (){
    /*let btn = document.getElementById("followers");
    btn.style.backgroundColor = "#157DEC";*/
    let httpreq = new XMLHttpRequest();
    const postContents = document.getElementById("postContents");
    postContents.innerHTML = "";

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
        rightPanel.innerHTML="";
        rightPanel.style.display = "flex";
        rightPanel.style.width = "380%";
        rightPanel.style.marginLeft = "209%";


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
            '                        <input style="display:none; width: 100%;" id="follow'+userID+'" type="button" value="Follow" onclick="followUnfollowTeachers(' + userID +')">' +
            '                        <input style="display:block;width: 100%;" id="unFollow'+userID+'" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' +userID +')">' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        rightPanel.innerHTML +=htmlString;


        console.log(firstName);
        console.log(lastName);
        console.log(userID);










    }


}

function followUnfollowTeachers( id ){

    let followTeacherStringValue = "follow" + id;

    let unFollowStringValue = "unFollow" + id;

    let followButton = document.getElementById( followTeacherStringValue );

    let unFollowButton = document.getElementById( unFollowStringValue );

    if (unFollowButton.style.display === "none"){

        /*defaultView.getComputedStyle(enableButton)*/
        followTeacher( id , followButton , unFollowButton );

    }else{

        unfollowTeacher( id , followButton , unFollowButton );


    }

}

const followTeacher = function( T_UserId , followButton , unFollowButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            unFollowButton.style.display = "block";
            followButton.style.display = "none";

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/followTeacher" , true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "teacherId=" + T_UserId );

}

const unfollowTeacher = function( T_UserId , followButton , unFollowButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            unFollowButton.style.display = "none";
            followButton.style.display = "block";

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/unFollowTeacher" , true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "teacherId=" + T_UserId );

}

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        sendServerData();

    }

}

const sendServerData = function (){
    /* This function gets the username from the server*/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentProfileNameLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            console.log(jsonLoginResponse);
            /* This is where I need work everytime as per the authentication filter*/
            console.log(jsonLoginResponse.FullName);
            const name = document.getElementById("profileUserName");
            name.innerHTML = jsonLoginResponse.FullName;
        }else{
            alert("something went wrong!!!");
        }

    }


}
