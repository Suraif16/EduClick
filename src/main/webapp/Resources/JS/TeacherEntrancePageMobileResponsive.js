let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
let notificationStatus = false; /*if it is false the notification is hidden*/
let addClassroomFormStatus = false /*if it is false the addClassroomForm is hidden*/

const classroomListObjection = document.getElementById( "classroomsList" );
const search = document.getElementById( "searchBarText" );
const notifications = document.getElementById("notifications");
const addClassroomForm = document.getElementById("addClassroomForm");

search.addEventListener( "keyup" , function ( event ){

    if(event.key === "Enter"){

        console.log(search.value);

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


function showNotification(){

    console.log("hi")

    if(notificationStatus){

        notifications.style.display = "none";
        notificationStatus = false;

    }else{

        notifications.style.display = "flex";
        notificationStatus = true;

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

function createClassroom(){

    const classroomsListLinks = document.getElementById("classroomsListLinks");
    let classroomName = document.getElementById("classroomName");
    let yearOfExamination = document.getElementById("classroomYearOfExamination");
    let gradeClass = document.getElementById("classroomClassGrade");
    let subject = document.getElementById("classroomSubject");
    

    const createClassroomHtml = function (){


        classroomsListLinks.innerHTML += '<div className="classroomsListLinksItems"' +
            ' style="flex: 1;\n' +
            '    background-color: #4775c4;\n' +
            '    text-align: center;\n' +
            '    margin: 1.5% 0;\n' +
            '    padding: 1%;"> ' +
            '<a href="" className="classRooms"> ' +
            classroomName.value + ' : ' + subject.value + ' : Grade ' + gradeClass.value + ' : ' + yearOfExamination.value +
            '</a>' +
            '</div>';


        classroomName.value = "";
        yearOfExamination.value = "";
        gradeClass.value = "";
        subject.value = "";
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

            let jsonLoginResponse = JSON.parse(httpreq.responseText);

            if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonLoginResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/
                console.log("1");
                createClassroomHtml();


            }else{
                alert("something went wrong!!!");
            }

        }


    }



    sendServerData();


}
