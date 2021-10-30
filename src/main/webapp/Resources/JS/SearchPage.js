document.onreadystatechange = function (){

    if( document.readyState === 'complete' ){

        document.getElementById( "inputTeachers" ).style.backgroundColor = "#403434";
        searchPageLoad();

    }

}
let searchType = "Teacher";
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
        searchFunction( true );

    }
}



search.addEventListener( "keyup" , function (event){


    if(event.key === "Enter"){

        searchFunction( false );

    }

});

const searchFunction = function ( pageLoading ) {

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( httpreq.readyState === 4 && httpreq.status === 200){

            searchResult();

        }
    }

    let searchValue;

    if ( pageLoading ){

        searchValue = "-999999999999999999999999999999";

    }else {

        searchValue = search.value;

    }

    let url = "/EduClick_war_exploded/Search?searchValue=" + searchValue + "&searchType=" + searchType;
    httpreq.open( "GET" , url ,true);
    httpreq.send();





}

function searchResult() {

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

        search.value = jsonResponse.searchValue;

        if( jsonResponse.searchResult.length > 0){

            if ( searchType === "Teacher"){

                displayTeacher( jsonResponse );

            }else if ( searchType === "Student"){

                displayStudent( jsonResponse );

            }else if ( searchType === "Post"){

                displayPost( jsonResponse );

            }else if ( searchType === "Answer"){

                displayAnswer( jsonResponse );

            }

        }else {

            searchContent.innerHTML = "No result found...";

        }

    }


}

