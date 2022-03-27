
const loadFriendsList = function (){
    console.log("Friends called!")
    /*let btn = document.getElementById("friends");
    btn.style.backgroundColor = "#157DEC";*/
    let httpreq = new XMLHttpRequest();

    const postContents = document.getElementById("postContents");
    postContents.innerHTML = "";
    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running friendds")

                loadFriends(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/studentFriendListLoad", true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + getUserIdClientSide());

    function loadFriends(httpreq){
        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log(jsonLoginResponse)
        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            console.log(jsonLoginResponse+"Onna mama awa hehehehehe friends");

            let count = jsonLoginResponse.List.length - 1;
            for (i = 0 ; i <= count ; i++){


                classroomHtmlOutput(jsonLoginResponse.List[i].UserID,jsonLoginResponse.List[i].firstName,jsonLoginResponse.List[i].lastName);

            }
        }else{
            alert("something went wrong!!!");
        }
    }

    function classroomHtmlOutput( userID , firstName , lastName){
        /*let btn = document.getElementById("friends");
        btn.style.backgroundColor = "#157DEC";*/
        const postContents = document.getElementById("postContents");
        postContents.innerHTML = "";
        postContents.style.boxShadow = "";
        const searchContent = document.getElementById( "rightPanelStudentList" );
        const rightPanel = document.getElementById("rightPanel");

        rightPanel.innerHTML="";
        rightPanel.style.display = "flex";
        rightPanel.style.width = "435%";
        rightPanel.style.marginLeft = "230%";


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
            '                        <input style="display:none; width: 100%;" id="addFriend'+userID+'" type="button" value="Add Friend" onclick="addFriendCancel(' + userID +')">' +
            '                        <input style="display:block;width: 100%;" id="cancelRequest'+userID+'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' +userID +')">' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        rightPanel.innerHTML +=htmlString;


        console.log(firstName);
        console.log(lastName);
        console.log(userID);



    }



}
function addFriendCancel( id ){

    let addFriendStringValue = "addFriend" + id;

    let cancelRequestStringValue = "cancelRequest" + id;

    let addFriendButton = document.getElementById( addFriendStringValue );

    let cancelRequestButton = document.getElementById( cancelRequestStringValue );

    if (cancelRequestButton.style.display === "none"){

        addFriendRequestServer( id , addFriendButton , cancelRequestButton);

    }else{

        cancelFriendRequestServer( id , addFriendButton , cancelRequestButton);


    }

}
const addFriendRequestServer = function ( toUserId , addFriendButton , cancelRequestButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status == 200){

            cancelRequestButton.style.display = "block";
            addFriendButton.style.display = "none";

        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/addFriendRequest" , true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "toID=" + toUserId);

}

const cancelFriendRequestServer = function ( toUserId , addFriendButton , cancelRequestButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status == 200){

            cancelRequestButton.style.display = "none";
            addFriendButton.style.display = "block";

        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/cancelFriendRequest" , true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "toID=" + toUserId);

}

