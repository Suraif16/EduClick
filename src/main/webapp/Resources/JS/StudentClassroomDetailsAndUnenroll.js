const confirmationBox = document.getElementById( "confirmationBox" );

const displayCurrentClassroomDetails = function (){

    let httpreq = new XMLHttpRequest();
    const rightPanelClassroom = document.getElementById( "rightPanelClassroom" );

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            let jsonResponse = JSON.parse( this.responseText );



            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {

                console.log("Mama duwanoooooooooooooooooooooo")
                /* This is where I need work everytime as per the authentication filter*/

                rightPanelClassroom.innerHTML = '<div class="rightPanelClassroomDetails">' +
                    '                Classroom : ' + jsonResponse.classroomDetails.classroomName + ' : ' + jsonResponse.classroomDetails.yearOfExamination + ' : ' + jsonResponse.classroomDetails.grade + ' : ' + jsonResponse.classroomDetails.subject +
                    '            </div>' +
                    '            <div class="rightPanelUnEnrollButton">' +
                    '                <input type="button" value="Unenroll" onclick="showUnenrollConfirmationsBox()">' +
                    '            </div>';

                confirmationBox.innerHTML = '<div class="confirmationBoxContent">' +
                    '            Do you really wish to delete the classroom ' + ' : ' + jsonResponse.classroomDetails.classroomName + ' : ' + jsonResponse.classroomDetails.yearOfExamination + ' : ' + jsonResponse.classroomDetails.grade + ' : ' + jsonResponse.classroomDetails.subject +' ? <br/>By doing this you will loose all students and Contents in this classroom.' +
                    '        </div>' +
                    '        <div class="confirmationBoxYesNoButtons">' +
                    '            <input class="yesButton" type="button" value="Yes" onclick="deleteClassroom(' + getClassroomIdClientSide() + ')">' +
                    '            <input class="noButton" type="button" value="No" onclick="hideUnenrollConfirmationsBox()">' +
                    '        </div>'

            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/user/StudentClassroomDetailsSelect" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "classroomId=" + getClassroomIdClientSide() );

}

const deleteClassroom = function ( id ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            let jsonResponse = JSON.parse( this.responseText );

            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed") {
                /* This is where I need work everytime as per the authentication filter*/

                hideUnenrollConfirmationsBox();
                window.location.replace("/EduClick_war_exploded/Student/student.html");

            }else{
                alert("something went wrong!!!");
            }

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/classroomDeleteServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "classroomId=" + id );

}

const showUnenrollConfirmationsBox = function (){

    confirmationBox.style.display = "flex";

}

const hideUnenrollConfirmationsBox = function (){
    console.log("hasnhasbduhasbdubasdyhbaudaS")

    confirmationBox.style.display = "none";

}