const displayTeacher = function ( jsonResponse ){
    const searchContent = document.getElementById( "rightPanelStudentList" );
    for ( i = 0 ; i < jsonResponse.searchResult.length ; i++ ){

        let htmlString = '<div class="rightPanelSingleStudent" >' +
            '                <div>' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ jsonResponse.searchResult[i].userID +'" class="profile">' +
            '                        <div class="profileImage classroomStudentProfilePicture">' +
            '                            <img class="profileIcon" src="Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="classroomStudentProfileName">' + jsonResponse.searchResult[i].firstName + " " +jsonResponse.searchResult[i].lastName + '</div>' +
            '                    </a>' +
            '                </div>';

        if( userTypeValue !== "Guest" ){

            if (userTypeValue === "Teacher"){ /* here this checks the user type the current logedin user (my). not the
                    user whose profile is checked...*/

                if ( jsonResponse.searchResult[i].friendStatus === false && jsonResponse.searchResult[i].friendRequestStatus === false ){

                    htmlString += '                <div>' +
                        '                    <input style="display:block;" id="addFriend'+ jsonResponse.searchResult[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:none;" id="cancelRequest'+ jsonResponse.searchResult[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }else if ( jsonResponse.searchResult[i].friendStatus === true && jsonResponse.searchResult[i].friendRequestStatus === false ) {

                    htmlString += '                <div>' +
                        'Friend'+
                        '                </div>';

                }else if ( jsonResponse.searchResult[i].friendStatus === false && jsonResponse.searchResult[i].friendRequestStatus === true ) {

                    htmlString += '                <div>' +
                        '                    <input style="display:none;" id="addFriend'+ jsonResponse.searchResult[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:block;" id="cancelRequest'+ jsonResponse.searchResult[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }

            }else if(userTypeValue === "Student"){

                if ( jsonResponse.searchResult[i].followStatus === true ){

                    htmlString += '                <div>' +
                        '                    <input style="display:none;" id="follow'+ jsonResponse.searchResult[i].userID +'" type="button" value="Follow" onclick="followUnfollowTeachers(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:block;" id="unFollow'+ jsonResponse.searchResult[i].userID +'" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }else {

                    htmlString += '                <div>' +
                        '                    <input style="display:block;" id="follow'+ jsonResponse.searchResult[i].userID +'" type="button" value="Follow" onclick="followUnfollowTeachers(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:none;" id="unFollow'+ jsonResponse.searchResult[i].userID +'" type="button" value="Unfollow" class="studentDisable" onclick="followUnfollowTeachers(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }

            }

        }

        htmlString += '</div>';

        searchContent.innerHTML += htmlString;


    }


}

const displayStudent = function ( jsonResponse ){
    const searchContent = document.getElementById( "rightPanelStudentList" );
    for ( i = 0 ; i < jsonResponse.searchResult.length ; i++ ){

        let htmlString = '<div class="rightPanelSingleStudent" >' +
            '                <div>' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ jsonResponse.searchResult[i].userID +'" class="profile">' +
            '                        <div class="profileImage classroomStudentProfilePicture">' +
            '                            <img class="profileIcon" src="Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="classroomStudentProfileName">' + jsonResponse.searchResult[i].firstName + " " +jsonResponse.searchResult[i].lastName + '</div>' +
            '                    </a>' +
            '                </div>';

        if( userTypeValue !== "Guest" ){

            if (userTypeValue === "Student"){ /* here this checks the user type the current logedin user (my). not the
                    user whose profile is checked...*/

                if ( jsonResponse.searchResult[i].friendStatus === false && jsonResponse.searchResult[i].friendRequestStatus === false ){

                    htmlString += '                <div>' +
                        '                    <input style="display:block;" id="addFriend'+ jsonResponse.searchResult[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:none;" id="cancelRequest'+ jsonResponse.searchResult[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }else if ( jsonResponse.searchResult[i].friendStatus === true && jsonResponse.searchResult[i].friendRequestStatus === false ) {

                    htmlString += '                <div>' +
                        'Friend'+
                        '                </div>';

                }else if ( jsonResponse.searchResult[i].friendStatus === false && jsonResponse.searchResult[i].friendRequestStatus === true ) {

                    htmlString += '                <div>' +
                        '                    <input style="display:none;" id="addFriend'+ jsonResponse.searchResult[i].userID +'" type="button" value="Add Friend" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                    <input style="display:block;" id="cancelRequest'+ jsonResponse.searchResult[i].userID +'" type="button" value="Cancel request" class="studentDisable" onclick="addFriendCancel(' + jsonResponse.searchResult[i].userID +')">' +
                        '                </div>';

                }

            }else if(userTypeValue === "Teacher"){

                if ( jsonResponse.searchResult[i].followStatus === true ){

                    htmlString += '                <div>' +
                        'Following you'+
                        '                </div>';

                }


            }

        }

        htmlString += '</div>';

        searchContent.innerHTML += htmlString;


    }


}

const displayPost = function ( jsonResponse ){

    const searchContent = document.getElementById( "rightPanelStudentList" );
    searchContent.innerHTML = "No result found...";

}

const displayAnswer = function ( jsonResponse ){

    const searchContent = document.getElementById( "rightPanelStudentList" );
    searchContent.innerHTML = "No result found...";

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

const cancelFriendRequestServer = function ( toUserId , addFriendButton , cancelRequestButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status == 200){

            cancelRequestButton.style.display = "none";
            addFriendButton.style.display = "block";

        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/addFriendRequest" , true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "toID=" + toUserId);

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

const setSearchType = function( type ){

     if ( type === "Teachers" ){

         searchType = "Teacher";
         document.getElementById( "inputTeachers" ).style.backgroundColor = "#403434";
         document.getElementById( "inputStudents" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputPosts" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputAnswers" ).style.backgroundColor = "#4775c4";

     }else if ( type === "Students" ){

         searchType = "Student";
         document.getElementById( "inputTeachers" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputStudents" ).style.backgroundColor = "#403434";
         document.getElementById( "inputPosts" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputAnswers" ).style.backgroundColor = "#4775c4";

     }else if ( type === "Posts" ){

         searchType = "Post";
         document.getElementById( "inputTeachers" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputStudents" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputPosts" ).style.backgroundColor = "#403434";
         document.getElementById( "inputAnswers" ).style.backgroundColor = "#4775c4";

     }else if ( type === "Answers" ){

         searchType = "Answer";
         document.getElementById( "inputTeachers" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputStudents" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputPosts" ).style.backgroundColor = "#4775c4";
         document.getElementById( "inputAnswers" ).style.backgroundColor = "#403434";

     }

     searchFunction( false );

}