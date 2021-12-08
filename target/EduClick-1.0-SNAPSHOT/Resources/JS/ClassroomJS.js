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

        /*defaultView.getComputedStyle(enableButton)*/
        disableButton.style.display = "block";
        enableButton.style.display = "none";


    }else{

        disableButton.style.display = "none";
        enableButton.style.display = "block";

    }

}

function showAnswers( id ){

<<<<<<< HEAD
    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );
=======
    /*  let com = document.getElementById("ans");
      if(com.style.display === "none"){

          com.style.display = "flex";
      }else{
          com.style.display = "none"
      }*/

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );
    let com = document.getElementById("ans" + id) ;
>>>>>>> main

    if (answerContainer.style.display === "none"){

        answerContainer.style.display = "flex";

<<<<<<< HEAD
    }else{

        answerContainer.style.display = "none";

    }


=======
        com.style.display = "flex";

    }


    else{

        answerContainer.style.display = "none";
        com.style.display = "none"
    }




>>>>>>> main
}


function showMcqResult( id ){

    let mcqResultsInPostId = "mcqResultsInPost" + id;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );

    if (mcqResultsInPost.style.display === "none"){

        mcqResultsInPost.style.display = "flex";

    }else{

        mcqResultsInPost.style.display = "none";

    }


}

document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
<<<<<<< HEAD
        sendNameData();
        getClassroomList();
        console.log("I'm loaded js");
=======
        sendServerData();
        getClassroomList();
>>>>>>> main

    }

}

<<<<<<< HEAD
const sendNameData = function (){
    console.log("Firstname loaded!!")
=======
const sendServerData = function (){
>>>>>>> main
    /* This function gets the username from the server*/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

<<<<<<< HEAD
    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentLoad" , true);
=======
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoad" , true);
>>>>>>> main
    httpreq.send();

    function completeLogin( httpreq ){

<<<<<<< HEAD
        let jsonLoginResponse = JSON.parse(httpreq.responseText);


=======
        const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");

        let jsonLoginResponse = JSON.parse(httpreq.responseText);
>>>>>>> main

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
<<<<<<< HEAD

            console.log(jsonLoginResponse);
            /* This is where I need work everytime as per the authentication filter*/
            console.log(jsonLoginResponse.firstName);
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
=======
            /* This is where I need work everytime as per the authentication filter*/
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;

            headerUserProfileIdAchorElement.setAttribute("href" , url);
>>>>>>> main
        }else{
            alert("something went wrong!!!");
        }

    }


}

<<<<<<< HEAD
=======

>>>>>>> main
const getClassroomList = function (){
    /* This function gets the Lists of classrooms from the server*/
    const classroomsListLinksSelect = document.getElementById("classroomsListLinks");
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

<<<<<<< HEAD
    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentNewsFeedLoaded" , true);
=======
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoadClassroomList" , true);
>>>>>>> main
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
<<<<<<< HEAD

        classroomsListLinksSelect.innerHTML += '<div className="classroomsListLinksItems"' +
            ' style="flex: 1;\n' +
            '    background-color: #4775c4;\n' +
            '    text-align: center;\n' +
            '    margin: 1.5% 0;\n' +
            '    padding: 1%;"> ' +
            '<a href="/EduClick_war_exploded/Student/classroom.html?id=' + classroomId +'"' +' className="classRooms"> ' +
=======
        classroomsListLinksSelect.innerHTML += '<div class="classroomsListLinksItems">' +
            '' +
            '                        <a href="Classroom.html"  class="classRooms">' +
>>>>>>> main
            '' +
            '                            <p>Classroom Name : ' + classroomName +'</p>' +
            '                            <p>Subject : ' + subject + '</p>' +
            '                            <p>Grade : ' + gradeClass + '</p>' +
            '                            <p>Year of Examination : ' + yearOfExamination + '</p>' +
            '' +
<<<<<<< HEAD
            '</a>' +
            '</div>';

=======
            '                        </a>' +
            '' +
            '                    </div>'
>>>>>>> main

    }


}
<<<<<<< HEAD
const displayComment = function (){

 

}
=======

>>>>>>> main
