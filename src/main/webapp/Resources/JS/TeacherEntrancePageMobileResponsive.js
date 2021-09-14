
// const classroomListHideButton = document.getElementById("classroomListHideButton");

let classroomListStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const classroomListObjection = document.getElementById( "classroomsList" )

function showClassroomList(){

    if(classroomListStatus){

        classroomListObjection.style.display = "none";
        classroomListStatus = false;

    }else{

        classroomListObjection.style.display = "flex";
        classroomListStatus = true;

    }

}