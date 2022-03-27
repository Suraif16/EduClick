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

            classroomHtmlOutput(jsonResponse.classroomList[i].classroomId,
                jsonResponse.classroomList[i].classroomName, jsonResponse.classroomList[i].classroomSubject,
                jsonResponse.classroomList[i].classroomGrade, jsonResponse.classroomList[i].classroomYear,jsonResponse.classroomList[i].enrolledStatus,jsonResponse.classroomList[i].requestedStatus);

        }

    }

    function classroomHtmlOutput( classroomId , classroomName , subject , gradeClass , yearOfExamination,enrolledStatus,requestedStatus){
        console.log(classroomId)
        console.log(classroomName)
        console.log(subject)
        console.log(gradeClass)
        console.log(yearOfExamination)
        console.log(enrolledStatus)
        console.log(requestedStatus)


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


        if(enrolledStatus==true && requestedStatus==false){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:block;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }
        else if(enrolledStatus==false && requestedStatus==true){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:block;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }else{
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }

        /*if(eligibility=="Enrolled"){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:block;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }else if(eligibility==="Requested"){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:none;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:block;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }else if(eligibility==="Eligible"){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';


        }else if(eligibility===undefined){
            classroomsListLinksSelect.innerHTML +=
                '                <input style="display:block;" id="enable'+ classroomId +'" type="button" value="Enroll Request" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="disable'+ classroomId +'" type="button" value="Unenroll" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">' +
                '                    <input style="display:none;" id="requested'+ classroomId +'" type="button" value="Cancel Request" class="studentDisable" onclick="enableDisableStatus('+classroomId+')">';

        }*/

/*let flag = 0;
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
        }*/




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

    httpreq.open( "POST" , "/EduClick_war_exploded/student/teacherProfileNameLoad" , true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + getUserIdClientSide());

    function completeLogin( httpreq ){

        let jsonResponse = JSON.parse(httpreq.responseText);



        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            console.log(jsonResponse);
         /*  *************************/


            for( let i=0; i<jsonResponse.jsonArray1.length;i++) {

                console.log(jsonResponse.jsonArray1[i].Caption);

                if (jsonResponse.jsonArray1[i].path !== "" && jsonResponse.jsonArray1[i].Caption !=="") {

                    let innerPreviouseHTML = postContents.innerHTML;
                    postContents.innerHTML = '        <div class="post">    ' +
                        '              <div class="postContentContainer">' +
                        '                   <div class="postProfileSection">' +
                        '                        <a href="TeacherProfile.html" class="postProfile">' +
                        '                           <div class="postProfileImage">' +
                        '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                        '                           </div>' +
                        '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                        '</div>' + ' </a>' +
                        '                           <div class="postTimeAndDate" >' +
                        jsonResponse.jsonArray1[i].Time + ' | ' +
                        jsonResponse.jsonArray1[i].Date +
                        '                            </div>' +
                        '                    <div class="userOptions">' +
                        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                        '                    </div>' +
                        '                   </div>' +
                        '               </div>' +
                        '               <div class="postContentContainer">' +
                        '                   <div class="postData">' +
                        '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].Caption +

                        '                       </div>' +
                        '                       <div class="postPicture">' +
                        '                           <div class="postPictureImageContainer">' +
                        '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].path + '.jpeg">' +
                        '                           </div>' +
                        '                       </div>' +
                        '                     </div>' +
                        '                  </div>' +
                        '                   <div class="postContentContainer">' +
                        '                       <div class="postLikeShareButtons">' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                           <div class="countOfLikeShare" >' +
                        '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                        '                                   Likes' +
                        '                               </div >' +
                        '                               <div class="emptySpaceLikeShare">' +
                        '                               </div>' +
                        '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                        '                                    Shares' +
                        '                               </div>' +
                        '                              </div>' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                        </div>' +
                        '                       </div>' +
                        '                   </div>'


                    postContents.innerHTML += innerPreviouseHTML;


                }else if(jsonResponse.jsonArray1[i].Caption !==""){

                    let innerPreviouseHTML = postContents.innerHTML;
                    postContents.innerHTML = '        <div class="post">    ' +
                        '              <div class="postContentContainer">' +
                        '                   <div class="postProfileSection">' +
                        '                        <a href="TeacherProfile.html" class="postProfile">' +
                        '                           <div class="postProfileImage">' +
                        '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                        '                           </div>' +
                        '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                        '</div>' + ' </a>' +
                        '                           <div class="postTimeAndDate" >' +
                        jsonResponse.jsonArray1[i].Time + ' | ' +
                        jsonResponse.jsonArray1[i].Date +
                        '                            </div>' +
                        '                    <div class="userOptions">' +
                        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                        '                    </div>' +
                        '                   </div>' +
                        '               </div>' +
                        '               <div class="postContentContainer">' +
                        '                   <div class="postData">' +
                        '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].Caption +

                        '                       </div>' +

                        '                     </div>' +
                        '                  </div>' +
                        '                   <div class="postContentContainer">' +
                        '                       <div class="postLikeShareButtons">' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                           <div class="countOfLikeShare" >' +
                        '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                        '                                   Likes' +
                        '                               </div >' +
                        '                               <div class="emptySpaceLikeShare">' +
                        '                               </div>' +
                        '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                        '                                    Shares' +
                        '                               </div>' +
                        '                              </div>' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                        </div>' +
                        '                       </div>' +
                        '                   </div>'


                    postContents.innerHTML += innerPreviouseHTML;


                }else if (jsonResponse.jsonArray1[i].path !== "")
                {

                    let innerPreviouseHTML = postContents.innerHTML;
                    postContents.innerHTML = '        <div class="post">    ' +
                        '              <div class="postContentContainer">' +
                        '                   <div class="postProfileSection">' +
                        '                        <a href="TeacherProfile.html" class="postProfile">' +
                        '                           <div class="postProfileImage">' +
                        '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                        '                           </div>' +
                        '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                        '</div>' + ' </a>' +
                        '                           <div class="postTimeAndDate" >' +
                        jsonResponse.jsonArray1[i].Time + ' | ' +
                        jsonResponse.jsonArray1[i].Date +
                        '                            </div>' +
                        '                    <div class="userOptions">' +
                        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                        '                    </div>' +
                        '                   </div>' +
                        '               </div>' +
                        '               <div class="postContentContainer">' +
                        '                   <div class="postData">' +

                        '                       <div class="postPicture">' +
                        '                           <div class="postPictureImageContainer">' +
                        '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].path + '.jpeg">' +
                        '                           </div>' +
                        '                       </div>' +
                        '                     </div>' +
                        '                  </div>' +
                        '                   <div class="postContentContainer">' +
                        '                       <div class="postLikeShareButtons">' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                           <div class="countOfLikeShare" >' +
                        '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                        '                                   Likes' +
                        '                               </div >' +
                        '                               <div class="emptySpaceLikeShare">' +
                        '                               </div>' +
                        '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                        '                                    Shares' +
                        '                               </div>' +
                        '                              </div>' +
                        '                           <div class="likeShareButtons" >' +
                        '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                        '                           </div>' +
                        '                        </div>' +
                        '                       </div>' +
                        '                   </div>'


                    postContents.innerHTML += innerPreviouseHTML;





                }


            }

            console.log("hhhh");

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
    httpreq.send("userId=" + getUserIdClientSide());


    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);
        console.log("teacher pro name find loaded");


        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const name = document.getElementById("headerUserName");
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
