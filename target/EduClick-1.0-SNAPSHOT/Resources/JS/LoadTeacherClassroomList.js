document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        sendD();
        console.log("I'm loaded js");

    }

}

const sendD = function () {
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */
            sendNameData();
            LoadTeacherProName();
        }

    }
    httpreq.open("POST", "/EduClick_war_exploded/teacherClassroomList", true);
    httpreq.send();

    function completeLoad(httpreq) {

        let jsonResponse = JSON.parse(httpreq.responseText);
        let count = jsonResponse.classroomList.length - 1;

        for (i = 0; i <= count; i++) {

            classroomHtmlOutput(jsonResponse.classroomList[i].classroomID,
                jsonResponse.classroomList[i].classroomName, jsonResponse.classroomList[i].subject,
                jsonResponse.classroomList[i].grade, jsonResponse.classroomList[i].year);

        }

    }

    function classroomHtmlOutput( classroomId , classroomName , subject , gradeClass , yearOfExamination ){
        console.log(classroomId)
        console.log(classroomName)
        console.log(subject)
        console.log(gradeClass)
        console.log(yearOfExamination)
        console.log("\n")
        let classroomsListLinksSelect = document.getElementById("rightPanelStudentList");
        /*classroomsListLinksSelect.innerHTML += '<div class="classroomStudentProfileName">'+

            '<a href="Classroom.html" class="classRooms">'+

            classroomName + ' : ' + subject + ' : Grade ' + gradeClass + ' : ' + yearOfExamination +
            '</a>'+

            '</div>';*/



        classroomsListLinksSelect.innerHTML +=
            '            <div class="rightPanelSingleStudent" >' +
            '                <div>' +
            '                    <a href="#" class="profile">' +
            '                        <div class="classroomStudentProfileName">' +
            '                                        <p>Classroom Name : ' + classroomName +'</p>' +
            '                                        <p>Subject : ' + subject + '</p>' +
            '                                        <p>Grade : ' + gradeClass + '</p>' +
            '                                        <p>Year of Examination : ' + yearOfExamination + '</p>' +
            '             </div>' +
            '                    </a>' +
            '' +
            '                </div>' +
            '' +
            '                <div>' +
            '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
            '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
            '                </div>';

    }
}

const sendNameData = function (){
    console.log("Firstname loaded!!")
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



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            console.log(jsonLoginResponse);
            /* This is where I need work everytime as per the authentication filter*/
            console.log(jsonLoginResponse.firstName);
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
        }else{
            alert("something went wrong!!!");
        }

    }


}
const LoadTeacherProName = function (){
    console.log("Firstname loaded!!")
    /* This function gets the username from the server*/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/teacherProfileNameLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log("teacher pro name find loaded");




        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const name = document.getElementById("teacherUserName");
            name.innerHTML = jsonLoginResponse.FullName;
            console.log(name);

        }else{
            alert("something went wrong!!!");
        }

    }


}
