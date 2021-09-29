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

    console.log( classroomName.value , yearOfExamination.value , gradeClass.value , subject.value );

    classroomsListLinks.innerHTML += '<div className="classroomsListLinksItems"' +
        ' style="flex: 1;\n' +
        '    background-color: #4775c4;\n' +
        '    text-align: center;\n' +
        '    margin: 1.5% 0;\n' +
        '    padding: 1%;"> ' +
        '<a href="" className="classRooms"> ' +
        classroomName.value + ' : ' + subject.value + ' : Grade ' + gradeClass.value + ' : ' + subject.value +
        '</a>' +
        '</div>';


    classroomName.value = "";
    yearOfExamination.value = "";
    gradeClass.value = "";
    subject.value = "";
    showAddClassroomFrom();
}
