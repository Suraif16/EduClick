document.onreadystatechange = function (){

    if( document.readyState === 'complete' ){

        searchPageLoad();

    }

}

const userName = document.getElementById( "userName" )
const headerNotification = document.getElementById( "headerNotification" )
const logout = document.getElementById( "logout" );
const logoAnchorLink = document.getElementById( "linkLogo" );
const searchPost = document.getElementById( "searchPost" );
const searchComment = document.getElementById( "searchComment" );
const search = document.getElementById( "searchBarText" );

let userNameValue;
let userTypeValue;

const searchPageLoad = function (){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( httpreq.readyState === 4 && httpreq.status === 200){

            searchPageData( this );

        }

    }

    httpreq.open("GET", "/EduClick_war_exploded/searchPageOnLoad" ,true);
    httpreq.send();

    const searchPageData = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );
        userName.innerHTML = jsonResponse.UserName;
        logoAnchorLink.setAttribute( "href" , jsonResponse.Url );

        if ( jsonResponse.UserName === 'Guest' ){

            headerNotification.style.display = "none";
            logout.style.display = "none";
            searchPost.style.display = "none";
            searchComment.style.display = "none";

        }

        userNameValue = jsonResponse.UserName;
        userTypeValue = jsonResponse.UserType;

        searchForTeacher();

    }
}



search.addEventListener( "keyup" , function (event){


    if(event.key === "Enter"){

        let httpreq = new XMLHttpRequest();

        httpreq.onreadystatechange = function (){

            if ( httpreq.readyState === 4 && httpreq.status === 200){

                searchPageLoad();

            }
        }
        let url = "/EduClick_war_exploded/Search?searchValue="+search.value;
        httpreq.open( "GET" , url ,true);
        httpreq.send();

    }

});



function searchForTeacher() {

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){

            completeSearch( this );
        }
    }
    httpreq.open( "GET" , "/EduClick_war_exploded/searchTeacher" , true);
    httpreq.send();

    const completeSearch = function( httpreq ){
        const searchContent = document.getElementById( "rightPanelStudentList" );
        searchContent.innerHTML = "";
        let jsonResponse = JSON.parse( httpreq.responseText );

        console.log(jsonResponse.teacherList)
        console.log(userTypeValue);
        console.log(userNameValue)

        search.value = jsonResponse.searchValue;

        if( jsonResponse.teacherList.length > 0){

            for ( i = 0 ; i < jsonResponse.teacherList.length ; i++ ){

                let htmlString = '<div class="rightPanelSingleStudent" >' +
                    '                <div>' +
                    '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ jsonResponse.teacherList[i].userID +'" class="profile">' +
                    '                        <div class="profileImage classroomStudentProfilePicture">' +
                    '                            <img class="profileIcon" src="Resources/Icons/account_circle_white_24dp.svg">' +
                    '                        </div>' +
                    '                        <div class="classroomStudentProfileName">' + jsonResponse.teacherList[i].firstName + " " +jsonResponse.teacherList[i].lastName + '</div>' +
                    '                    </a>' +
                    '                </div>';

                if( userTypeValue !== "Guest" ){

                    if (userTypeValue === "Teacher"){ /* here this checks the user type the current logedin user (my). not the
                    user whose profile is checked...*/
                        console.log(jsonResponse.teacherList[i].friendStatus);
                        console.log(jsonResponse.teacherList[i].friendRequestStatus);
                        console.log(typeof jsonResponse.teacherList[i].friendStatus);
                        console.log(typeof jsonResponse.teacherList[i].friendRequestStatus);



                        if ( jsonResponse.teacherList[i].friendStatus === false && jsonResponse.teacherList[i].friendRequestStatus === false ){
                            console.log( "false false");
                            htmlString += '                <div>' +
                                '                    <input style="display:block;" id="addFriend'+ jsonResponse.teacherList[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.teacherList[i].userID +')">' +
                                '                    <input style="display:none;" id="cancelRequest'+ jsonResponse.teacherList[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.teacherList[i].userID +')">' +
                                '                </div>';

                        }else if ( jsonResponse.teacherList[i].friendStatus === true && jsonResponse.teacherList[i].friendRequestStatus === false ) {

                            htmlString += '                <div>' +
                                'Friend'+
                                '                </div>';

                        }else if ( jsonResponse.teacherList[i].friendStatus === false && jsonResponse.teacherList[i].friendRequestStatus === true ) {

                            htmlString += '                <div>' +
                                '                    <input style="display:none;" id="addFriend'+ jsonResponse.teacherList[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.teacherList[i].userID +')">' +
                                '                    <input style="display:block;" id="cancelRequest'+ jsonResponse.teacherList[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.teacherList[i].userID +')">' +
                                '                </div>';

                        }

                    }else if(userTypeValue === "Student"){

                        htmlString += '                <div>' +
                            '                    <input style="display:block;" id="follow'+ jsonResponse.teacherList[i].userID +'" type="button" value="Follow" onclick="followUnfollowTeachers(' + jsonResponse.teacherList[i].userID +')">' +
                            '                    <input style="display:none;" id="unFollow'+ jsonResponse.teacherList[i].userID +'" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' + jsonResponse.teacherList[i].userID +')">' +
                            '                </div>';

                    }

                }

                htmlString += '</div>';

                searchContent.innerHTML += htmlString;


            }

        }else {
            console.log("no found")
            searchContent.innerHTML = "No result found...";

        }

    }


}

function addFriendCancel( id ){

    let addFriendStringValue = "addFriend" + id;

    let cancelRequestStringValue = "cancelRequest" + id;

    let addFriendButton = document.getElementById( addFriendStringValue );

    let cancelRequestButton = document.getElementById( cancelRequestStringValue );

    if (cancelRequestButton.style.display === "none"){
        console.log( "sent request" + id)
        /*defaultView.getComputedStyle(enableButton)*/
        addFriendRequestServer( id )
        cancelRequestButton.style.display = "block";
        addFriendButton.style.display = "none";


    }else{
        console.log( "cancel request" + id)
        cancelRequestButton.style.display = "none";
        addFriendButton.style.display = "block";

    }

}

function followUnfollowTeachers( id ){

    let followTeacherStringValue = "follow" + id;

    let unFollowStringValue = "unFollow" + id;

    let followButton = document.getElementById( followTeacherStringValue );

    let unFollowButton = document.getElementById( unFollowStringValue );

    if (unFollowButton.style.display === "none"){
        console.log( "follow" + id)
        /*defaultView.getComputedStyle(enableButton)*/
        unFollowButton.style.display = "block";
        followButton.style.display = "none";


    }else{
        console.log( "unfollow" + id)
        unFollowButton.style.display = "none";
        followButton.style.display = "block";

    }

}

const addFriendRequestServer = function ( toUserId ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status == 200){

        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/addFriendRequest" , true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    httpreq.send( "toID=" + toUserId);

}



let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const classroomListObjection = document.getElementById( "classroomsList" );
function showClassroomList(){

    if(classroomListStatus){
        classroomListObjection.style.display = "none";
        classroomListStatus = false;
    }else{
        classroomListObjection.style.display = "flex";
        classroomListStatus = true;
    }
}