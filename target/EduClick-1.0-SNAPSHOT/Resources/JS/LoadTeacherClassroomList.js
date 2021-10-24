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
            '                        <div class="classroomStudentProfileName">' + classroomName + ' : ' + subject + ' : Grade ' + gradeClass + ' : ' + yearOfExamination + '</div>' +
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
