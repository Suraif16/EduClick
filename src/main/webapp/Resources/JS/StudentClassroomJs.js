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
                console.log(jsonAnswerLoadResponse.Answered)

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


function showMcqResult( id ){

    let mcqResultsInPostId = "mcqResultsInPost" + id;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );

    if (mcqResultsInPost.style.display === "none"){

        mcqResultsInPost.style.display = "flex";

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
            if(jsonStatusResponse.Status==="Enable"){
                loadStudentEducationalPosts(false);
            }else if(jsonStatusResponse.Status==="Disable"){
                console.log("Case case case")
            }
            status = jsonStatusResponse.Status;
        }else{
            alert("something went wrong!!!");
        }

    }

}

document.onreadystatechange = function (){


    if ( document.readyState === 'complete' ){

        /* when the document is loaded and complete this function will run*/
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

