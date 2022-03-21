let rightPanelStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const rightPanel = document.getElementById("rightPanel");


function showRightPanel(){

    if(rightPanelStatus){

        rightPanel.style.display = "none";
        rightPanelStatus = false;

    }else{

        rightPanel.style.display = "flex";
        rightPanelStatus = true;

    }

}


function enableDisableStatus( id ){

    let enableStringValue = "enable" + id;

    let disableStringValue = "disable" + id;

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);

    if (disableButton.style.display === "none"){

        /* pressing enabled button to disable student*/
        enableDisableStatusServer( id , "Disable" , true , enableButton , disableButton );



    }else{

        /* pressing disable button to enable student*/
        enableDisableStatusServer( id , "Enable" , false , enableButton , disableButton );


    }

}

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        sendServerData();
        displayCurrentClassroomDetails();
        getClassroomList();
        selectEPostFromServer( false );
        selectStudentEnrollList();

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

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;

            headerUserProfileIdAchorElement.setAttribute("href" , url);
        }else{
            alert("something went wrong!!!");
        }

    }


}


const getClassroomList = function (){
    /* This function gets the Lists of classrooms from the server*/
    const classroomsListLinksSelect = document.getElementById("classroomsListLinks");
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoadClassroomList" , true);
    httpreq.send();

    function complete( httpreq ){

        let jsonResponse = JSON.parse(httpreq.responseText);

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/

            let count = jsonResponse.classroomList.length - 1;

            for(i = 0 ; i <= count ; i++){

                classroomHtmlOutput( jsonResponse.classroomList[i].classroomID ,
                    jsonResponse.classroomList[i].classroomName , jsonResponse.classroomList[i].subject ,
                    jsonResponse.classroomList[i].grade , jsonResponse.classroomList[i].year);

            }

        }else{
            alert("something went wrong!!!");
        }

    }


    function classroomHtmlOutput( classroomId , classroomName , subject , gradeClass , yearOfExamination ){
        classroomsListLinksSelect.innerHTML += '<div class="classroomsListLinksItems">' +
            '' +
            '                        <a href="/EduClick_war_exploded/SaveClassroomId?id=' + classroomId + '"  class="classRooms">' +
            '' +
            '                            <p>Classroom Name : ' + classroomName +'</p>' +
            '                            <p>Subject : ' + subject + '</p>' +
            '                            <p>Grade : ' + gradeClass + '</p>' +
            '                            <p>Year of Examination : ' + yearOfExamination + '</p>' +
            '' +
            '                        </a>' +
            '' +
            '                    </div>'

    }


}

const getClassroomIdClientSide = function (){

    let currentClassUrl = new URL( window.location.href );
    return currentClassUrl.searchParams.get( "clsId" );

}

