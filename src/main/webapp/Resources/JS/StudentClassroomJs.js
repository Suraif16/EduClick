let rightPanelStatus = false; /*if it is false the list is hidden, if it is true the list it visible*/
const rightPanel = document.getElementById("rightPanel");
let status ;


function showRightPanel(){

    if(rightPanelStatus){

        rightPanel.style.display = "none";
        rightPanelStatus = false;

    }else{

        rightPanel.style.display = "flex";
        rightPanelStatus = true;

    }

}


function enableDisableStatus( id ){

    let enableStringValue = "enable" + id;

    let disableStringValue = "disable" + id;

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);

    if (disableButton.style.display === "none"){

        /*defaultView.getComputedStyle(enableButton)*/
        disableButton.style.display = "block";
        enableButton.style.display = "none";


    }else{

        disableButton.style.display = "none";
        enableButton.style.display = "block";

    }

}

function showAnswers( id ){

    /*  let com = document.getElementById("ans");
      if(com.style.display === "none"){

          com.style.display = "flex";
      }else{
          com.style.display = "none"
      }*/

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );
    let com = document.getElementById("ans" + id) ;

    if (answerContainer.style.display === "none"){

        answerContainer.style.display = "flex";

        com.style.display = "flex";

        console.log("ID is loading cerrr : "+id)

        let httpreq = new XMLHttpRequest();
        httpreq.onreadystatechange = function (){

            if (this.readyState === 4 && this.status === 200){
                completeAnswerLoad( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
            }

        }

        httpreq.open( "POST" , "/EduClick_war_exploded/student/studentEducationalPostAnswerLoad" , true);
        httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        httpreq.send("ePostId=" + id);


        function completeAnswerLoad( httpreq ){
            let jsonAnswerLoadResponse = JSON.parse( httpreq.responseText );
            if( jsonAnswerLoadResponse.serverResponse === "null Session" || jsonAnswerLoadResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonAnswerLoadResponse.serverResponse === "Allowed") {

                console.log("Danata everything is okay cerrrrrrr")
                console.log("Answer Load Response : "+jsonAnswerLoadResponse.AnswerContent.ImageStatus)

                let answersInPost = "answersInPost"+id;
                let answer = document.getElementById(answersInPost);
                let postTextBox = "ans"+id;
                let textBoxId = document.getElementById(postTextBox);
                let markText = "";

                if(jsonAnswerLoadResponse.AnswerDetails.Marks == null){
                    markText = "Your teacher has not marked your answer yet";
                }else{
                    markText = "Your mark is "+jsonAnswerLoadResponse.AnswerDetails.Marks+"%";
                }
                console.log("Mark text : "+markText)

                if(jsonAnswerLoadResponse.Answered=="Yes"){

                    answer.innerHTML = "";

                    if(jsonAnswerLoadResponse.AnswerContent.ImageStatus == "true"){
                        let htmlString =
                            '<div class="singleAnswer">' +
                            '                                    <div class="textAnswers" id="myComment">' +
                            jsonAnswerLoadResponse.AnswerContent.Content +
                            '                                    </div>' +
                            '                                    <div class="pictureAnswers">' +
                            '                                        <a href="#">' +
                            '                                            <img src="../Resources/Images/AnswerImages/' + id + '.jpeg">' +
                            '                                        </a>' +
                            '                                    </div>' +
                            '                                    <div class="Marks">'
                            + markText +
                            '                                    </div>' +
                            '                                </div>'
                        answer.innerHTML+=htmlString;
                        textBoxId.innerHTML="You have already answered to this question!";
                        textBoxId.style.color = "white";
                        textBoxId.style.backgroundColor = "#F4330A";

                    }else if(jsonAnswerLoadResponse.AnswerContent.ImageStatus == "false"){
                        let htmlString =
                            '<div class="singleAnswer">' +
                            '                                    <div class="textAnswers" id="myComment">' +
                            jsonAnswerLoadResponse.AnswerContent.Content +
                            '                                    </div>' +
                            '                                    <div class="Marks">'
                            + markText +
                            '                                    </div>' +
                            '                                </div>'
                        answer.innerHTML+=htmlString;
                        textBoxId.innerHTML="You have already answered to this question!";
                        textBoxId.style.color = "white";
                        textBoxId.style.backgroundColor = "#F4330A";
                    }





                }else if(jsonAnswerLoadResponse.Answered=="No"){

                    /*answer.innerHTML = "Enter your answer";*/

                    /*let htmlString =
                        '<div class="singleAnswer">' +
                        '                                    <div class="textAnswers" id="myComment">' +
                        jsonAnswerLoadResponse.AnswerContent.Content +
                        '                                    </div>' +
                        '                                    <div class="pictureAnswers">' +
                        '                                        <a href="#">' +
                        '                                            <img src="../Resources/Images/AnswerImages/' + jsonAnswerLoadResponse.AnswerContent.ImagePath + '.jpeg">' +
                        '                                        </a>' +
                        '                                    </div>' +
                        '                                    <div class="Marks">'
                                                                + markText +
                        '                                    </div>' +
                        '                                </div>'
                    answer.innerHTML+=htmlString;*/

                }


            }else{
                alert("something went wrong!!!");
            }
        }

    }


    else{

        answerContainer.style.display = "none";
        com.style.display = "none"
    }




}


