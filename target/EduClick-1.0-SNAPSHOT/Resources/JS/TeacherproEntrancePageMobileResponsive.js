let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let notificationStatus = false; /*if it is false the notification is hidden*/
let addClassroomFormStatus = false; /*if it is false the addClassroomForm is hidden*/
let addClassroomFormErrorStatus = false; /* if it is false then classroomFormRowErrorMessage is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const search = document.getElementById( "searchBarText" );
const addClassroomForm = document.getElementById("addClassroomForm");
const classroomFormRowErrorMessage = document.getElementById( "classroomFormRowErrorMessage" );

search.addEventListener( "keyup" , function ( event ){

    if(event.key === "Enter"){

        // console.log(search.value);
        let httpreq = new XMLHttpRequest();

        httpreq.onreadystatechange = function (){

        }
    }

});

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


    const createClassroomHtml = function ( classroomId ){


        classroomsListLinks.innerHTML += '<div class="classroomsListLinksItems">' +
            '' +
            '                        <a href="/EduClick_war_exploded/Teacher/Classroom.html?id="' + classroomId +  ' class="classRooms">' +
            '' +
            '                            <p>Classroom Name : ' + classroomName.value + '</p>' +
            '                            <p>Subject : ' + subject.value + '</p>' +
            '                            <p>Grade : ' + gradeClass.value + '</p>' +
            '                            <p>Year of Examination : ' + yearOfExamination.value + '</p>' +
            '' +
            '                        </a>' +
            '' +
            '                    </div>'


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


    if( classroomName.value === "" || yearOfExamination.value === "" || gradeClass.value === "" || subject.value === "" ){

        showClassroomFormRowErrorMessage( "Please fill all input fields..." );


    }else {

        sendServerData();

    }


}
