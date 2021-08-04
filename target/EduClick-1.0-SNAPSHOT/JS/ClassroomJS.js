const menuPanelBtn = document.getElementById("menuPanel");
const classroomListMenu = document.getElementById("classroomsList");

let statusOfClassroomListMenu = true;

menuPanelBtn.onclick = function (){
    if(statusOfClassroomListMenu){
        classroomListMenu.style.display = "none";
        statusOfClassroomListMenu = false;
    }else{
        classroomListMenu.style.display = "";
        statusOfClassroomListMenu = true;
    }
}

