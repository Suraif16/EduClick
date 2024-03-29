let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let addClassroomFormStatus = false; /*if it is false the addClassroomForm is hidden*/
let addClassroomFormErrorStatus = false; /* if it is false then classroomFormRowErrorMessage is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const addClassroomForm = document.getElementById("addClassroomForm");
const classroomFormRowErrorMessage = document.getElementById( "classroomFormRowErrorMessage" );


function showClassroomList(){

    if(classroomListStatus){

        classroomListObjection.style.display = "none";
        classroomListStatus = false;

    }else{

        classroomListObjection.style.display = "flex";
        classroomListStatus = true;

    }

}

function showAddClassroomFrom(){

    if(addClassroomFormStatus){

        addClassroomForm.style.display = "none";
        addClassroomFormStatus = false;

    }else {

        addClassroomForm.style.display = "flex";
        addClassroomFormStatus = true;

    }

}

function showClassroomFormRowErrorMessage( message ){

    if(addClassroomFormErrorStatus){

        classroomFormRowErrorMessage.style.display = "none";
        addClassroomFormErrorStatus = false;

    }else {

        classroomFormRowErrorMessage.style.display = "flex";
        classroomFormRowErrorMessage.innerHTML = message;
        addClassroomFormErrorStatus = true;

    }

}

function createClassroom(){

    const classroomsListLinks = document.getElementById("classroomsListLinks");
    let classroomName = document.getElementById("classroomName");
    let yearOfExamination = document.getElementById("classroomYearOfExamination");
    let gradeClass = document.getElementById("classroomClassGrade");
    let subject = document.getElementById("classroomSubject");

    const checkYearConstraint = function (){

        if ( parseInt( yearOfExamination.value ) <= ( ( new Date().getFullYear() ) + 10 ) ) {

            return false;

        }else {

            return true;

        }

    }


    const createClassroomHtml = function ( classroomId ){

        classroomSuccessfullyCreated( classroomName.value , subject.value , gradeClass.value , yearOfExamination.value );
        classroomsListLinks.innerHTML += '<div class="classroomsListLinksItems">' +
            '' +
            '                        <a href="/EduClick_war_exploded/SaveClassroomId?id=' + classroomId + '"  class="classRooms">' +
            '' +
            '                            <p>Classroom Name : ' + classroomName.value + '</p>' +
            '                            <p>Subject : ' + subject.value + '</p>' +
            '                            <p>Grade : ' + gradeClass.value + '</p>' +
            '                            <p>Year of Examination : ' + yearOfExamination.value + '</p>' +
            '' +
            '                        </a>' +
            '' +
            '                    </div>';


        classroomName.value = "";
        yearOfExamination.value = "";
        gradeClass.value = "";
        subject.value = "";
        console.log(classroomId);
        showAddClassroomFrom();

    }

    const sendServerData = function (){

        let httpreq = new XMLHttpRequest();
        httpreq.onreadystatechange = function (){

            if (this.readyState === 4 && this.status === 200){
                completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
            }

        }

        httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherCreateClassroom" , true);
        httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
        httpreq.send("classroomName=" + classroomName.value + "&subject=" + subject.value + "&grade=" + gradeClass.value + "&yearOfExamination=" + yearOfExamination.value );

        function completeLogin( httpreq ){

            let jsonResponse = JSON.parse(httpreq.responseText);

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/

                if( jsonResponse.classroomId == "Classroom not created" ){

                    showClassroomFormRowErrorMessage( "Classroom creation was unsuccessful. Please try again later..." );

                }else {

                    createClassroomHtml( jsonResponse.classroomId );
                    showClassroomFormRowErrorMessage();

                }


            }else{
                alert("something went wrong!!!");
            }

        }


    }


    if( classroomName.value === "" || yearOfExamination.value === "" || gradeClass.value === "" || subject.value === "" || checkYearConstraint() ){

        if ( checkYearConstraint() ){

            showClassroomFormRowErrorMessage( "Year should be less than or equal to : " + ( new Date().getFullYear() + 10 ) );

        }else{

            showClassroomFormRowErrorMessage( "Please fill all input fields..." );

        }


    }else {

        sendServerData();

    }


}

function searchTeacher(){

    const searchName = document.getElementById("searchBarText").value;

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeSearch( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }
    /* ************************ */
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherCreateClassroom" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchBarText=" + searchName);

    function completeSearch(httpreq){

        console.log(httpreq.responseText);
        let jsonSearchResponse = JSON.parse(httpreq.responseText);

    }


}