function showMcqResult( x,id ){

    console.log("PostID eka awooo : "+id)

    for (let i = 0; i <x.length; i++) {

        console.log( "id is here : " , x[i] );

    }

    let mcqResultsInPostId = "mcqResultsInPost" + id;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );

    if (mcqResultsInPost.style.display === "none"){

        mcqResultsInPost.style.display = "flex";

        console.log("Openedo okay?")

        let mcqAnswers = [];

        for (let i = 0; i <x.length; i++) {

            let MCQName = "MCQ"+x[i];

            let option = document.getElementsByName(MCQName);

            for (let j = 0; j < option.length; j++) {
                if (option[j].checked){
                    console.log("Check Vlues : "+option[j].value)
                    mcqAnswers[i] = option[j].value;
                }
            }

        }

        // submitMCQToServer(mcqAnswers,id);

        let goodToSubmit = 1;
        for (i = 0 ; i < 10 ; i++){
            console.log(mcqAnswers[i]+"\n")
            if(mcqAnswers[i] === undefined){
                goodToSubmit = 0;
            }
        }

        for(i=0;i<10;i++){
            console.log(x[i]);
        }


        if(goodToSubmit === 1){
            let httpreq = new XMLHttpRequest();

            httpreq.onreadystatechange = function (){

                if ( this.readyState === 4 && this.status === 200 ){

                    console.log( "done!!!");

                    let jsonResponse = JSON.parse( httpreq.responseText );
                    if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                        window.location.replace("/EduClick_war_exploded/Login.html");
                    }else if(jsonResponse.serverResponse === "Allowed"){

                        console.log(jsonResponse.Result)
                        let resultTag = "mcqResultsInPost"+id
                        let studentResult = document.getElementById(resultTag);
                        studentResult.innerHTML="";
                        let htmlString =
                            '<div class="mcqSingleStudentResult">' +
                            '          <div  class="mcqSingleStudentResultMarks">' +
                            "Your result is "+jsonResponse.Result+"%" +
                            '           </div>' +
                            '</div>'
                        studentResult.innerHTML+=htmlString




                    }else{
                        alert("something went wrong!!!");
                    }

                }

            }
            let dataString =
                "mcq1=" + '{"questionId" : ' + '"' + x[0] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[0] + '"'+'}'+
                "&mcq2=" + '{"questionId" : ' + '"' + x[1] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[1] + '"'+'}'+
                "&mcq3=" + '{"questionId" : ' + '"' + x[2] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[2] + '"'+'}'+
                "&mcq4=" + '{"questionId" : ' + '"' + x[3] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[3] + '"'+'}'+
                "&mcq5=" + '{"questionId" : ' + '"' + x[4] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[4] + '"'+'}'+
                "&mcq6=" + '{"questionId" : ' + '"' + x[5] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[5] + '"'+'}'+
                "&mcq7=" + '{"questionId" : ' + '"' + x[6] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[6] + '"'+'}'+
                "&mcq8=" + '{"questionId" : ' + '"' + x[7] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[7] + '"'+'}'+
                "&mcq9=" + '{"questionId" : ' + '"' + x[8] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[8] + '"'+'}'+
                "&mcq10=" + '{"questionId" : ' + '"' + x[9] + '"' + ', "answerChoice" : ' + '"' + mcqAnswers[9] + '"'+'}' + "&postId="+id;



            console.log(dataString)

            httpreq.open( "POST" , "/EduClick_war_exploded/student/mcqResultLoad" , true);
            httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
            httpreq.send(dataString);


        }else{
            let mcqResultsInPostId = "mcqResultsInPost" + id;
            let mcqResultsInPost = document.getElementById( mcqResultsInPostId );
            mcqResultsInPost.style.display = "none";
            alert("You cant keep empty fields")
        }

    }else{

        mcqResultsInPost.style.display = "none";

    }


}

