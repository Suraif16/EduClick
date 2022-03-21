let ePostGetAnswerList = [];
let markingStatus = false;

function showAnswers( id ){

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );

    if (answerContainer.style.display === "none"){

        ePostGetAnswerList.push( id );

        answerContainer.style.display = "flex";

    }else{

        ePostGetAnswerList.splice( ePostGetAnswerList.indexOf( id ) , 1 );

        answerContainer.style.display = "none";

    }

}

const getAnswersServer = function ( id ){

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            complete( this )

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherClassroomSelectAnswersServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "epostId=" + id );

    const complete = function ( httpreq ){

        let jsonObject = JSON.parse( httpreq.responseText );
        let answerList = jsonObject.answerList;

        answerContainer.innerHTML = "";

        for ( let i = 0 ; i < answerList.length ; i++ ) {

            let singleAnswer = '<div class="singleAnswer">' +
                '<div class="answerUser">' +
                '    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + answerList[i].userId + '" class="answerProfile">' +
                '        <div class="answerProfileImage">' +
                '            <img class="answerProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '        </div>' +
                '        <div class="answerProfileName" >' + answerList[i].studentName + '</div>' +
                '    </a>' +
                '    <div class="postTimeAndDate">' +
                answerList[i].answerTime + ' | ' + answerList[i].answerDate +
                '    </div>' +
                '    <div class="userOptions">' +
                '        <input class="userOptionsButton" type="button" value="    " id="answerOption1" onclick="showOptionMenu(1,\'answerOption\')">' +
                '    </div>' +
                '</div>' +
                '<div class="textAnswers">' +

                answerList[i].content +

                '</div>';

            if ( answerList[i].imageStatus === "true" ){

                singleAnswer +=    '<div class="pictureAnswers">' +
                    '    <a href="#">' +
                    '        <img src="../Resources/Images/AnswerImages/' + answerList[i].answerId + '.jpeg">' +
                    '    </a>' +
                    '</div>';

            }
            if ( answerList[i].marks === undefined ){

                singleAnswer +=    '<div class="marksForAnswers">' +
                    '    <input type="range" value="0" max="100" oninput="markingStatus=true;answer' + answerList[i].answerId + '.value = this.value" onchange="setMarks( ' + answerList[i].answerId + ' , this.value )" class="marksForAnswersRange">' +
                    '    <output id="answer' + answerList[i].answerId + '" class="marksForAnswersRangeValue">0</output>' +
                    '</div>' +
                    '</div>';

            }else{

                singleAnswer +=    '<div class="marksForAnswers">' +
                    '    <input type="range" value="' + parseInt( answerList[i].marks ) + '" max="100" oninput="markingStatus=true;answer' + answerList[i].answerId + '.value = this.value" onchange="setMarks( ' + answerList[i].answerId + ' , this.value )" class="marksForAnswersRange">' +
                    '    <output id="answer' + answerList[i].answerId + '" class="marksForAnswersRangeValue"> ' + answerList[i].marks + ' </output>' +
                    '</div>' +
                    '</div>';

            }

            answerContainer.innerHTML += singleAnswer;

        }

    }

}

const traverseEPostListToGetAnswers = function (){

    if ( !markingStatus ){

        ePostGetAnswerList.forEach( function ( id ){

            getAnswersServer( id );

        });

    }

}

setInterval( traverseEPostListToGetAnswers , 2500 );

function showMcqResult( id ){

    let mcqResultsInPostId = "mcqResultsInPost" + id;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );

    if (mcqResultsInPost.style.display === "none"){

        mcqResultsInPost.style.display = "flex";
        getMcqResult( mcqResultsInPost , id )

    }else{

        mcqResultsInPost.style.display = "none";

    }


}

const getMcqResult = function ( elementId , id ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            displayMcqResult( this )

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherClassroomSelectMcqResultServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "epostMcqId=" + id );

    const displayMcqResult = function ( httpreq ){


        let jsonResponse = JSON.parse( httpreq.responseText );
        elementId.innerHTML = "";

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            const mcqResultList = jsonResponse.mcqResultList;

            for ( mcqResultListElement of mcqResultList ) {

                elementId.innerHTML += '<div class="mcqSingleStudentResult">' +
                    '                        <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + mcqResultListElement.userId + '" class="mcqProfile">' +
                    '                            <div class="mcqProfileImage">' +
                    '                                <img class="mcqProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                    '                            </div>' +
                    '                            <div>' + mcqResultListElement.firstName + " " + mcqResultListElement.lastName + '</div>' +
                    '                        </a>' +
                    '                        <div class="mcqSingleStudentResultMarks">' +
                    mcqResultListElement.marks +
                    '                        </div>' +
                    '                    </div>'

            }

        }else{
            alert("something went wrong!!!");
        }

    }

}

const setMarks = function ( id , marks ){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            markingStatus = false;

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherSetMarksForAnswersServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "answerId=" + id + "&marks=" + marks );

}