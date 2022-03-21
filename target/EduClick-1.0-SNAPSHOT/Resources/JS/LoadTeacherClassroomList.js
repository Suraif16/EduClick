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
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + getUserIdClientSide());

    function completeLoad(httpreq) {

        let jsonResponse = JSON.parse(httpreq.responseText);
        let count = jsonResponse.classroomList.length - 1;

        console.log(jsonResponse)


        for (i = 0; i <= count; i++) {

            classroomHtmlOutput(jsonResponse.classroomList[i].classroomID,
                jsonResponse.classroomList[i].classroomName, jsonResponse.classroomList[i].subject,
                jsonResponse.classroomList[i].grade, jsonResponse.classroomList[i].year,jsonResponse.EnrolledClassroomList,jsonResponse.RequestedClassroomList);

        }

    }

    function classroomHtmlOutput( classroomId , classroomName , subject , gradeClass , yearOfExamination,enrolledClassroomList,RequestedClassroomList ){
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
            '                <div>'

let flag = 0;
        for(let i = 0;i<enrolledClassroomList.length+RequestedClassroomList.length;i++){
            if(enrolledClassroomList[i]==classroomId){
                classroomsListLinksSelect.innerHTML +=
                    '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:block;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                break;
            }else if(RequestedClassroomList[i]==classroomId){
                classroomsListLinksSelect.innerHTML +=
                    '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:block;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                break;
            }else{
                classroomsListLinksSelect.innerHTML +=
                    '                <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                break;
            }
        }

        /*if(enrolledClassroomList.length == 0 || RequestedClassroomList.length == 0 ){
            classroomsListLinksSelect.innerHTML +=
                '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';
            flag =1;
        }else{
            for(let i = 0;i<enrolledClassroomList.length;i++){
                if(enrolledClassroomList[i]==classroomId){
                    classroomsListLinksSelect.innerHTML +=
                        '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:block;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';
                    flag =1;
                    break;
                }

            }

        }

        if(RequestedClassroomList.length == 0){
            if(flag==1){
                classroomsListLinksSelect.innerHTML +=
                '                    <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';
            }else
            classroomsListLinksSelect.innerHTML +=
                '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

        }else{
            for(let i = 0;i<RequestedClassroomList.length;i++){
                if(RequestedClassroomList[i]==classroomId){
                    classroomsListLinksSelect.innerHTML +=
                        '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:block;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                    flag =1;
                    break;
                }

            }
            if(enrolledClassroomList.length == 0 && RequestedClassroomList.length==0){
                if(flag==0){
                    classroomsListLinksSelect.innerHTML +=
                        '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                        '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                }

            }
            if(enrolledClassroomList.length ==0 && RequestedClassroomList.length !=0){
                classroomsListLinksSelect.innerHTML +=
                    '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

            }

        }
        for(let i = 0;i<enrolledClassroomList.length+RequestedClassroomList.length;i++){
            if(classroomId==enrolledClassroomList[i] && classroomId==RequestedClassroomList[i]){
                classroomsListLinksSelect.innerHTML +=
                    '                    <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

            }else if(flag==0){
                classroomsListLinksSelect.innerHTML +=
                    '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                break;
            }
        }*/


            /*for(i=0;i<=enrolledClassroomList.length;i++){
                if(enrolledClassroomList[i]==classroomId){
                    classroomsListLinksSelect.innerHTML+=
                    '                    <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:block;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

                }

            }

        for(i=0;i<=RequestedClassroomList.length;i++){
            if(enrolledClassroomList[i]==classroomId){
                classroomsListLinksSelect.innerHTML+=
                    '                    <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                    '                    <input style="display:block;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

            }

        }*/




       /*     '                    <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll request" onclick="enableDisableStatus('+classroomId+')">' +
            '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Cancel" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
            '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Requested" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +*/



        classroomsListLinksSelect.innerHTML+='  </div>';


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

    console.log("sadsad asda : "+getUserIdClientSide())
    let id = getUserIdClientSide();

    httpreq.open( "POST" , "/EduClick_war_exploded/student/teacherProfileNameLoad" , true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + id);


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



const getUserIdClientSide = function (){

    let currentClassUrl = new URL( window.location.href );
    return currentClassUrl.searchParams.get( "userId" );

}