const checkEnableOrDisable = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            return completeEnabiltyCheck( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/student/checkStudentEnableOrDisable" , true);
    httpreq.send();

    function completeEnabiltyCheck( httpreq ){



        let jsonStatusResponse = JSON.parse(httpreq.responseText);


        console.log(jsonStatusResponse.Status)

        if( jsonStatusResponse.serverResponse === "null Session" || jsonStatusResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");

        }else if(jsonStatusResponse.serverResponse === "Allowed") {
            console.log(jsonStatusResponse.Status)
            if(jsonStatusResponse.Status==="Disable"){
                /*loadStudentEducationalPosts(false);*/
                let postContents = document.getElementById("postContents");
                postContents.innerHTML = ""
                console.log("Case case case disabled")

            }/*else if(jsonStatusResponse.Status==="Enable"){
                console.log("Case case case disabled")
                console.log("I am enabled")
                loadStudentEducationalPosts(false);
                br
            }*/
            status = jsonStatusResponse.Status;
        }else{
            alert("something went wrong!!!");
        }

    }

}

document.onreadystatechange = function (){


    if ( document.readyState === 'complete' ){

        /* when the document is loaded and complete this function will run*/
        loadStudentEducationalPosts(false);
        checkEnableOrDisable();
        sendNameData();
        getClassroomList();


    }

}

/*const sendServerData = function (){
    /!* This function gets the username from the server*!/
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ); /!*This is where we get the response when the request was successfully sent and a successfully response is received *!/
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        const headerUserProfileIdAchorElement = document.getElementById("headerUserProfileId");

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            /!* This is where I need work everytime as per the authentication filter*!/
            const name = document.getElementById("headerUserName");
            name.innerHTML = jsonLoginResponse.firstName;
            let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;

            headerUserProfileIdAchorElement.setAttribute("href" , url);
        }else{
            alert("something went wrong!!!");
        }

    }


}*/

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

const getClassroomList = function (){
    /* This function gets the Lists of classrooms from the server*/
    const classroomsListLinksSelect = document.getElementById("classroomsListLinks");
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentNewsFeedLoaded" , true);
    httpreq.send();

    function complete( httpreq ){

        let jsonResponse = JSON.parse(httpreq.responseText);

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            /* This is where I need work everytime as per the authentication filter*/

            let count = jsonResponse.classroomList.length - 1;

            for(i = 0 ; i <= count ; i++){

                classroomHtmlOutput( jsonResponse.classroomList[i].classroomID ,
                    jsonResponse.classroomList[i].classroomName , jsonResponse.classroomList[i].subject ,
                    jsonResponse.classroomList[i].grade , jsonResponse.classroomList[i].year);

            }

        }else{
            alert("something went wrong!!!");
        }

    }


    function classroomHtmlOutput( classroomId , classroomName , subject , gradeClass , yearOfExamination ){

        classroomsListLinksSelect.innerHTML += '<div class="classroomsListLinksItems">' +
            '' +
            '                        <a href="/EduClick_war_exploded/SaveClassroomId?id=' + classroomId + '"  class="classRooms">' +
            '' +
            '                            <p>Classroom Name : ' + classroomName +'</p>' +
            '                            <p>Subject : ' + subject + '</p>' +
            '                            <p>Grade : ' + gradeClass + '</p>' +
            '                            <p>Year of Examination : ' + yearOfExamination + '</p>' +
            '' +
            '                        </a>' +
            '' +
            '                    </div>'



    }


}
setInterval( checkEnableOrDisable , 5000);


const getClassroomIdClientSide = function (){

    let currentClassUrl = new URL( window.location.href );
    return currentClassUrl.searchParams.get( "clsId" );

}