const selectStudentEnrollList = function (){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            complete( this );

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/getTeacherClassroomStudentEnrollList" , true );
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("classroomId=" + getClassroomIdClientSide() );

    const complete = function ( httpreq ){

        let rightPanelStudentList = document.getElementById( "rightPanelStudentList" );
        rightPanelStudentList.innerHTML = "";

        const jsonObject = JSON.parse( httpreq.responseText );

        // console.log( jsonObject );

        let studentEnrollList = jsonObject.classroomEnrollList;
        let nowDateTime = new Date();
        for (let i = 0; i < studentEnrollList.length; i++) {


            let loginDateTime  = new Date( studentEnrollList[i].loginDate + " " + studentEnrollList[i].loginTime );

            let loginStatus = calculateOnlineStatus( nowDateTime , loginDateTime );

            let singlestudent = '<div class="rightPanelSingleStudent" >' +
                '<div>' +
                '<a href="/EduClick_war_exploded/userProfileRedirect?userId=' + studentEnrollList[i].userId + '" class="profile">' +
                '<div class="profileImage classroomStudentProfilePicture">' +
                '<img class="profileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '</div>' +
                '<div class="classroomStudentProfileName">' + studentEnrollList[i].studentName + '</div>' +
                '</a>' +
                '<div class="lastSeen">';


            if ( loginStatus === "Active"){

                singlestudent += '<img style="background-color:greenyellow;" class="onlineStatus" src="../Resources/Icons/circle_white_24dp.svg">' + 'Active';

            }else{

                singlestudent += '<img style="background-color:red;" class="onlineStatus" src="../Resources/Icons/circle_white_24dp.svg">' + loginStatus + '';

            }

            singlestudent +=    '</div>' +
                '</div>' +
                '<div>';

            if ( studentEnrollList[i].status === "Enable" ){

                singlestudent += '<input style="display:block;" id="enable' + studentEnrollList[i].userId + '" type="button" value="Enabled" onclick="enableDisableStatus(' + studentEnrollList[i].userId + ')">' +
                '<input style="display:none;" id="disable' + studentEnrollList[i].userId + '" type="button" value="Disabled" class="studentDisable" onclick="enableDisableStatus(' + studentEnrollList[i].userId + ')">';


            }else{

                singlestudent += '<input style="display:none;" id="enable' + studentEnrollList[i].userId + '" type="button" value="Enabled" onclick="enableDisableStatus(' + studentEnrollList[i].userId + ')">' +
                '<input style="display:block;" id="disable' + studentEnrollList[i].userId + '" type="button" value="Disabled" class="studentDisable" onclick="enableDisableStatus(' + studentEnrollList[i].userId + ')">';


            }

            singlestudent +=    '</div>' +
                '</div>';

            rightPanelStudentList.innerHTML += singlestudent;

        }


    }

    const calculateOnlineStatus = function ( nowDateTime , loginDateTime ){

        if ( loginDateTime.getFullYear() === nowDateTime.getFullYear() ){

            if ( loginDateTime.getMonth() === nowDateTime.getMonth() ){

                if ( loginDateTime.getDate() === nowDateTime.getDate() ){

                    if ( loginDateTime.getHours() === nowDateTime.getHours() ){

                        let minuteDifference = loginDateTime.getMinutes() - nowDateTime.getMinutes();

                        if ( minuteDifference <= 2 ){

                            return "Active";

                        }else{

                            return "Last Seen " + minuteDifference + " minute/s ago"

                        }

                    }else{

                        return "Last Seen " + ( loginDateTime.getHours() - nowDateTime.getHours() ) + " hour/s ago";

                    }

                }else{

                    let numberOfDays = loginDateTime.getDate() - nowDateTime.getDate();

                    if ( numberOfDays < 7 ){

                        return "Last Seen " + numberOfDays + " day/s ago"

                    }else if ( numberOfDays < 14 ){

                        return "Last Seen a week ago"

                    }else if ( numberOfDays < 21 ){

                        return "Last Seen two weeks ago"

                    }else{

                        return "Last Seen three weeks ago"

                    }

                }

            }else{

                return "Last Seen " + (  loginDateTime.getMonth() - nowDateTime.getMonth() ) + " month/s ago";

            }

        }else{

            return "Last Seen " + ( nowDateTime.getFullYear() - loginDateTime.getFullYear() ) + " year/s ago";

        }

    }

}


const enableDisableStatusServer = function ( id , enableDisableStatus , buttonStatus , enableButton , disableButton ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            if ( buttonStatus ){

                disableButton.style.display = "block";
                enableButton.style.display = "none";

            }else{

                disableButton.style.display = "none";
                enableButton.style.display = "block";

            }

        }

    }

    let classroomId = getClassroomIdClientSide();
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/classroomEnableDisableStudent" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "userId=" + id + "&classroomId=" + classroomId + "&status=" + enableDisableStatus );

}

const displayCurrentClassroomDetails = function (){

    let httpreq = new XMLHttpRequest();
    const rightPanelClassroom = document.getElementById( "rightPanelClassroom" );

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            let jsonResponse = JSON.parse( this.responseText );

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/

                rightPanelClassroom.innerHTML = '<div class="rightPanelClassroomDetails">' +
                    '                Classroom : ' + jsonResponse.classroomDetails.classroomName + ' : ' + jsonResponse.classroomDetails.yearOfExamination + ' : ' + jsonResponse.classroomDetails.grade + ' : ' + jsonResponse.classroomDetails.subject +
                    '            </div>' +
                    '            <div class="rightPanelUnEnrollButton">' +
                    '                <input type="button" value="Delete Classroom" onclick="deleteClassroom(' + getClassroomIdClientSide() + ')">' +
                    '            </div>';

            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/user/classroomDetailsServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "classroomId=" + getClassroomIdClientSide() );

}

const deleteClassroom = function ( id ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            let jsonResponse = JSON.parse( this.responseText );

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/

                window.location.replace("/EduClick_war_exploded/Teacher/Teacher.html");

            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/classroomDeleteServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "classroomId=" + id );

}

setInterval( selectStudentEnrollList , 10000);