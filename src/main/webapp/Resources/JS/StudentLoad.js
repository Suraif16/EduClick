

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        sendServerData();
        getClassroomList();
      //  sendServerDataStudent();
        LoadSelectedNewsFeeds();
        StudentLoadAdminPost();

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

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentLoad" , true);
    httpreq.send();



    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            console.log(jsonLoginResponse);
            /* This is where I need work everytime as per the authentication filter*/
            console.log(jsonLoginResponse.firstName);
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;
            console.log(url);
            headerUserProfileIdAchorElement.setAttribute("href" , url);
            if ( jsonLoginResponse.profilePicture === undefined ){

                document.getElementById( "headerProfilePicture" ).innerHTML = '<img class="profileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'

            }else{

                document.getElementById( "headerProfilePicture" ).innerHTML = '<img class="profileIcon" src="../Resources/Images/UserProfileImages/profilePicture' + jsonLoginResponse.profilePicture + '.jpeg">'

            }
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

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentNewsFeedLoaded" , true);
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