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
                console
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
        let jsonResponse = JSON.parse( httpreq.responseText);

        console.log(jsonResponse.teacherList)
        console.log(userTypeValue);
        console.log(userNameValue)
        
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

                    if (userTypeValue === "Teacher"){

                        htmlString += '                <div>' +
                            '                    <input style="display:block;" id="enable2" type="button" value="Add Friend" onclick="enableDisableStatus(' + jsonResponse.teacherList[i].userID +')">' +
                            '                    <input style="display:none;" id="disable2" type="button" value="Cancel request" class="studentDisable" onclick="enableDisableStatus(' + jsonResponse.teacherList[i].userID +')">' +
                            '                </div>';

                    }else if(userTypeValue === "Student"){

                        htmlString += '                <div>' +
                            '                    <input style="display:block;" id="enable2" type="button" value="Follow" onclick="enableDisableStatus(' + jsonResponse.teacherList[i].userID +')">' +
                            '                    <input style="display:none;" id="disable2" type="button" value="Unfollow" class="studentDisable" onclick="enableDisableStatus(' + jsonResponse.teacherList[i].userID +')">' +
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



